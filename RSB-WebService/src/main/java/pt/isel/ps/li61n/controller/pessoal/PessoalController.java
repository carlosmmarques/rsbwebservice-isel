package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.isel.ps.li61n.controller.RsbBaseController;
import pt.isel.ps.li61n.model.entities.*;
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
     * Instância do repositório de elementos do pessoal
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
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(method = RequestMethod.GET) /* Este Método atende ao verbo HTTP GET para "/pessoal" */
    @ResponseBody //Responsebody em JSON
    public List<PessoalDTO> obterElementosDoPessoal(
            @RequestParam(value = "postofuncional_id", required = false) Optional<Long> postofuncional_id,
            @RequestParam(value = "turno_id", required = false) Optional<Long> turno_id,
            @RequestParam(value = "instalacao_id", required = false) Optional<Long> instalacao_id,
            @RequestParam(value = "categoria_id", required = false) Optional<Long> categoria_id,
            @RequestParam(value = "registoFormacao_id", required = false) Optional<Long> formacao_id,
            @RequestParam(value = "responsabilidadeoperacional_id", required = false)
            Optional<Long> responsabilidadeoperacional_id,
            HttpServletRequest request
    ) {

        final List<PessoalDTO> pessoalDTOs = new LinkedList<>();
        pessoal_Repository.findAll().stream()
                .filter(pessoa -> postofuncional_id.map(v -> v.equals(pessoa.getPostoFuncional().getId())).orElse(true))
                .filter(pessoa -> turno_id.map(v -> v.equals(pessoa.getTurno().getId())).orElse(true))
                .filter(pessoa -> instalacao_id.map(v -> v.equals(pessoa.getInstalacao().getId())).orElse(true))
                .filter(pessoa ->
                        categoria_id.map(v ->
                                pessoa.getAtribuicaoDeCategorias().stream().sorted(
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
                .forEach(pessoal -> pessoalDTOs.add(new PessoalDTO(pessoal, request)));
        return pessoalDTOs;
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Representação do elemento na forma de um DTO facilmente serializavel em Json.
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public PessoalDTO obterUmElementoDoPessoal(
            @PathVariable String id,
            HttpServletRequest request
    ) {
        final ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(id));
        if (elemento != null)
            return new PessoalDTO(elemento, request);
        throw new PessoalNotFoundException(id);
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Lista das formações de um determinado elemento na forma de um DTO facilmente serializável em JSon
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}/formacao", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public List<RegistoFormaçãoDoElementoDTO> obterRegistosDeFormacaoDeUmElemento(
            @PathVariable String id,
            HttpServletRequest request
    ) {
        final List<RegistoFormaçãoDoElementoDTO> registoFormaçãoDoElementoDTOs = new LinkedList<>();
        final ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(id));
        if (elemento != null) {
            elemento.getFormacoes().stream().forEach(
                    registoFormacao ->
                            registoFormaçãoDoElementoDTOs.add(new RegistoFormaçãoDoElementoDTO(registoFormacao, request))
            );
            return registoFormaçãoDoElementoDTOs;
        }
        throw new PessoalNotFoundException(id);
    }


    /**
     * @param elemento_id        Identificador do elemento do pessoal
     * @param registoFormacao_id Identificador do registo da formação
     * @param request            HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                           nomeadamente do URI.
     * @return O registo da formação do elemento na forma de um DTO facilmente serializável em JSon
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{elemento_id}/formacao/{registoFormacao_id}", method = RequestMethod.GET)
    // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public RegistoFormaçãoDoElementoDTO obterUmRegistoDeFormacaoDeUmElemento(
            @PathVariable String elemento_id,
            @PathVariable String registoFormacao_id,
            HttpServletRequest request
    ) {
        final ElementoDoPessoal elemento = pessoal_Repository.findOne(Long.parseLong(elemento_id));
        if (elemento != null) {
            try {
                final RegistoFormacao registoFormacao = elemento.getFormacoes().stream()
                        .filter(regFormacao -> regFormacao.getId().equals(Long.parseLong(registoFormacao_id)))
                        .findAny().get();
                return new RegistoFormaçãoDoElementoDTO(registoFormacao, request);
            } catch (Exception e) {
                throw new RegistoFormacaoNotFoundException(registoFormacao_id, elemento_id);
            }
        }
        throw new RegistoFormacaoNotFoundException(registoFormacao_id, elemento_id);
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Conjunto de Responsabilidades Operacionais a que o elemento está habilitado
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}/responsabilidadeoperacional", method = RequestMethod.GET)
    // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public Set<ResponsabilidadeOperacional> obterResponsabilidadesOperacionaisDeUmElemento(
            @PathVariable String id,
            HttpServletRequest request
    ) {
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
     * @param numdocidentificação     Numero do documento de Identificação
     * @param factorelegibilidade     Factor de elegibilidade
     * @param request                 HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
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
            @RequestParam(value = "numdocidentificação", required = false) Optional<String> numdocidentificação,
            @RequestParam(value = "factorelegibilidade", required = false) Optional<Float> factorelegibilidade,
            HttpServletRequest request
    ) {

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
        numdocidentificação.ifPresent(elemento::setNumDocIdentificação);
        factorelegibilidade.ifPresent(elemento::setFactorElegibilidade);
        try {
            elemento.setPostoFuncional(postoFuncional_Repository.findOne(postofuncional_id));
        } catch (Exception e) {
            throw new PostoFuncionalNotFoundException(postofuncional_id);
        }
        try {
            elemento.setTipoPresenca(tipoPresenca_Repository.findOne(tipopresenca_id));
        } catch (Exception e) {
            throw new TipoPresencaNotfoundException(tipopresenca_id);
        }
        try {
            elemento.setInstalacao(instalacao_Repository.findOne(instalacao_id));
        } catch (Exception e) {
            throw new InstalacaoNotfoundException(tipopresenca_id);
        }
        pessoal_Repository.save(elemento);

        try {
            atribuicaoDeCategoria.setCategoria(categoria_Repository.findOne(categoria_id));
        } catch (Exception e) {
            throw new CategoriaNotfoundException(categoria_id);
        }
        atribuicaoDeCategoria.setElementoDoPessoal(elemento);
        atribuicaoDeCategoria.setDataAtribuicaoCategoria(dataatribuicaocategoria);
        atribuicaoDeCategoria.setClassificacaoFormacao(classificacaoformacao);
        atribuicaoCategoria_Repository.save(atribuicaoDeCategoria);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param numeroMecanografico O numero mecanográfico do elemento a validar
     * @throws RegistoPessoalNumMecanograficoExistsException
     */
    private void validarExistenciaDeNumeroMecanografico(String numeroMecanografico) throws RegistoPessoalNumMecanograficoExistsException {

        if (pessoal_Repository.findAll().stream()
                .filter(p -> p.getNumMecanografico().equals(numeroMecanografico))
                .count() > 0)
            throw new RegistoPessoalNumMecanograficoExistsException(numeroMecanografico);
    }
}
