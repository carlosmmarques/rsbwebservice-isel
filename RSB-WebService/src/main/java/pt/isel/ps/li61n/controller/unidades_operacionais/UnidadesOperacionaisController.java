package pt.isel.ps.li61n.controller.unidades_operacionais;

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
import pt.isel.ps.li61n.controller.dto.GuarnicaoDTO;
import pt.isel.ps.li61n.controller.dto.TipoUnidadeOperacionalDTO;
import pt.isel.ps.li61n.controller.dto.UnidadeOperacionalDTO;
import pt.isel.ps.li61n.controller.error.exception.ErroNaoDeterminadoException;
import pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException;
import pt.isel.ps.li61n.model.entities.Guarnicao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;
import pt.isel.ps.li61n.model.services.IUnidadeOperacionalService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * UnidadesOperacionaisController - Description
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping(value = "/unidadeoperacional")
public class UnidadesOperacionaisController extends RsbBaseController<UnidadeOperacional> {
    /**
     * Instância do Serviço
     */
    @Autowired
    IUnidadeOperacionalService unidadeOperacionalService;

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Lista de Unidades Operacionais global
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterUnidadesOperacionais(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            final List<UnidadeOperacionalDTO> unidadeOperacionalDTOs = new LinkedList<>();
            unidadeOperacionalService.obterUnidadesOperacionais().stream().forEach(uo -> {
                unidadeOperacionalDTOs.add(
                        new UnidadeOperacionalDTO(uo, request, ModeloDeRepresentacao.Sumario.class));
            });
            if (unidadeOperacionalDTOs.size() == 0)
                throw new NaoEncontradoException("Não existem elementos para os critérios introduzidos!");
            return new ResponseEntity<>(unidadeOperacionalDTOs, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador da Unidade Operacional
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterUnidadeOperacional(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            UnidadeOperacionalDTO unidadeOperacionalDTO = new UnidadeOperacionalDTO(
                    unidadeOperacionalService.obterUnidadeOperacional(id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(unidadeOperacionalDTO, HttpStatus.OK);
        };
    }


    /**
     * @param designacao                Designação do Unidade Operacional
     * @param tipounidadeoperacional_id Tipo da Unidade Operacional
     * @param operacional               Indica se a Unidade Operacional está em condições de operacionalidade
     * @param instalacao_id             Instalação a que a unidade está atribuida
     * @param request                   HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                  nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
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
            @RequestParam(value = "tipounidadeoperacional_id", required = true) Long tipounidadeoperacional_id,
            @RequestParam(value = "operacional", required = true) Boolean operacional,
            @RequestParam(value = "instalacao_id", required = true) Long instalacao_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            UnidadeOperacional unidadeOperacional = unidadeOperacionalService.inserirUnidadeOperacional(
                    designacao,
                    tipounidadeoperacional_id,
                    operacional,
                    instalacao_id
            );
            return new ResponseEntity<>(new UnidadeOperacionalDTO(unidadeOperacional, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
    }


    /**
     * @param id                        Identificador da Unidade Esturtural a actualizar
     * @param designacao                Designação do Unidade Operacional
     * @param tipounidadeoperacional_id Tipo da Unidade Operacional
     * @param operacional               Indica se a Unidade Operacional está em condições de operacionalidade
     * @param instalacao_id             Instalação a que a unidade está atribuida
     * @param request                   HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                  nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarUnidadeOperacional(
            @PathVariable Long id,
            @RequestParam(value = "designacao", required = false) Optional<String> designacao,
            @RequestParam(value = "tipounidadeoperacional_id", required = false) Optional<Long> tipounidadeoperacional_id,
            @RequestParam(value = "operacional", required = false) Optional<Boolean> operacional,
            @RequestParam(value = "instalacao_id", required = false) Optional<Long> instalacao_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            UnidadeOperacional unidadeOperacional = unidadeOperacionalService.actualizarUnidadeOperacional(
                    id,
                    designacao,
                    tipounidadeoperacional_id,
                    operacional,
                    instalacao_id
            );
            return new ResponseEntity<>(new UnidadeOperacionalDTO(unidadeOperacional, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador da Unidade Operacional a eliminar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarUnidadeOperacional(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            UnidadeOperacional unidadeOperacional = unidadeOperacionalService.eliminarUnidadeOperacional(
                    id
            );
            return new ResponseEntity<>(new UnidadeOperacionalDTO(unidadeOperacional, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(value = "/tipo", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterTiposUnidadeOperacional(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<TipoUnidadeOperacionalDTO> tipoUnidadeOperacionalDTOs = new LinkedList<>();
            unidadeOperacionalService.obterTiposUnidadeOperacional().stream()
                    .forEach(tipoUO -> tipoUnidadeOperacionalDTOs.add(
                            new TipoUnidadeOperacionalDTO(tipoUO, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(tipoUnidadeOperacionalDTOs, HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do Tipo de Unidade Operacional
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterTipoUnidadeOperacional(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(
                    new TipoUnidadeOperacionalDTO(
                            unidadeOperacionalService.obterTipoUnidadeOperacional(id),
                            request,
                            ModeloDeRepresentacao.Normal.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param designacao Designação do Tipo de Unidade Operacional
     * @param descricao  Descrição do Tipo de Unidade Operacional
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo", method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirTipoUnidadeOperacional(
            @RequestParam(value = "designacao", required = true) String designacao,
            @RequestParam(value = "descricao", required = true) String descricao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoUnidadeOperacional tipoUnidadeOperacional = unidadeOperacionalService
                    .inserirTipoUnidadeOperacional(
                            designacao,
                            descricao
                    );
            return new ResponseEntity<>(
                    new TipoUnidadeOperacionalDTO(
                            tipoUnidadeOperacional,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.CREATED
            );
        };
    }

    /**
     * @param id         Identificador do Tipo de Unidade Operacional
     * @param designacao Designação do Tipo de Unidade Operacional
     * @param descricao  Descrição do Tipo de Unidade Operacional
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarTipoUnidadeOperacional(
            @PathVariable Long id,
            @RequestParam(value = "designacao", required = false) Optional<String> designacao,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoUnidadeOperacional tipoUnidadeOperacional = unidadeOperacionalService.actualizarTipoUnidadeOperacional(
                    id,
                    designacao,
                    descricao
            );
            return new ResponseEntity<>(
                    new TipoUnidadeOperacionalDTO(
                            tipoUnidadeOperacional,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param id      Identificador do Tipo de Unidade Operacional
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarTipoUnidadeOperacional(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoUnidadeOperacional tipoUnidadeOperacional = unidadeOperacionalService.eliminarTipoUnidadeOperacional(id);
            return new ResponseEntity<>(
                    new TipoUnidadeOperacionalDTO(
                            tipoUnidadeOperacional,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }


    /**
     * @param unidadeoperacional_id Identificador da Unidade Operacional a que pertencem as instalações
     * @param request               HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                              nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(value = "/{unidadeoperacional_id}/guarnicao", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterGuarnicoesDeUnidadeOperacional(
            @PathVariable Long unidadeoperacional_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<GuarnicaoDTO> guarnicaoDTOs = new LinkedList<>();
            unidadeOperacionalService.obterGuarnicoesDeUnidadeOperacional(unidadeoperacional_id).stream()
                    .forEach(
                            guarnicao -> guarnicaoDTOs.add(
                                    new GuarnicaoDTO(guarnicao, request, ModeloDeRepresentacao.Sumario.class)
                            )
                    );
            return new ResponseEntity<>(guarnicaoDTOs, HttpStatus.OK);
        };
    }


    /**
     * @param unidadeoperacional_id Identificador da Unidade Operacional
     * @param guarnicao_id          Identificador da Guarnicao
     * @param request               HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                              nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/{unidadeoperacional_id}/guarnicao/{guarnicao_id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterGuarnicaoDeUnidadeOperacional(
            @PathVariable Long unidadeoperacional_id,
            @PathVariable Long guarnicao_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(new GuarnicaoDTO(
                    unidadeOperacionalService.obterGuarnicaoDeUnidadeOperacional(
                            unidadeoperacional_id,
                            guarnicao_id),
                    request,
                    ModeloDeRepresentacao.Normal.class
            ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param unidadeoperacional_id          Identificador da Unidade Operacional
     * @param responsabilidadeOperacional_id Identificador da ResponsabilidadeOperacional
     * @param qtdminima                      Quantidade Mínima de meios
     * @param qtdmaxima                      Quantidade Máxima de meios
     * @param request                        HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                       nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{unidadeoperacional_id}/guarnicao", method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirGuarnicao(
            @PathVariable Long unidadeoperacional_id,
            @RequestParam(value = "responsabilidadeOperacional_id", required = true) Long responsabilidadeOperacional_id,
            @RequestParam(value = "qtdminima", required = true) Integer qtdminima,
            @RequestParam(value = "qtdmaxima", required = true) Integer qtdmaxima,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Guarnicao guarnicao = unidadeOperacionalService
                    .inserirGuarnicao(
                            unidadeoperacional_id,
                            responsabilidadeOperacional_id,
                            qtdminima,
                            qtdmaxima
                    );
            return new ResponseEntity<>(
                    new GuarnicaoDTO(
                            guarnicao,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.CREATED
            );
        };
    }

    /**
     * @param guarnicao_id                   Identificador da Guarni;#ao
     * @param unidadeOperacional_id          Identificador da Unidade Operacional
     * @param responsabilidadeOperacional_id Identificador da ResponsabilidadeOperacional
     * @param qtdminima                      Quantidade Mínima de meios
     * @param qtdmaxima                      Quantidade Máxima de meios
     * @param request                        HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                       nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{unidadeoperacional_id}/guarnicao/{guarnicao_id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarGuarnicao(
            @PathVariable Long unidadeOperacional_id,
            @PathVariable Long guarnicao_id,
            @RequestParam(value = "responsabilidadeOperacional_id", required = false) Optional<Long> responsabilidadeOperacional_id,
            @RequestParam(value = "qtdminima", required = false) Optional<Integer> qtdminima,
            @RequestParam(value = "qtdmaxima", required = false) Optional<Integer> qtdmaxima,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Guarnicao guarnicao = unidadeOperacionalService.actualizarGuarnicao(
                    guarnicao_id,
                    unidadeOperacional_id,
                    responsabilidadeOperacional_id,
                    qtdminima,
                    qtdmaxima
            );
            return new ResponseEntity<>(
                    new GuarnicaoDTO(
                            guarnicao,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param guarnicao_id          Identificador da Guarnicao
     * @param unidadeoperacional_id
     * @param request               HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                              nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     * {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     * {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     * {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{unidadeoperacional_id}/guarnicao/{guarnicao_id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarGuarnicao(
            @PathVariable Long unidadeoperacional_id,
            @PathVariable Long guarnicao_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Guarnicao guarnicao = unidadeOperacionalService.eliminarGuarnicao(
                    guarnicao_id,
                    unidadeoperacional_id
            );
            return new ResponseEntity<>(
                    new GuarnicaoDTO(
                            guarnicao,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }
}
