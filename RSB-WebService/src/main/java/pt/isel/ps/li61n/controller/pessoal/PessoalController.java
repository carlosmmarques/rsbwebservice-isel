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
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
import pt.isel.ps.li61n.model.entities.RegistoFormacao;
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
    public Callable<List<PessoalDTO>> obterElementosDoPessoal(
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
                throw new NotFoundException("Não existem elementos para os critérios introduzidos!");
            return pessoalDTOs;
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
    public Callable<PessoalDTO> obterUmElementoDoPessoal(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new PessoalDTO(pessoalService.obterUmElementoDoPessoal(id)
                    , request
                    , ModeloDeRepresentacao.Normal.class);
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
    public Callable<?> obterRegistosDeFormacaoDeUmElemento(
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
            return registoFormacaoDTOs;
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
    public Callable<?> obterRegistoDeFormacaoDeUmElemento(
            @PathVariable Long elemento_id,
            @PathVariable Long registoFormacao_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new RegistoFormacaoDTO(
                    pessoalService.obterUmRegistoDeFormacaoDeUmElemento(elemento_id, registoFormacao_id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
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
    public Callable<?> obterResponsabilidadesOperacionaisDeUmElemento(
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
            return responsabilidadesOperacionais;
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
    public Callable<ResponseEntity<?>> inserirUmElementoDoPessoal(
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
    public Callable<ResponseEntity<?>> actualizarUmElementoDoPessoal(
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
    public Callable<ResponseEntity<?>> actualizarFormacaoDeElementoDoPessoal(
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
            return new ResponseEntity<>(
                    new RegistoFormacaoDTO(
                            registoFormacao,
                            request,
                            ModeloDeRepresentacao.Normal.class),
                    novoRegisto[0] ? HttpStatus.CREATED : HttpStatus.OK);
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

}
