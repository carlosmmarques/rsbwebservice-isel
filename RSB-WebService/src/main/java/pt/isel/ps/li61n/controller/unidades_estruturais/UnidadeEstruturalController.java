package pt.isel.ps.li61n.controller.unidades_estruturais;

import com.fasterxml.jackson.annotation.JsonView;
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
import pt.isel.ps.li61n.controller.error.exception.ErroNaoDeterminadoException;
import pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException;
import pt.isel.ps.li61n.model.entities.*;
import pt.isel.ps.li61n.model.services.IUnidadeEstruturalService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * Turnos -  Atendimento a pedidos da secção nuclear de gestão de Presenças
 * Created on 13/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping(value = "/unidadeestrutural")
public class UnidadeEstruturalController extends RsbBaseController<UnidadeEstruturalController> {

    /**
     * Instância do Serviço
     */
    @Autowired
    IUnidadeEstruturalService unidadeEstruturalService;

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     *
     * @param request Instancia de HttpServletRequest que expõe informação para os servlets HTTP.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterUnidadesEstruturais(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            final List<UnidadeEstruturalDTO> unidadeEstruturalDTOs = new LinkedList<>();
            unidadeEstruturalService.obterUnidadesEstruturais().stream().forEach(ue -> {
                unidadeEstruturalDTOs.add(
                        new UnidadeEstruturalDTO(ue, request, ModeloDeRepresentacao.Sumario.class));
            });
            if (unidadeEstruturalDTOs.size() == 0)
                throw new NaoEncontradoException("Não existem elementos para os critérios introduzidos!");
            return new ResponseEntity<>(unidadeEstruturalDTOs, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador da Unidade Estrutura
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterUnidadeEstrutural(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            UnidadeEstruturalDTO unidadeEstruturalDTO = new UnidadeEstruturalDTO(
                    unidadeEstruturalService.obterUnidadeEstrutural(id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(unidadeEstruturalDTO, HttpStatus.OK);
        };
    }


    /**
     * @param designacao               Designação da Unidade Estrutural
     * @param tipounidadeestrutural_id Identificador de Tipo de Unidade Estrutural
     * @param unidadeestruturalmae_id  Identificador da Unidade Estutural Mãe
     * @param nivelhierarquico         Nível hierarquico
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirUnidadeEstrutural(
            @RequestParam(value = "designacao", required = true) String designacao,
            @RequestParam(value = "tipounidadeestrutural_id", required = true) Long tipounidadeestrutural_id,
            @RequestParam(value = "unidadeestruturalmae_id", required = false) Long unidadeestruturalmae_id,
            @RequestParam(value = "nivelhierarquico", required = false) Integer nivelhierarquico,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            UnidadeEstrutural unidadeEstrutural = unidadeEstruturalService.inserirUnidadeEstrutural(
                    designacao,
                    tipounidadeestrutural_id,
                    unidadeestruturalmae_id,
                    nivelhierarquico
            );
            return new ResponseEntity<>(new UnidadeEstruturalDTO(unidadeEstrutural, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
    }


    /**
     * @param id                       Indentificador da Unidade Estrutural
     * @param designacao               Designação da Unidade Estrutural
     * @param tipounidadeestrutural_id Identificador de Tipo de Unidade Estrutural
     * @param unidadeestruturalmae_id  Identificador da Unidade Estutural Mãe
     * @param nivelhierarquico         Nível hierarquico
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarUnidadeEstrutural(
            @PathVariable Long id,
            @RequestParam(value = "designacao", required = false) Optional<String> designacao,
            @RequestParam(value = "tipounidadeestrutural_id", required = false) Optional<Long> tipounidadeestrutural_id,
            @RequestParam(value = "unidadeestruturalmae_id", required = false) Optional<Long> unidadeestruturalmae_id,
            @RequestParam(value = "nivelhierarquico", required = false) Optional<Integer> nivelhierarquico,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            UnidadeEstrutural unidadeEstrutural = unidadeEstruturalService.actualizarUnidadeEstrutural(
                    id,
                    designacao,
                    unidadeestruturalmae_id,
                    tipounidadeestrutural_id,
                    nivelhierarquico
            );
            return new ResponseEntity<>(new UnidadeEstruturalDTO(unidadeEstrutural, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador da Unidade Estrutural
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarUnidadeEstrutural(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            UnidadeEstrutural unidadeEstrutural = unidadeEstruturalService.eliminarUnidadeEstrutural(
                    id
            );
            return new ResponseEntity<>(new UnidadeEstruturalDTO(unidadeEstrutural, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(value = "/tipo", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterTiposUnidadeEstrutural(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<TipoUnidadeEstruturalDTO> tipoUnidadeEstruturalDTOs = new LinkedList<>();
            unidadeEstruturalService.obterTiposUnidadeEstrutural().stream()
                    .forEach(tipoUE -> tipoUnidadeEstruturalDTOs.add(
                            new TipoUnidadeEstruturalDTO(tipoUE, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(tipoUnidadeEstruturalDTOs, HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do Tipo de Unidade Estrutural
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterTipoUnidadeEstutural(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(
                    new TipoUnidadeEstruturalDTO(
                            unidadeEstruturalService.obterTipoUnidadeEstutural(id),
                            request,
                            ModeloDeRepresentacao.Normal.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param designacao                Designação do Tipo de Unidade Estrutural
     * @param descricao                 Descrição do Tipo de Unidade Estrutural
     * @param nivelHierarquicoMaximoMae Indica o nível hierarquico máximo para o Tipo de Unidade Estrutural Mãe
     * @param request                   HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                  nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo", method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirTipoUnidadeEstrutural(
            @RequestParam(value = "designacao", required = true) String designacao,
            @RequestParam(value = "descricao", required = true) String descricao,
            @RequestParam(value = "nivelHierarquicoMaximoMae", required = true) Integer nivelHierarquicoMaximoMae,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoUnidadeEstrutural tipoUnidadeEstrutural = unidadeEstruturalService
                    .inserirTipoUnidadeEstrutural(
                            designacao,
                            descricao,
                            nivelHierarquicoMaximoMae
                    );
            return new ResponseEntity<>(
                    new TipoUnidadeEstruturalDTO(
                            tipoUnidadeEstrutural,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.CREATED
            );
        };
    }

    /**
     * @param id                        Identificador do Tipo de Unidade Estrutural
     * @param designacao                Designação do Tipo de Unidade Estrutural
     * @param descricao                 Descrição do Tipo de Unidade Estrutural
     * @param nivelHierarquicoMaximoMae Indica o nível hierarquico máximo para o Tipo de Unidade Estrutural Mãe
     * @param request                   HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                  nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarTipoUnidadeEstrutural(
            @PathVariable Long id,
            @RequestParam(value = "designacao", required = false) Optional<String> designacao,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            @RequestParam(value = "nivelHierarquicoMaximoMae", required = false) Optional<Integer> nivelHierarquicoMaximoMae,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoUnidadeEstrutural tipoUnidadeEstrutural = unidadeEstruturalService.actualizarTipoUnidadeEstrutural(
                    id,
                    designacao,
                    descricao,
                    nivelHierarquicoMaximoMae
            );
            return new ResponseEntity<>(
                    new TipoUnidadeEstruturalDTO(
                            tipoUnidadeEstrutural,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param id      Identificador do Tipo de Unidade Estrutural
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarTipoUnidadeEstrutural(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoUnidadeEstrutural tipoUnidadeEstrutural = unidadeEstruturalService.eliminarTipoUnidadeEstrutural(id);
            return new ResponseEntity<>(
                    new TipoUnidadeEstruturalDTO(
                            tipoUnidadeEstrutural,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }


    /**
     * @throws Exception
     */

    /**
     * @param unidadeEstrutural_id Identificador da Unidade Estrutural a que pertencem as instalações
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(value = "/{unidadeestrutural_id}/instalacao", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterInstalacoes(
            @PathVariable( "unidadeestrutural_id" ) Long unidadeEstrutural_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<InstalacaoDTO> instalacaoDTOs = new LinkedList<>();
            unidadeEstruturalService.obterInstalacoes(unidadeEstrutural_id).stream()
                    .forEach(
                            instalacao -> instalacaoDTOs.add(
                                    new InstalacaoDTO(instalacao, request, ModeloDeRepresentacao.Sumario.class)
                            )
                    );
            return new ResponseEntity<>(instalacaoDTOs, HttpStatus.OK);
        };
    }


    /**
     * @param unidadeEstrutural_id Identificador da Unidade Estrutural
     * @param instalacao_id        Identificador da Instalação
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/{unidadeestrutural_id}/instalacao/{instalacao_id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterInstalacao(
            @PathVariable( "unidadeestrutural_id" ) Long unidadeEstrutural_id,
            @PathVariable Long instalacao_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(new InstalacaoDTO(
                    unidadeEstruturalService.obterInstalacao(
                            unidadeEstrutural_id,
                            instalacao_id),
                    request,
                    ModeloDeRepresentacao.Normal.class
            ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param unidadeestrutural_id Identificador da Unidade Estrutural
     * @param designacao           Designação da Instalação
     * @param descricao            Descrição da Instalação
     * @param localizacao          Descrição da Localização da Instalação
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{unidadeestrutural_id}/instalacao", method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirInstalacao(
            @PathVariable Long unidadeestrutural_id,
            @RequestParam(value = "designacao", required = true) String designacao,
            @RequestParam(value = "descricao", required = true) String descricao,
            @RequestParam(value = "localizacao", required = true) String localizacao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Instalacao instalacao = unidadeEstruturalService
                    .inserirInstalacao(
                            unidadeestrutural_id,
                            designacao,
                            descricao,
                            localizacao
                    );
            return new ResponseEntity<>(
                    new InstalacaoDTO(
                            instalacao,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.CREATED
            );
        };
    }

    /**
     * @param instalacao_id                   Identificador da Instalação
     * @param unidadeestrutural_id            Identificador da Unidade Estrutural
     * @param designacao                      Designação da Instalação
     * @param descricao                       Descrição da Instalação
     * @param localizacao                     Descrição da Localização da Instalação
     * @param unidadeestruturalreafectacao_id Identificador da Unidade Estrutural para reafectação desta instalacao
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{unidadeestrutural_id}/instalacao/{instalacao_id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarInstalacao(
            @PathVariable Long unidadeestrutural_id,
            @PathVariable Long instalacao_id,
            @RequestParam(value = "designacao", required = false) Optional<String> designacao,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            @RequestParam(value = "localizacao", required = false) Optional<String> localizacao,
            @RequestParam(value = "unidadeestruturalreafectacao_id", required = false) Optional<Long> unidadeestruturalreafectacao_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Instalacao instalacao = unidadeEstruturalService.actualizarInstalacao(
                    instalacao_id,
                    unidadeestrutural_id,
                    designacao,
                    descricao,
                    localizacao,
                    unidadeestruturalreafectacao_id
            );
            return new ResponseEntity<>(
                    new InstalacaoDTO(
                            instalacao,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param instalacao_id        Identificador da Instalação
     * @param unidadeestrutural_id Identificador da Unidade Estrutural
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Callable que se destina a ser executado pelo SimpleAsyncTaskExecutor (omissão Spring)
     * que entrega um objecto ou colecção de objectos DTO para retornar no Corpo da Resposta
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{unidadeestrutural_id}/instalacao/{instalacao_id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarInstalacao(
            @PathVariable Long instalacao_id,
            @PathVariable Long unidadeestrutural_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Instalacao instalacao = unidadeEstruturalService.eliminarInstalacao(
                instalacao_id,
                    unidadeestrutural_id
            );
            return new ResponseEntity<>(
                    new InstalacaoDTO(
                            instalacao,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }
}
