package pt.isel.ps.li61n.controller.pessoal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.controller.RsbBaseController;
import pt.isel.ps.li61n.controller.dto.*;
import pt.isel.ps.li61n.controller.error.NaoEncontradoException;
import pt.isel.ps.li61n.model.entities.*;
import pt.isel.ps.li61n.model.services.IPessoalService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.Callable;

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
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

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
    @RequestMapping(method = RequestMethod.GET) /* Este Método atende ao verbo HTTP GET para "/pessoal" */
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterElementosDoPessoal(
            @RequestParam(value = "postofuncional_id", required = true) Optional<Long> postofuncional_id,
            @RequestParam(value = "turno_id", required = true) Optional<Long> turno_id,
            @RequestParam(value = "instalacao_id", required = true) Optional<Long> instalacao_id,
            @RequestParam(value = "categoria_id", required = true) Optional<Long> categoria_id,
            @RequestParam(value = "formacao_id", required = true) Optional<Long> formacao_id,
            @RequestParam(value = "responsabilidadeoperacional_id", required = true) Optional<Long> responsabilidadeoperacional_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            final List<PessoalDTO> pessoalDTOs = new LinkedList<>();
            pessoalService.obterElementosDoPessoal(
                    postofuncional_id,
                    turno_id,
                    instalacao_id,
                    categoria_id,
                    formacao_id,
                    responsabilidadeoperacional_id)
                    .stream().forEach(elementoDoPessoal -> pessoalDTOs.add(
                    new PessoalDTO(elementoDoPessoal, request, ModeloDeRepresentacao.Sumario.class)));
            if (pessoalDTOs.size() == 0)
                throw new NaoEncontradoException("Não existem elementos para os critérios introduzidos!");
            return new ResponseEntity<>(pessoalDTOs, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Representação do elemento na forma de um DTO facilmente serializavel em Json.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterElementoDoPessoal(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PessoalDTO pessoalDTO = new PessoalDTO(
                    pessoalService.obterElementoDoPessoal(id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(pessoalDTO, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Lista das formações de um determinado elemento na forma de um DTO facilmente serializável em JSon
     */
    @RequestMapping(value = "/{id}/formacao", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterRegistosDeFormacaoDeElemento(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            final List<RegistoFormacaoDTO> registoFormacaoDTOs = new LinkedList<>();
            pessoalService.obterRegistosDeFormacaoDeUmElemento(id).stream().forEach(
                    registoFormacao ->
                            registoFormacaoDTOs.add(
                                    new RegistoFormacaoDTO(registoFormacao,
                                            request,
                                            ModeloDeRepresentacao.Sumario.class)
                            )
            );
            return new ResponseEntity<>(registoFormacaoDTOs, HttpStatus.OK);
        };
    }


    /**
     * @param elemento_id        Identificador do elemento do pessoal
     * @param registoFormacao_id Identificador do registo da formação
     * @param request            HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                           nomeadamente do URI.
     * @return O registo da formação do elemento na forma de um DTO facilmente serializável em JSon
     */
    @RequestMapping(value = "/{elemento_id}/formacao/{registoFormacao_id}", method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterRegistoDeFormacaoDeElemento(
            @PathVariable Long elemento_id,
            @PathVariable Long registoFormacao_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            RegistoFormacaoDTO registoFormacaoDTO = new RegistoFormacaoDTO(
                    pessoalService.obterRegistoDeFormacaoDeUmElemento(elemento_id, registoFormacao_id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(registoFormacao_id, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Conjunto de Responsabilidades Operacionais a que o elemento está habilitado
     */
    @RequestMapping(value = "/{id}/responsabilidadeoperacional", method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterResponsabilidadesOperacionaisDeElemento(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            final Set<ResponsabilidadeOperacionalDTO> responsabilidadesOperacionais = new LinkedHashSet<>();
            pessoalService.obterResponsabilidadesOperacionaisDeUmElemento(id).stream()
                    .forEach(responsabilidadeOperacional -> {
                                responsabilidadesOperacionais.add(
                                        new ResponsabilidadeOperacionalDTO(responsabilidadeOperacional
                                                , request
                                                , ModeloDeRepresentacao.Sumario.class));
                            }
                    );
            return new ResponseEntity<>(responsabilidadesOperacionais, HttpStatus.OK);
        };
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
    @RequestMapping(method = RequestMethod.POST) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirElementoDoPessoal(
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
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            ElementoDoPessoal elemento = pessoalService
                    .inserirElementoDoPessoal(
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
            return new ResponseEntity<>(new PessoalDTO(elemento, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarElementoDoPessoal(
            @PathVariable Long id,
            @RequestParam(value = "idInterno", required = false) Optional<String> idInterno,
            @RequestParam(value = "matricula", required = false) Optional<String> matricula,
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
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            ElementoDoPessoal elemento = pessoalService.actualizarElementoDoPessoal(
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
            return new ResponseEntity<>(new PessoalDTO(elemento, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
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
    @RequestMapping(value = "/{elemento_id}/formacao/{formacao_id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarFormacaoDeElementoDoPessoal(
            @PathVariable Long elemento_id,
            @PathVariable Long formacao_id,
            @RequestParam(value = "dataformacao", required = true) Date dataFormacao,
            @RequestParam(value = "datacaducidade", required = false) Optional<Date> dataCaducidade,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            final boolean[] novoRegisto = {false};
            RegistoFormacao registoFormacao = pessoalService.actualizarFormacaoDeElementoDoPessoal(
                    elemento_id,
                    formacao_id,
                    dataFormacao,
                    dataCaducidade,
                    novoRegisto
            );
            RegistoFormacaoDTO registoFormacaoDTO = new RegistoFormacaoDTO(
                    registoFormacao,
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(registoFormacaoDTO, novoRegisto[0] ? HttpStatus.CREATED : HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do elemento.
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> EliminarElementoDoPessoal(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            ElementoDoPessoal elemento = pessoalService.EliminarElementoDoPessoal(
                    id
            );
            return new ResponseEntity<>(new PessoalDTO(elemento, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @RequestMapping(value = "/categoria", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterCategorias(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<CategoriaDTO> categoriasDTO = new LinkedList<>();
            pessoalService.obterCategorias().stream()
                    .forEach(categoria -> categoriasDTO.add(new CategoriaDTO(categoria, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(categoriasDTO, HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador da Categoria.
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @RequestMapping(value = "/categoria/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterCategoria(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(new CategoriaDTO(pessoalService.obterCategoria(id), request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }

    /**
     * @param quadro           Quadro de Categoria (COMANDO, BOMBEIRO OU OUTRO)
     * @param abreviatura      Abreviatura da categoria
     * @param descricao        Descrição da categoria
     * @param nivelHierarquico Nível Hierarquico
     * @param request          HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                         nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/categoria", method = RequestMethod.POST) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirCategoria(
            @RequestParam(value = "quadro", required = true) String quadro,
            @RequestParam(value = "abreviatura", required = true) String abreviatura,
            @RequestParam(value = "descricao", required = true) String descricao,
            @RequestParam(value = "nivelhierarquico", required = true) Integer nivelHierarquico,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Categoria categoria = pessoalService
                    .inserirCategoria(
                            quadro,
                            abreviatura,
                            descricao,
                            nivelHierarquico
                    );
            return new ResponseEntity<>(new CategoriaDTO(categoria, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
    }

    /**
     * @param id               Identificador da Categoria
     * @param quadro           Quadro de Categoria (COMANDO, BOMBEIRO OU OUTRO)
     * @param abreviatura      Abreviatura da categoria
     * @param descricao        Descrição da categoria
     * @param nivelHierarquico Nível Hierarquico
     * @param request          HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                         nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/categoria/{id}", method = RequestMethod.PUT) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarCategoria(
            @PathVariable Long id,
            @RequestParam(value = "quadro", required = false) Optional<String> quadro,
            @RequestParam(value = "abreviatura", required = false) Optional<String> abreviatura,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            @RequestParam(value = "nivelhierarquico", required = false) Optional<Integer> nivelHierarquico,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Categoria categoria = pessoalService.actualizarCategoria(
                    id,
                    quadro,
                    abreviatura,
                    descricao,
                    nivelHierarquico
            );
            return new ResponseEntity<>(new CategoriaDTO(categoria, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador da Categoria
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/categoria/{id}", method = RequestMethod.DELETE) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarCategoria(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Categoria categoria = pessoalService.eliminarCategoria(
                    id
            );
            return new ResponseEntity<>(new CategoriaDTO(categoria, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
    }


    /**
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @RequestMapping(value = "/postofuncional", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterPostosFuncionais(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<PostoFuncionalDTO> postosFuncionaisDTO = new LinkedList<>();
            pessoalService.obterPostosFuncionais().stream()
                    .forEach(postoFuncional -> postosFuncionaisDTO.add(new PostoFuncionalDTO(postoFuncional, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(postosFuncionaisDTO, HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do Posto Funcional
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @RequestMapping(value = "/postofuncional/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterPostoFuncional(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PostoFuncionalDTO postoFuncionalDTO = new PostoFuncionalDTO(
                    pessoalService.obterPostoFuncional(id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(
                    postoFuncionalDTO,
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param designacao Designação do Posto Funcional
     * @param descricao  Descrição do Posto Funcional
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/postofuncional", method = RequestMethod.POST) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirPostoFuncional(
            @RequestParam(value = "designacao", required = true) String designacao,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PostoFuncional postoFuncional = pessoalService
                    .inserirPostoFuncional(
                            designacao,
                            descricao
                    );
            PostoFuncionalDTO postoFuncionalDTO = new PostoFuncionalDTO(
                    postoFuncional,
                    request,
                    ModeloDeRepresentacao.Verboso.class
            );
            return new ResponseEntity<>(postoFuncionalDTO, HttpStatus.CREATED);
        };
    }

    /**
     * @param id         Identificador do Posto Funcional
     * @param designacao Designação do Posto Funcional
     * @param descricao  Descrição do Posto Funcional
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/postofuncional/{id}", method = RequestMethod.PUT) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarPostoFuncional(
            @PathVariable Long id,
            @RequestParam(value = "designacao", required = true) Optional<String> designacao,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PostoFuncional postoFuncional = pessoalService.actualizarPostoFuncional(
                    id,
                    designacao,
                    descricao
            );
            PostoFuncionalDTO postoFuncionalDTO = new PostoFuncionalDTO(
                    postoFuncional,
                    request,
                    ModeloDeRepresentacao.Verboso.class
            );
            return new ResponseEntity<>(postoFuncionalDTO, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador do Posto Funcional
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/postofuncional/{id}", method = RequestMethod.DELETE) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarPostoFuncional(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PostoFuncional postoFuncional = pessoalService.eliminarPostoFuncional(
                    id
            );
            PostoFuncionalDTO postoFuncionalDTO = new PostoFuncionalDTO(
                    postoFuncional,
                    request,
                    ModeloDeRepresentacao.Verboso.class
            );
            return new ResponseEntity<>(postoFuncionalDTO, HttpStatus.CREATED);
        };
    }


    /**
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @RequestMapping(value = "/formacao", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterFormacoes(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<FormacaoDTO> formacoesDTO = new LinkedList<>();
            pessoalService.obterFormacoes().stream()
                    .forEach(formacao -> formacoesDTO.add(new FormacaoDTO(formacao, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(formacoesDTO, HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador da Formação.
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @RequestMapping(value = "/formacao/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterFormacao(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            FormacaoDTO formacaoDTO = new FormacaoDTO(
                    pessoalService.obterFormacao(id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(
                    formacaoDTO,
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param validade   Validade (Caducidade) da Formação - Se omisso será aceite o valor por omissão -1
     * @param designacao Designação da Formação
     * @param descricao  Descrição da Formação
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/formacao", method = RequestMethod.POST) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirFormacao(
            @RequestParam(value = "validade", required = false, defaultValue = "-1") Float validade,
            @RequestParam(value = "designacao", required = true) String designacao,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Formacao formacao = pessoalService
                    .inserirFormacao(
                            validade,
                            designacao,
                            descricao
                    );
            FormacaoDTO formacaoDTO = new FormacaoDTO(
                    formacao,
                    request,
                    ModeloDeRepresentacao.Verboso.class
            );
            return new ResponseEntity<>(formacaoDTO, HttpStatus.CREATED);
        };
    }

    /**
     * @param id         Identificador da Formação
     * @param validade   Validade (Caducidade) da Formação
     * @param designacao Designação da Formação
     * @param descricao  Descrição da Formação
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/formacao/{id}", method = RequestMethod.PUT) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarFormacao(
            @PathVariable Long id,
            @RequestParam(value = "validade", required = false) Optional<Float> validade,
            @RequestParam(value = "designacao", required = true) Optional<String> designacao,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Formacao formacao = pessoalService.actualizarFormacao(
                    id,
                    validade,
                    designacao,
                    descricao
            );
            FormacaoDTO formacaoDTO = new FormacaoDTO(
                    formacao,
                    request,
                    ModeloDeRepresentacao.Verboso.class
            );
            return new ResponseEntity<>(formacaoDTO, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador da Formação
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @RequestMapping(value = "/formacao/{id}", method = RequestMethod.DELETE) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarFormacao(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Formacao formacao = pessoalService.eliminarFormacao(
                    id
            );
            FormacaoDTO formacaoDTO = new FormacaoDTO(
                    formacao,
                    request,
                    ModeloDeRepresentacao.Verboso.class
            );
            return new ResponseEntity<>(formacaoDTO, HttpStatus.CREATED);
        };
    }

}
