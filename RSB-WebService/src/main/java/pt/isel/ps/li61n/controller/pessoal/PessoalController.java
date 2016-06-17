package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.isel.ps.li61n.controller.Representation;
import pt.isel.ps.li61n.controller.RsbBaseController;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
import pt.isel.ps.li61n.model.entities.RegistoFormacao;
import pt.isel.ps.li61n.model.services.IPessoalService;

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
     * Instância do Serviço
     */
    @Autowired
    IPessoalService pessoalService;

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
        pessoalService.obterElementosDoPessoal(
                postofuncional_id,
                turno_id,
                instalacao_id,
                categoria_id,
                formacao_id,
                responsabilidadeoperacional_id)
                .stream().forEach(elementoDoPessoal -> pessoalDTOs.add(
                new PessoalDTO<>(elementoDoPessoal, request, Representation.Summary.class)));
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
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        return new PessoalDTO<>(pessoalService.obterUmElementoDoPessoal(id)
                , request
                , Representation.Normal.class);
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
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        final List<RegistoFormaçãoDoElementoDTO> registoFormaçãoDoElementoDTOs = new LinkedList<>();
        pessoalService.obterRegistosDeFormacaoDeUmElemento(id).stream().forEach(
                registoFormacao ->
                        registoFormaçãoDoElementoDTOs.add(
                                new RegistoFormaçãoDoElementoDTO(registoFormacao, request)
                        )
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
            @PathVariable Long elemento_id,
            @PathVariable Long registoFormacao_id,
            HttpServletRequest request
    ) throws Exception {
        return new RegistoFormaçãoDoElementoDTO(
                pessoalService.obterUmRegistoDeFormacaoDeUmElemento(elemento_id, registoFormacao_id)
                , request);
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
    public Set<ResponsabilidadeOperacionalDTO<Representation.Summary>> obterResponsabilidadesOperacionaisDeUmElemento(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        final Set<ResponsabilidadeOperacionalDTO<Representation.Summary>> responsabilidadesOperacionais = new LinkedHashSet<>();
        pessoalService.obterResponsabilidadesOperacionaisDeUmElemento(id).stream()
                .forEach(responsabilidadeOperacional -> {
                            responsabilidadesOperacionais.add(
                                    new ResponsabilidadeOperacionalDTO<>(responsabilidadeOperacional
                                            , request
                                            , Representation.Summary.class));
                        }
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
    @JsonView(Representation.Verbose.class) // A representação incluirá apenas campos com esta anotação
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

        ElementoDoPessoal elemento = pessoalService
                .inserirUmElementoDoPessoal(
                        idInterno,
                        matricula,
                        nummecanografico,
                        abreviatura,
                        nome,
                        datanascimento,
                        telefone1,
                        postofuncional_id,
                        tipopresenca_id,
                        turno_id,
                        instalacao_id,
                        categoria_id,
                        dataatribuicaocategoria,
                        classificacaoformacao,
                        telefone2,
                        email,
                        nif,
                        dataingresso,
                        tipodocidentificacao,
                        numdocidentificacao,
                        factorelegibilidade
                );
        return new ResponseEntity<>(new PessoalDTO<>(elemento, request, Representation.Verbose.class), HttpStatus.CREATED);
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
            @PathVariable Long id,
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
        ElementoDoPessoal elemento = pessoalService.actualizarUmElementoDoPessoal(
                id,
                idInterno,
                matricula,
                abreviatura,
                nome,
                datanascimento,
                telefone1,
                postofuncional_id,
                tipopresenca_id,
                turno_id,
                instalacao_id,
                categoria_id,
                dataatribuicaocategoria,
                classificacaoformacao,
                telefone2,
                email,
                nif,
                dataingresso,
                tipodocidentificacao,
                numdocidentificacao,
                factorelegibilidade
        );
        return new ResponseEntity<>(new PessoalDTO<>(elemento, request, Representation.Normal.class), HttpStatus.OK);
    }


    /**
     * @param elemento_id    Identificador do elemento.
     * @param formacao_id    Identificador da Formação
     * @param dataFormacao   data de aquisição da Formação
     * @param dataCaducidade data de caducidade da Formação
     * @param request        HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                       nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(Representation.Normal.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{elemento_id}/formacao/{formacao_id}", method = RequestMethod.PUT)
    // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public ResponseEntity<?> actualizarFormacaoDeElementoDoPessoal(
            @PathVariable Long elemento_id,
            @PathVariable Long formacao_id,
            @RequestParam(value = "dataformacao", required = true) Date dataFormacao,
            @RequestParam(value = "datacaducidade", required = false) Optional<Date> dataCaducidade,
            HttpServletRequest request
    ) throws Exception {
        ResponseEntity<?> response;
        final boolean[] novoRegisto = {false};
        synchronized (this) {
            RegistoFormacao registoFormacao = pessoalService.actualizarFormacaoDeElementoDoPessoal(
                    elemento_id,
                    formacao_id,
                    dataFormacao,
                    dataCaducidade,
                    novoRegisto
            );
            response = new ResponseEntity<>(new RegistoFormacaoDTO<>(registoFormacao, request, Representation.Normal.class), novoRegisto[0] ? HttpStatus.CREATED : HttpStatus.OK);
        }
        return response;
    }


    /**
     * @param id      Identificador do elemento.
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(Representation.Normal.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    // Este Método atende ao verbo HTTP DELETE
    @ResponseBody //Responsebody em JSON
    public ResponseEntity<?> EliminarElementoDoPessoal(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        ElementoDoPessoal elemento = pessoalService.EliminarElementoDoPessoal(
                id
        );
        return new ResponseEntity<>(new PessoalDTO<>(elemento, request, Representation.Normal.class), HttpStatus.OK);
    }

}
