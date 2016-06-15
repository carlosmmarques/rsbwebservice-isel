package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.isel.ps.li61n.controller.Representation;
import pt.isel.ps.li61n.controller.RsbBaseController;
import pt.isel.ps.li61n.model.entities.AtribuicaoCategoria;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
import pt.isel.ps.li61n.model.entities.RegistoFormacao;
import pt.isel.ps.li61n.model.entities.ResponsabilidadeOperacional;
import pt.isel.ps.li61n.model.repository.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

/**
 * PessoalController - Description
 * Created on 13/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping(value = "/pessoal")
public class PessoalController extends RsbBaseController<ElementoDoPessoal> {

    /**
     * Instâncias dos repositórios
     */
    @Autowired
    Pessoal_IRepository pessoal_Repository;
    @Autowired
    PostoFuncional_IRepository postoFuncional_Repository;
    @Autowired
    TipoPresenca_IRepository tipoPresenca_Repository;
    @Autowired
    Categoria_IRepository categoria_Repository;
    @Autowired
    Instalacao_IRepository instalacao_Repository;
    @Autowired
    AtribuicaoCategoria_IRepository atribuicaoCategoria_Repository;
    @Autowired
    Turno_IRepository turno_Repository;
    @Autowired
    RegistoFormacao_IRepository registoFormacao_Repository;
    @Autowired
    Formacao_IRepository formacao_Repository;

    /**
     * @param postofuncional_id              Opcional - O identificador (Id) do Posto Funcional
     * @param turno_id                       Opcional - O identificador (Id) do turno
     * @param instalacao_id                  Opcional - O identificador (Id) da instalação a que o elemento está atribuido
     * @param categoria_id                   Opcional - O identificador (Id) da categoria do elemento
     * @param formacao_id                    Opcional - O identificador (Id) de uma formação que o elemento tenha adquirido
     * @param responsabilidadeoperacional_id Opcional - O identificador (Id) de uma responsabilidade operacional
     *                                       que o elemento possa desempenhar
     * @return Lista de ElementoDoPessoal global ou filtrada através dos parametros acima designados.
     */
    @JsonView(Representation.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(method = RequestMethod.GET) /* Este Método atende ao verbo HTTP GET para "/pessoal" */
    @ResponseBody //Responsebody em JSON
    public List<PessoalDTO<Representation.Summary>> obterElementosDoPessoal(
            @RequestParam(value = "postofuncional_id", required = true) Optional<Long> postofuncional_id,
            @RequestParam(value = "turno_id", required = true) Optional<Long> turno_id,
            @RequestParam(value = "instalacao_id", required = true) Optional<Long> instalacao_id,
            @RequestParam(value = "categoria_id", required = true) Optional<Long> categoria_id,
            @RequestParam(value = "formacao_id", required = true) Optional<Long> formacao_id,
            @RequestParam(value = "responsabilidadeoperacional_id", required = true) Optional<Long> responsabilidadeoperacional_id,
            HttpServletRequest request
    ) throws Exception {
        final List<PessoalDTO<Representation.Summary>> pessoalDTOs = new LinkedList<>();
        pessoal_Repository.findAll().stream()
                .filter(pessoa -> postofuncional_id.map(v -> v.equals(pessoa.getPostoFuncional().getId())).orElse(true))
                .filter(pessoa -> turno_id.map(v -> v.equals(pessoa.getTurno().getId())).orElse(true))
                .filter(pessoa -> instalacao_id.map(v -> v.equals(pessoa.getInstalacao().getId())).orElse(true))
                .filter(pessoa ->
                        categoria_id.map(v ->
                                pessoa.getAtribuicõesDeCategoria().stream().sorted(
                                        (c1, c2) ->
                                                c2.getDataAtribuicaoCategoria()
                                                        .compareTo(c1.getDataAtribuicaoCategoria())
                                ).findFirst().map(c -> c.getCategoria().getId().equals(v))
                        ).orElse(Optional.of(true)).get())
                .filter(pessoa ->
                        formacao_id.map(v ->
                                pessoa.getFormacoes().stream().anyMatch(
                                        f -> f.getFormacao().getId().equals(v))
                        ).orElse(true))
                .filter(pessoa ->
                        responsabilidadeoperacional_id.map(v ->
                                pessoa.getFormacoes().stream().anyMatch(
                                        f -> f.getFormacao().getResponsabilidadesOperacionais().stream().anyMatch(
                                                r -> r.getId().equals(v)))
                        ).orElse(true))
                .forEach(pessoal -> pessoalDTOs.add(new PessoalDTO<>(pessoal, request, Representation.Summary.class)));
        if (pessoalDTOs.size() == 0)
            throw new NotFoundException("Não existem elementos para os critérios introduzidos!");
        return pessoalDTOs;
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Representação do elemento na forma de um DTO facilmente serializavel em Json.
     */
    @JsonView(Representation.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public PessoalDTO<Representation.Normal> obterUmElementoDoPessoal(
            @PathVariable String id,
            HttpServletRequest request
    ) throws Exception {
        final ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(id));

        if (elemento == null)
            throw new NotFoundException(String.format("Não é possível obter o Elemento do Pessoal com id: %s", id));

        return new PessoalDTO<>(elemento, request, Representation.Normal.class);
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Lista das formações de um determinado elemento na forma de um DTO facilmente serializável em JSon
     */
    @JsonView(Representation.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}/formacao", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public List<RegistoFormaçãoDoElementoDTO> obterRegistosDeFormacaoDeUmElemento(
            @PathVariable String id,
            HttpServletRequest request
    ) throws Exception {
        final List<RegistoFormaçãoDoElementoDTO> registoFormaçãoDoElementoDTOs = new LinkedList<>();
        final ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(id));

        if (elemento == null)
            throw new NotFoundException(String.format("Não é possível obter o Elemento do Pessoal com id: %s", id));

        elemento.getFormacoes().stream().forEach(
                registoFormacao ->
                        registoFormaçãoDoElementoDTOs.add(new RegistoFormaçãoDoElementoDTO(registoFormacao, request))
        );

        return registoFormaçãoDoElementoDTOs;
    }


    /**
     * @param elemento_id        Identificador do elemento do pessoal
     * @param registoFormacao_id Identificador do registo da formação
     * @param request            HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                           nomeadamente do URI.
     * @return O registo da formação do elemento na forma de um DTO facilmente serializável em JSon
     */
    @JsonView(Representation.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{elemento_id}/formacao/{registoFormacao_id}", method = RequestMethod.GET)
    // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public RegistoFormaçãoDoElementoDTO obterUmRegistoDeFormacaoDeUmElemento(
            @PathVariable String elemento_id,
            @PathVariable String registoFormacao_id,
            HttpServletRequest request
    ) throws Exception {
        final ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(elemento_id));
        if (elemento != null) {
            try {
                final RegistoFormacao registoFormacao = elemento.getFormacoes().stream()
                        .filter(regFormacao -> regFormacao.getId().equals(Long.parseLong(registoFormacao_id)))
                        .findAny().get();
                return new RegistoFormaçãoDoElementoDTO(registoFormacao, request);
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não existe nenhum registo de formação com o id %s para o elemento %id", registoFormacao_id, elemento_id));
            }
        }
        throw new NotFoundException(String.format("Não existe nenhum registo de formação com o id %s para o elemento %id", registoFormacao_id, elemento_id));
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Conjunto de Responsabilidades Operacionais a que o elemento está habilitado
     */
    @JsonView(Representation.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}/responsabilidadeoperacional", method = RequestMethod.GET)
    // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public Set<ResponsabilidadeOperacional> obterResponsabilidadesOperacionaisDeUmElemento(
            @PathVariable String id,
            HttpServletRequest request
    ) throws Exception {
        final Set<ResponsabilidadeOperacional> responsabilidadesOperacionais = new LinkedHashSet<>();
        final ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(id));
        elemento.getFormacoes().stream()
                .filter(registoFormacao -> //Obter apenas registos de formação que não estão caducados
                        registoFormacao.getDataCaducidadeFormacao().getTime() > Calendar.getInstance().getTimeInMillis())
                .forEach(registoFormacao ->
                        registoFormacao.getFormacao().getResponsabilidadesOperacionais().stream()
                                .forEach(responsabilidadesOperacionais::add)
                );
        return responsabilidadesOperacionais;
    }

    /**
     * @param idInterno               Identificador interno da Unidade Estrutural
     * @param matricula               Matricula do elemento
     * @param nummecanografico        Numero mecanográfico (Unico - Destina-se a mapear ao sistema de RH da CML)
     * @param abreviatura             Abreviatura - Nome curto
     * @param nome                    Nome do elemento
     * @param datanascimento          Data de nascimento do elemento
     * @param telefone1               Telefone do elemento
     * @param postofuncional_id       Código (id) do Posto Funcional por omissão
     * @param tipopresenca_id         Codigo (id) do tipo de presença por omissão
     * @param turno_id                código (id) do turno por omissão
     * @param instalacao_id           código (id) da Instalação por omissão
     * @param categoria_id            Código (id) da Categoria por omissão
     * @param dataatribuicaocategoria Data a que a categoria foi atribuida ao elemento
     * @param classificacaoformacao   Classificação com que o elemento adquiriu a categoria
     * @param telefone2               Telefone do elemento
     * @param email                   e-mail do elemento
     * @param nif                     Numero de Identificação Fiscal do Elemento
     * @param dataingresso            Data de Ingresso no Corpo de Bombeiros
     * @param tipodocidentificacao    Tipo de documento de Identificação (Bilhete de Identidade / Cartão de Cidadão)
     * @param numdocidentificacao     Numero do documento de Identificação
     * @param factorelegibilidade     Factor de elegibilidade
     * @param request                 HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(Representation.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(method = RequestMethod.POST) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public ResponseEntity<?> inserirUmElementoDoPessoal(
            @RequestParam(value = "idInterno", required = true) String idInterno,
            @RequestParam(value = "matricula", required = true) String matricula,
            @RequestParam(value = "nummecanografico", required = true) String nummecanografico,
            @RequestParam(value = "abreviatura", required = true) String abreviatura,
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "datanascimento", required = true) Date datanascimento,
            @RequestParam(value = "telefone1", required = true) String telefone1,
            @RequestParam(value = "postofuncional_id", required = true) Long postofuncional_id,
            @RequestParam(value = "tipopresenca_id", required = true) String tipopresenca_id,
            @RequestParam(value = "turno_id", required = true) Long turno_id,
            @RequestParam(value = "instalacao_id", required = true) Long instalacao_id,
            @RequestParam(value = "categoria_id", required = true) Long categoria_id,
            @RequestParam(value = "dataatribuicaocategoria", required = true) Date dataatribuicaocategoria,
            @RequestParam(value = "classificacaoformacao", required = true) Float classificacaoformacao,
            @RequestParam(value = "telefone2", required = false) Optional<String> telefone2,
            @RequestParam(value = "email", required = false) Optional<String> email,
            @RequestParam(value = "nif", required = false) Optional<String> nif,
            @RequestParam(value = "dataingresso", required = false) Optional<Date> dataingresso,
            @RequestParam(value = "tipodocidentificacao", required = false) Optional<ElementoDoPessoal.TipoDocIdentificacao> tipodocidentificacao,
            @RequestParam(value = "numdocidentificacao", required = false) Optional<String> numdocidentificacao,
            @RequestParam(value = "factorelegibilidade", required = false) Optional<Float> factorelegibilidade,
            HttpServletRequest request
    ) throws Exception {

        validarExistenciaDeNumeroMecanografico(nummecanografico);

        ElementoDoPessoal elemento = new ElementoDoPessoal();
        AtribuicaoCategoria atribuicaoDeCategoria = new AtribuicaoCategoria();

        elemento.setIdInterno(idInterno);
        elemento.setMatricula(matricula);
        elemento.setNumMecanografico(nummecanografico);
        elemento.setAbreviatura(abreviatura);
        elemento.setNome(nome);
        elemento.setDataNascimento(datanascimento);
        elemento.setTelefone1(telefone1);
        telefone2.ifPresent(elemento::setTelefone2);
        email.ifPresent(elemento::seteMail);
        nif.ifPresent(elemento::setNif);
        dataingresso.ifPresent(elemento::setDataIngresso);
        tipodocidentificacao.ifPresent(elemento::setTipoDocIdentificacao);
        numdocidentificacao.ifPresent(elemento::setNumDocIdentificacao);
        factorelegibilidade.ifPresent(elemento::setFactorElegibilidade);
        try {
            elemento.setPostoFuncional(postoFuncional_Repository.findOne(postofuncional_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter o Posto Funcional com id: %s", postofuncional_id.toString()));
        }
        try {
            elemento.setTipoPresenca(tipoPresenca_Repository.findOne(tipopresenca_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter o Tipo de Presença com id: %s", tipopresenca_id));
        }
        try {
            elemento.setTurno(turno_Repository.findOne(turno_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter o Turno com id: %s", turno_id));
        }
        try {
            elemento.setInstalacao(instalacao_Repository.findOne(instalacao_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter a Instalação com id: %s", instalacao_id));
        }
        try {
            atribuicaoDeCategoria.setCategoria(categoria_Repository.findOne(categoria_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter a Categoria com id: %s", categoria_id));
        }
        elemento = pessoal_Repository.saveAndFlush(elemento);
        atribuicaoDeCategoria.setElementoDoPessoal(elemento);
        atribuicaoDeCategoria.setDataAtribuicaoCategoria(dataatribuicaocategoria);
        atribuicaoDeCategoria.setClassificacaoFormacao(classificacaoformacao);
        atribuicaoDeCategoria = atribuicaoCategoria_Repository.saveAndFlush(atribuicaoDeCategoria);
        List<AtribuicaoCategoria> atribuicoesCategoria = new ArrayList<AtribuicaoCategoria>();
        atribuicoesCategoria.add(atribuicaoDeCategoria);
        elemento.setAtribuicõesDeCategoria(atribuicoesCategoria);

        return new ResponseEntity<>(new PessoalDTO<>(elemento, request, Representation.Normal.class), HttpStatus.CREATED);
    }


    /**
     * @param id                      Identificador do elemento.
     * @param idInterno               Identificador interno da Unidade Estrutural
     * @param matricula               Matricula do elemento
     * @param abreviatura             Abreviatura - Nome curto
     * @param nome                    Nome do elemento
     * @param datanascimento          Data de nascimento do elemento
     * @param telefone1               Telefone do elemento
     * @param postofuncional_id       Código (id) do Posto Funcional por omissão
     * @param tipopresenca_id         Codigo (id) do tipo de presença por omissão
     * @param turno_id                código (id) do turno por omissão
     * @param instalacao_id           código (id) da Instalação por omissão
     * @param categoria_id            Código (id) da Categoria por omissão
     * @param dataatribuicaocategoria Data a que a categoria foi atribuida ao elemento
     * @param classificacaoformacao   Classificação com que o elemento adquiriu a categoria
     * @param telefone2               Telefone do elemento
     * @param email                   e-mail do elemento
     * @param nif                     Numero de Identificação Fiscal do Elemento
     * @param dataingresso            Data de Ingresso no Corpo de Bombeiros
     * @param tipodocidentificacao    Tipo de documento de Identificação (Bilhete de Identidade / Cartão de Cidadão)
     * @param numdocidentificacao     Numero do documento de Identificação
     * @param factorelegibilidade     Factor de elegibilidade
     * @param request                 HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(Representation.Normal.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public ResponseEntity<?> actualizarUmElementoDoPessoal(
            @PathVariable String id,
            @RequestParam(value = "idInterno", required = false) Optional<String> idInterno,
            @RequestParam(value = "matricula", required = false) Optional<String> matricula,
            // @RequestParam(value = "nummecanografico", required = true) Optional<String> nummecanografico,
            @RequestParam(value = "abreviatura", required = false) Optional<String> abreviatura,
            @RequestParam(value = "nome", required = false) Optional<String> nome,
            @RequestParam(value = "datanascimento", required = false) Optional<Date> datanascimento,
            @RequestParam(value = "telefone1", required = false) Optional<String> telefone1,
            @RequestParam(value = "postofuncional_id", required = false) Optional<Long> postofuncional_id,
            @RequestParam(value = "tipopresenca_id", required = false) Optional<String> tipopresenca_id,
            @RequestParam(value = "turno_id", required = false) Optional<Long> turno_id,
            @RequestParam(value = "instalacao_id", required = false) Optional<Long> instalacao_id,
            @RequestParam(value = "categoria_id", required = false) Optional<Long> categoria_id,
            @RequestParam(value = "dataatribuicaocategoria", required = true) Optional<Date> dataatribuicaocategoria,
            @RequestParam(value = "classificacaoformacao", required = true) Optional<Float> classificacaoformacao,
            @RequestParam(value = "telefone2", required = false) Optional<String> telefone2,
            @RequestParam(value = "email", required = false) Optional<String> email,
            @RequestParam(value = "nif", required = false) Optional<String> nif,
            @RequestParam(value = "dataingresso", required = false) Optional<Date> dataingresso,
            @RequestParam(value = "tipodocidentificacao", required = false) Optional<ElementoDoPessoal.TipoDocIdentificacao> tipodocidentificacao,
            @RequestParam(value = "numdocidentificacao", required = false) Optional<String> numdocidentificacao,
            @RequestParam(value = "factorelegibilidade", required = false) Optional<Float> factorelegibilidade,
            HttpServletRequest request
    ) throws Exception {

        ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.valueOf(id));

        if (elemento == null)
            throw new NotFoundException(String.format("Não é possível obter o Elemento do Pessoal com id: %s", id));

        idInterno.ifPresent(elemento::setIdInterno);
        matricula.ifPresent(elemento::setMatricula);
        // nummecanografico.ifPresent(elemento::setNumMecanografico);
        abreviatura.ifPresent(elemento::setAbreviatura);
        nome.ifPresent(elemento::setNome);
        datanascimento.ifPresent(elemento::setDataNascimento);
        telefone1.ifPresent(elemento::setTelefone1);
        telefone2.ifPresent(elemento::setTelefone2);
        email.ifPresent(elemento::seteMail);
        nif.ifPresent(elemento::setNif);
        dataingresso.ifPresent(elemento::setDataIngresso);
        tipodocidentificacao.ifPresent(elemento::setTipoDocIdentificacao);
        numdocidentificacao.ifPresent(elemento::setNumDocIdentificacao);
        factorelegibilidade.ifPresent(elemento::setFactorElegibilidade);
        postofuncional_id.ifPresent(pf -> {
            try {
                elemento.setPostoFuncional(postoFuncional_Repository.findOne(pf));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter o Posto Funcional com id: %s", pf.toString()));
            }
        });
        tipopresenca_id.ifPresent(tp -> {
            try {
                elemento.setTipoPresenca(tipoPresenca_Repository.findOne(tp));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter o Tipo de Presença com id: %s", tp.toString()));
            }
        });
        turno_id.ifPresent(t -> {
            try {
                elemento.setTurno(turno_Repository.findOne(t));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter o Turno com id: %s", t.toString()));
            }
        });
        instalacao_id.ifPresent(i -> {
            try {
                elemento.setInstalacao(instalacao_Repository.findOne(i));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter a Instalação com id: %s", i.toString()));
            }
        });
        categoria_id.ifPresent(cat_id -> {
            List<AtribuicaoCategoria> atribuicoesCategoria = atribuicaoCategoria_Repository.findByElementoDoPessoal(elemento);
            Optional<AtribuicaoCategoria> atribuicaoDeCategoriaOpt = atribuicoesCategoria
                    .stream()
                    .filter(c -> c.getCategoria().getId().equals(cat_id))
                    .findFirst();
            AtribuicaoCategoria atribuicaoCategoria =
                    atribuicaoDeCategoriaOpt.isPresent() ?
                            atribuicaoDeCategoriaOpt.get() :
                            new AtribuicaoCategoria();
            if (atribuicaoCategoria.getCategoria() == null) {
                try {
                    atribuicaoCategoria.setCategoria(categoria_Repository.findOne(cat_id));
                    atribuicaoCategoria.setElementoDoPessoal(elemento);
                } catch (Exception e) {
                    throw new NotFoundException(String.format("Não é possível obter a Categoria com id: %s", cat_id.toString()));
                }
            }
            dataatribuicaocategoria.ifPresent(atribuicaoCategoria::setDataAtribuicaoCategoria);
            classificacaoformacao.ifPresent(atribuicaoCategoria::setClassificacaoFormacao);
            atribuicaoCategoria_Repository.save(atribuicaoCategoria);
        });
        pessoal_Repository.save(elemento);

        return new ResponseEntity<>(new PessoalDTO<>(elemento, request, Representation.Normal.class), HttpStatus.OK);
    }


    /**
     * @param elemento_id   Identificador do elemento.
     * @param formacao_id   Identificador da Formação
     * @param dataFormacao data de aquisição da Formação
     * @param dataCaducidade   data de caducidade da Formação
     * @param request      HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                     nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(Representation.Normal.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{elemento_id}/formacao/{formacao_id}", method = RequestMethod.PUT)
    // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public ResponseEntity<?> actualizarFormacaoDeElementoDoPessoal(
            @PathVariable String elemento_id,
            @PathVariable String formacao_id,
            @RequestParam(value = "dataformacao", required = true) Date dataFormacao,
            @RequestParam(value = "datacaducidade", required = false) Optional<Date> dataCaducidade,
            HttpServletRequest request
    ) throws Exception {
        ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(elemento_id));
        List<RegistoFormacao> registosFormacao = registoFormacao_Repository.findByElementoDoPessoal(elemento);
        Optional<RegistoFormacao> registoFormacaoOpt;
        boolean novoRegisto = false;
        try {
            registoFormacaoOpt = registosFormacao
                    .stream()
                    .filter( r -> r.getFormacao().getId().equals(Long.parseLong(formacao_id)))
                    .findFirst();
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter o registo da formação com id %s para o elemento %s", formacao_id, elemento_id));
        }
        RegistoFormacao registoFormacao =
                registoFormacaoOpt.isPresent() ?
                        registoFormacaoOpt.get() :
                        new RegistoFormacao();
        if (registoFormacao.getElementoDoPessoal() == null) {
            try {
                novoRegisto = true;
                registoFormacao.setElementoDoPessoal(elemento);
                registoFormacao.setFormacao(formacao_Repository.findOne(Long.parseLong(formacao_id)));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter o registo de Formacao com id: %s", formacao_id));
            }
        }
        registoFormacao.setDataAquisicaoFormacao(dataFormacao);
        if (dataCaducidade.isPresent()) registoFormacao.setDataCaducidadeFormacao(dataCaducidade.get());
        else registoFormacao.setDataCaducidadeFormacao(registoFormacao.getFormacao().getValidade());
        registoFormacao_Repository.save(registoFormacao);
        return new ResponseEntity<>(new RegistoFormacaoDTO<>(registoFormacao, request, Representation.Normal.class), novoRegisto ? HttpStatus.CREATED : HttpStatus.OK);
    }


    /**
     * @param id   Identificador do elemento.
     * @param request      HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                     nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(Representation.Normal.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    // Este Método atende ao verbo HTTP DELETE
    @ResponseBody //Responsebody em JSON
    public ResponseEntity<?> EliminarElementoDoPessoal(
            @PathVariable String id,
            HttpServletRequest request
    ) throws Exception {
        ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(id));
        elemento.setEliminado(true);
        /* TODO Validar a necessidade de lançar excepção quando um elmento é eliminado. Exemplo:
            Um elementoa afecto a um turno. Faz sentido validar esta afectação antes de o sinalizar como "Eliminado"?
         */

//        List<AtribuicaoCategoria> atribuicoesCategoria = atribuicaoCategoria_Repository.findByElementoDoPessoal(elemento);
//        atribuicoesCategoria.stream()
//                .forEach(atribuicaoCategoria_Repository::delete);
//        pessoal_Repository.delete(Long.parseLong(id));
        elemento = pessoal_Repository.save(elemento);
        return new ResponseEntity<>(new PessoalDTO<>(elemento, request, Representation.Normal.class), HttpStatus.OK);
    }



    /**
     * @param numeroMecanografico O numero mecanográfico do elemento a validar
     * @throws ConflictException se o numero mecanográfico já está atribuido
     */
    private void validarExistenciaDeNumeroMecanografico(String numeroMecanografico) throws ConflictException {

        if (pessoal_Repository.findAll().stream()
                .filter(p -> p.getNumMecanografico().equals(numeroMecanografico))
                .count() > 0)
            throw new ConflictException(String.format("Já existe um elemento com o numero mecanográfico %s", numeroMecanografico));
    }


}
