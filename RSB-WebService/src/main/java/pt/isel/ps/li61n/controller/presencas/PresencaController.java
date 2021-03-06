package pt.isel.ps.li61n.controller.presencas;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.controller.RsbBaseController;
import pt.isel.ps.li61n.controller.dto.PeriodoDTO;
import pt.isel.ps.li61n.controller.dto.ElementoDoPessoalDTO;
import pt.isel.ps.li61n.controller.dto.PresencaDTO;
import pt.isel.ps.li61n.controller.dto.TipoPresencaDTO;
import pt.isel.ps.li61n.controller.error.exception.ErroNaoDeterminadoException;
import pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException;
import pt.isel.ps.li61n.model.entities.Periodo;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.model.entities.TipoPresenca;
import pt.isel.ps.li61n.model.services.IPresencaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * PresencasController - Atendimento a pedidos da secção nuclear de gestão de Presenças
 * Created on 13/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping(value = "/presenca")
@Validated
public class PresencaController extends RsbBaseController<Presenca> {

    /**
     * Instância do Serviço
     */
    @Autowired
    IPresencaService presencaService;

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     * @param datainicio           Data de Inicio
     * @param datafim              Data de Fim
     * @param periodo_id           Identificador do Periodo
     * @param turno_id             Identificadro do Turno (Opcional)
     * @param instalacao_id        Identificador da Instalação (Opcional)
     * @param postofuncional_id    Identificador do Posto Funcional (Opcional)
     * @param tipopresenca_id      Identificador do Tipo de Presença
     * @param elementodopessoal_id Identificador do Elemento do pessoal a que diz respeito a presença
     * @param elementoreforco_id   Identificador do Elemento que reforça a presença por motivos de ausencia
     * @param elementoreforcado_id Identificador do Elemento que foi reforçado por motivos de ausencia
     * @return Lista de ElementoDoPessoal global ou filtrada através dos parametros acima designados.
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterPresencas(
            @RequestParam(value = "datainicio", required = false) Optional<Date> datainicio,
            @RequestParam(value = "datafim", required = false) Optional<Date> datafim,
            @RequestParam(value = "periodo_id", required = false) Optional<Long> periodo_id,
            @RequestParam(value = "turno_id", required = false) Optional<Long> turno_id,
            @RequestParam(value = "instalacao_id", required = false) Optional<Long> instalacao_id,
            @RequestParam(value = "postofuncional_id", required = false) Optional<Long> postofuncional_id,
            @RequestParam(value = "tipopresenca_id", required = false) Optional<String> tipopresenca_id,
            @RequestParam(value = "elementodopessoal_id", required = false) Optional<Long> elementodopessoal_id,
            @RequestParam(value = "elementoreforco_id", required = false) Optional<Long> elementoreforco_id,
            @RequestParam(value = "elementoreforcado_id", required = false) Optional<Long> elementoreforcado_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            final List<PresencaDTO> presencasDTO = new LinkedList<>();
            presencaService.obterPresencas(
                    datainicio,
                    datafim,
                    periodo_id,
                    turno_id,
                    instalacao_id,
                    postofuncional_id,
                    tipopresenca_id,
                    elementodopessoal_id,
                    elementoreforco_id,
                    elementoreforcado_id)
                    .stream().forEach(presenca -> {
                presencasDTO.add(
                        new PresencaDTO(presenca, request, ModeloDeRepresentacao.Sumario.class));
            });
            if (presencasDTO.size() == 0)
                throw new NaoEncontradoException("Não existem elementos para os critérios introduzidos!");
            return new ResponseEntity<>(presencasDTO, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador da Presença
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Representação do elemento na forma de um DTO facilmente serializavel em Json.
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterPresenca(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PresencaDTO presencaDTO = new PresencaDTO(
                    presencaService.obterPresenca(id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(presencaDTO, HttpStatus.OK);
        };
    }


    /**
     * @param data                 Data da presença
     * @param numhoras             Numero de horas em presença
     * @param periodo_id           Identificador do Periodo
     * @param turno_id             Identificador do Turno (Opcional)
     * @param instalacao_id        Identificador da Instalação (Opcional)
     * @param postofuncional_id    Identificador do Posto Funcional (Opcional)
     * @param tipopresenca_id      Identificador do Tipo de Presença
     * @param elementodopessoal_id Identificador do Elemento do pessoal a que diz respeito a presença
     * @param elementoreforco_id   (Opcional) Identificador do Elemento que reforça a presença por motivos de ausencia
     * @param elementoreforcado_id (Opcional) Identificador do Elemento que foi reforçado por motivos de ausencia
     * @param request              HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                             nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirPresenca(
            @RequestParam(value = "data", required = true) Date data,
            @RequestParam(value = "horainicio", required = true) Time horainicio,
            @RequestParam(value = "numhoras", required = true) Float numhoras,
            @RequestParam(value = "periodo_id", required = true) Long periodo_id,
            @RequestParam(value = "turno_id", required = true) Long turno_id,
            @RequestParam(value = "instalacao_id", required = true) Long instalacao_id,
            @RequestParam(value = "postofuncional_id", required = true) Long postofuncional_id,
            @RequestParam(value = "tipopresenca_id", required = true) String tipopresenca_id,
            @RequestParam(value = "elementodopessoal_id", required = true) Long elementodopessoal_id,
            @RequestParam(value = "elementoreforco_id", required = true) Optional<Long> elementoreforco_id,
            @RequestParam(value = "elementoreforcado_id", required = true) Optional<Long> elementoreforcado_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Presenca presenca = presencaService
                    .inserirPresenca(
                            data,
                            horainicio,
                            numhoras,
                            periodo_id,
                            turno_id,
                            instalacao_id,
                            postofuncional_id,
                            tipopresenca_id,
                            elementodopessoal_id,
                            elementoreforco_id,
                            elementoreforcado_id
                    );
            return new ResponseEntity<>(new PresencaDTO(presenca, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
    }


    /**
     * @param id                   Indentificador do registo de Presença
     * @param data                 Data da presença (Opcional)
     * @param numhoras             Numero de horas em presença (Opcional)
     * @param periodo_id           Identificador do Periodo (Opcional)
     * @param turno_id             Identificador do Turno (Opcional)
     * @param instalacao_id        Identificador da Instalação (Opcional)
     * @param postofuncional_id    Identificador do Posto Funcional (Opcional)
     * @param tipopresenca_id      Identificador do Tipo de Presença
     * @param elementodopessoal_id Identificador do Elemento do pessoal a que diz respeito a presença
     * @param elementoreforco_id   (Opcional) Identificador do Elemento que reforça a presença por motivos de ausencia
     * @param elementoreforcado_id (Opcional) Identificador do Elemento que foi reforçado por motivos de ausencia
     * @param request              HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                             nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarPresenca(
            @PathVariable Long id,
            @RequestParam(value = "data", required = true) Optional<Date> data,
            @RequestParam(value = "numhoras", required = true) Optional<Float> numhoras,
            @RequestParam(value = "periodo_id", required = true) Optional<Long> periodo_id,
            @RequestParam(value = "turno_id", required = true) Optional<Long> turno_id,
            @RequestParam(value = "instalacao_id", required = true) Optional<Long> instalacao_id,
            @RequestParam(value = "postofuncional_id", required = true) Optional<Long> postofuncional_id,
            @RequestParam(value = "tipopresenca_id", required = true) Optional<String> tipopresenca_id,
            @RequestParam(value = "elementodopessoal_id", required = true) Optional<Long> elementodopessoal_id,
            @RequestParam(value = "elementoreforco_id", required = true) Optional<Long> elementoreforco_id,
            @RequestParam(value = "elementoreforcado_id", required = true) Optional<Long> elementoreforcado_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Presenca presenca = presencaService.actualizarPresenca(
                    id,
                    data,
                    numhoras,
                    periodo_id,
                    turno_id,
                    instalacao_id,
                    postofuncional_id,
                    tipopresenca_id,
                    elementodopessoal_id,
                    elementoreforco_id,
                    elementoreforcado_id
            );
            return new ResponseEntity<>(new PresencaDTO(presenca, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do elemento.
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarPresenca(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Presenca presenca = presencaService.eliminarPresenca(
                    id
            );
            return new ResponseEntity<>(new PresencaDTO(presenca, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(value = "/tipo", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterTiposPresenca(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<TipoPresencaDTO> tiposPresencaDTO = new LinkedList<>();
            presencaService.obterTiposPresenca().stream()
                    .forEach(tipoPresenca -> tiposPresencaDTO.add(new TipoPresencaDTO(tipoPresenca, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(tiposPresencaDTO, HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do Tipo de Presença.
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterTipoPresenca(
            @PathVariable String id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(new TipoPresencaDTO(presencaService.obterTipoPresenca(id), request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }

    /**
     * @param ausencia              Sinaliza o tipo de presença como sendo de ausencia
     * @param reforco               Sinaliza o tipo de presença como sendo de reforço
     * @param tipopresencaemreforco identifica o tipo de presença que uma ausencia deve gerar em Reforço
     * @param abreviatura           Abreviatura
     * @param descricao             Descrição
     * @param request               HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                              nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo", method = RequestMethod.POST) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirTipoPresenca(
            @RequestParam(value = "ausencia", required = true) Boolean ausencia,
            @RequestParam(value = "reforco", required = true) Boolean reforco,
            @RequestParam(value = "tipopresencaemreforco_id", required = true) String tipopresencaemreforco,
            @RequestParam(value = "abreviatura", required = true) String abreviatura,
            @RequestParam(value = "descricao", required = true) String descricao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoPresenca tipoPresenca = presencaService
                    .inserirTipoPresenca(
                            ausencia,
                            reforco,
                            abreviatura,
                            descricao
                    );
            return new ResponseEntity<>(new TipoPresencaDTO(tipoPresenca, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
    }

    /**
     * @param id                    Identificador da Categoria
     * @param ausencia              Sinaliza o tipo de presença como sendo de ausencia
     * @param reforco               Sinaliza o tipo de presença como sendo de reforço
     * @param tipopresencaemreforco identifica o tipo de presença que uma ausencia deve gerar em Reforço
     * @param abreviatura           Abreviatura
     * @param descricao             Descrição
     * @param request               HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                              nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.PUT) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarTipoPresenca(
            @PathVariable String id,
            @RequestParam(value = "ausencia", required = false) Optional<Boolean> ausencia,
            @RequestParam(value = "reforco", required = false) Optional<Boolean> reforco,
            @RequestParam(value = "tipopresencaemreforco_id", required = false) Optional<String> tipopresencaemreforco,
            @RequestParam(value = "abreviatura", required = false) Optional<String> abreviatura,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoPresenca tipoPresenca = presencaService.actualizarTipoPresenca(
                    id,
                    ausencia,
                    reforco,
                    abreviatura,
                    descricao
            );
            return new ResponseEntity<>(new TipoPresencaDTO(tipoPresenca, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador do Tipo de Presença
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/tipo/{id}", method = RequestMethod.DELETE) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarTipoPresenca(
            @PathVariable String id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TipoPresenca tipoPresenca = presencaService.eliminarTipoPresenca(
                    id
            );
            return new ResponseEntity<>(new TipoPresencaDTO(tipoPresenca, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.OK);
        };
    }


    /**
     * @throws Exception
     */

    /**
     * @param datainicio Data inicio de periodos
     * @param datafim    Data de fim de periodos
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(value = "/periodo", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterPeriodos(
            @RequestParam(value = "datainicio", required = false) Optional<Date> datainicio,
            @RequestParam(value = "datafim", required = false) Optional<Date> datafim,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<PeriodoDTO> periodosDTO = new LinkedList<>();
            presencaService.obterPeriodos(datainicio, datafim).stream()
                    .forEach(periodo -> periodosDTO.add(new PeriodoDTO(periodo, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(periodosDTO, HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do Periodo
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/periodo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterPeriodo(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(new PeriodoDTO(presencaService.obterPeriodo(id), request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }

    /**
     * @param datainicio Data inicio de periodo
     * @param datafim    Data de fim de periodo
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/periodo", method = RequestMethod.POST) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirPeriodo(
            @RequestParam(value = "datainicio", required = true) Date datainicio,
            @RequestParam(value = "datafim", required = true) Date datafim,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Periodo periodo = presencaService
                    .inserirPeriodo(
                            datainicio,
                            datafim
                    );
            return new ResponseEntity<>(new PeriodoDTO(periodo, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
    }

    /**
     * @param id         Identificador do Periodo
     * @param datainicio Data inicio de periodo
     * @param datafim    Data de fim de periodo
     * @param request    HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                   nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/periodo/{id}", method = RequestMethod.PUT) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarPeriodo(
            @PathVariable Long id,
            @RequestParam(value = "datainicio", required = false) Optional<Date> datainicio,
            @RequestParam(value = "datafim", required = false) Optional<Date> datafim,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Periodo periodo = presencaService.actualizarPeriodo(
                    id,
                    datainicio,
                    datafim
            );
            return new ResponseEntity<>(new PeriodoDTO(periodo, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador do Tipo de Presença
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/periodo/{id}", method = RequestMethod.DELETE) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarPeriodo(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Periodo periodo = presencaService.eliminarPeriodo(id);
            return new ResponseEntity<>(new PeriodoDTO(periodo, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.OK);
        };
    }

    /**
     * Actualiza as presenças de um elemento para um determinado periodo.
     *
     * @param elementodopessoal_id Identificador do Elemento do Pessoal
     * @param periodo_id           Identificador do Periodo
     * @param request              HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                             nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/popularperiodo", method = RequestMethod.PUT) // Este Método atende ao verbo HTTP PUT
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarPresencasDeElemento(
            @Valid @RequestParam(value = "elementodopessoal_id", required = true) Long elementodopessoal_id,
            @Valid @RequestParam(value = "periodo_id", required = true) Long periodo_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Collection<Presenca> presencas = presencaService.obterPresencas(
                    Optional.empty(), Optional.empty(),
                    Optional.of(periodo_id),
                    Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                    Optional.of(elementodopessoal_id),
                    Optional.empty(), Optional.empty());
            presencas.stream().forEach(presenca -> {
                try {
                    presencaService.eliminarPresenca(presenca.getId());
                } catch (Exception e) {
                    logger.debug("Erro não determinado: " + e);
                    throw new ErroNaoDeterminadoException(e.getMessage());
                }
            });
            List<PresencaDTO> presencaDTOs = new LinkedList<>();
            presencaService.popularPresencas(periodo_id, elementodopessoal_id).stream().forEach(
                    presenca -> presencaDTOs.add(new PresencaDTO(presenca, request, ModeloDeRepresentacao.Sumario.class))
            );
            return new ResponseEntity<>(presencaDTOs, HttpStatus.OK);
        };
    }

    /**
     * Obter elementos para cedencia de troca, ordenados por ordem de favorabilidade de solução
     *
     * @param id Identificador da Presença, em relação à qual queremos obter elementos para troca
     * @return Lista de elementos disponíveis para troca, ordenados por favorabilidade de solução
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}/pessoalreforco", method = RequestMethod.GET) // Este Método atende ao verbo HTTP PUT
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterElementosDoPessoalParaReforco(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Presenca presenca = presencaService.obterPresenca(id);
            Collection<ElementoDoPessoalDTO> elementoDoPessoalDTOs = new LinkedList<>();
            presencaService.obterElementosDoPessoalParaReforco(presenca.getId()).stream().forEach(
                    elemento -> elementoDoPessoalDTOs.add(new ElementoDoPessoalDTO(elemento, request, ModeloDeRepresentacao.Sumario.class))
            );
            return new ResponseEntity<>(elementoDoPessoalDTOs, HttpStatus.OK);
        };
    }

    /**
     * Obter elementos para cedencia de troca, ordenados por ordem de favorabilidade de solução
     *
     * @param id          Identificador da Presença, em relação à qual queremos obter elementos para troca
     * @param elementodereforco_id Identificador do elemento de reforço
     * @return Lista de elementos disponíveis para troca, ordenados por favorabilidade de solução
     * @throws Exception Excepções com relevancia em termos de lógica da aplicação:
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ConflictoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException},
     *                   {@link pt.isel.ps.li61n.controller.error.exception.ErroNaoDeterminadoException},
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}/realizarreforco", method = RequestMethod.POST)
    // Este Método atende ao verbo HTTP PUT
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> realizarReforco(
            @PathVariable Long id,
            @Valid @RequestParam(value = "elementodereforco_id", required = true) Long elementodereforco_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PresencaDTO presencaDTO = new PresencaDTO(
                    presencaService.realizarReforco(id, elementodereforco_id),
                    request,
                    ModeloDeRepresentacao.Verboso.class);
            return new ResponseEntity<>(presencaDTO, HttpStatus.CREATED);
        };
    }

}
