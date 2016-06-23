package pt.isel.ps.li61n.controller.turnos;

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
import pt.isel.ps.li61n.controller.dto.AlgoritmoCalculoTurnoDTO;
import pt.isel.ps.li61n.controller.dto.PeriodoCicloTurnoDTO;
import pt.isel.ps.li61n.controller.dto.TurnoDTO;
import pt.isel.ps.li61n.controller.error.NaoEncontradoException;
import pt.isel.ps.li61n.model.entities.AlgoritmoCalculoTurno;
import pt.isel.ps.li61n.model.entities.PeriodoCicloTurno;
import pt.isel.ps.li61n.model.entities.Turno;
import pt.isel.ps.li61n.model.services.ITurnoService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
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
@RequestMapping(value = "/turno")
public class TurnoController extends RsbBaseController<Turno> {

    /**
     * Instância do Serviço
     */
    @Autowired
    ITurnoService turnoService;

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     * @return Lista de Turnos global.
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterTurnos(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            final List<TurnoDTO> turnosDTO = new LinkedList<>();
            turnoService.obterturnos().stream().forEach(turno -> {
                turnosDTO.add(
                        new TurnoDTO(turno, request, ModeloDeRepresentacao.Sumario.class));
            });
            if (turnosDTO.size() == 0)
                throw new NaoEncontradoException("Não existem elementos para os critérios introduzidos!");
            return new ResponseEntity<>(turnosDTO, HttpStatus.OK);
        };
    }

    /**
     * @param id      Identificador da Turno
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> obterTurno(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            TurnoDTO turnoDTO = new TurnoDTO(
                    turnoService.obterTurno(id),
                    request,
                    ModeloDeRepresentacao.Normal.class);
            return new ResponseEntity<>(turnoDTO, HttpStatus.OK);
        };
    }


    /**
     * @param designacao               Designação do Turno
     * @param dtiniciociclo            Data de incio do Turno
     * @param hrinicociclo             Hora de incio do Turno
     * @param algoritmocalculoturno_id Identificado do Algoritmos de Calculo de Turno
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirTurno(
            @RequestParam(value = "designacao", required = true) String designacao,
            @RequestParam(value = "dtiniciociclo", required = true) Date dtiniciociclo,
            @RequestParam(value = "hriniciociclo", required = true) Time hrinicociclo,
            @RequestParam(value = "algoritmocalculoturno_id", required = true) Long algoritmocalculoturno_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Turno turno = turnoService
                    .inserirTurno(
                            designacao,
                            dtiniciociclo,
                            hrinicociclo,
                            algoritmocalculoturno_id
                    );
            return new ResponseEntity<>(new TurnoDTO(turno, request, ModeloDeRepresentacao.Verboso.class), HttpStatus.CREATED);
        };
    }


    /**
     * @param id                       Indentificador do registo de Presença
     * @param designacao               Designação do Turno
     * @param dtiniciociclo            Data de incio do Turno
     * @param hrinicociclo             Hora de incio do Turno
     * @param algoritmocalculoturno_id Identificado do Algoritmos de Calculo de Turno
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarTurno(
            @PathVariable Long id,
            @RequestParam(value = "designacao", required = false) Optional<String> designacao,
            @RequestParam(value = "dtiniciociclo", required = false) Optional<Date> dtiniciociclo,
            @RequestParam(value = "hriniciociclo", required = false) Optional<Time> hrinicociclo,
            @RequestParam(value = "algoritmocalculoturno_id", required = false) Optional<Long> algoritmocalculoturno_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Turno turno = turnoService.actualizarTurno(
                    id,
                    designacao,
                    dtiniciociclo,
                    hrinicociclo,
                    algoritmocalculoturno_id
            );
            return new ResponseEntity<>(new TurnoDTO(turno, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do elemento.
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> EliminarTurno(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            Turno turno = turnoService.eliminarTurno(
                    id
            );
            return new ResponseEntity<>(new TurnoDTO(turno, request, ModeloDeRepresentacao.Normal.class), HttpStatus.OK);
        };
    }


    /**
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(value = "/algoritmocalculoturno", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterAlgoritmosCalculoTurno(
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<AlgoritmoCalculoTurnoDTO> algoritmosCalculoTurnoDTO = new LinkedList<>();
            turnoService.obterAlgoritmosCalculoTurno().stream()
                    .forEach(algoritmo -> algoritmosCalculoTurnoDTO.add(
                            new AlgoritmoCalculoTurnoDTO(algoritmo, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(algoritmosCalculoTurnoDTO, HttpStatus.OK);
        };
    }


    /**
     * @param id      Identificador do Algoritmo de Calculo de Turno.
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/algoritmocalculoturno/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterAlgoritmoCalculoTurno(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(
                    new AlgoritmoCalculoTurnoDTO(
                            turnoService.obterAlgoritmoCalculoTurno(id),
                            request,
                            ModeloDeRepresentacao.Normal.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param designacao        Designação do Algoritmo de Cálculo de Turno
     * @param descricao         Descrição do Algoritmo de Cálculo de Turno
     * @param servicopermanente Indica se o Algortimo atende ao critério de manutenção de serviço permanente
     * @param request           HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                          nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/algoritmocalculoturno", method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirAlgoritmoCalculoTurno(
            @RequestParam(value = "designacao", required = true) String designacao,
            @RequestParam(value = "descricao", required = true) String descricao,
            @RequestParam(value = "servicopermanente", required = true) Boolean servicopermanente,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            AlgoritmoCalculoTurno algoritmoCalculoTurno = turnoService
                    .inserirAlgoritmoCalculoTurno(
                            designacao,
                            descricao,
                            servicopermanente
                    );
            return new ResponseEntity<>(
                    new AlgoritmoCalculoTurnoDTO(
                            algoritmoCalculoTurno,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.CREATED
            );
        };
    }

    /**
     * @param id                Identificador do Algoritmo de Calculo de Turno
     * @param designacao        Designação do Algoritmo de Cálculo de Turno
     * @param descricao         Descrição do Algoritmo de Cálculo de Turno
     * @param servicopermanente Indica se o Algortimo atende ao critério de manutenção de serviço permanente
     * @param request           HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                          nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/algoritmocalculoturno/{id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarAlgoritmoCalculoTurno(
            @PathVariable Long id,
            @RequestParam(value = "designacao", required = false) Optional<String> designacao,
            @RequestParam(value = "descricao", required = false) Optional<String> descricao,
            @RequestParam(value = "servicopermanente", required = false) Optional<Boolean> servicopermanente,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            AlgoritmoCalculoTurno algoritmoCalculoTurno = turnoService.actualizarAlgoritmoCalculoTurno(
                    id,
                    designacao,
                    descricao,
                    servicopermanente
            );
            return new ResponseEntity<>(
                    new AlgoritmoCalculoTurnoDTO(
                            algoritmoCalculoTurno,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param id      Identificador do Algoritmo de Calculo de Turno
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/algoritmocalculoturno/{id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarTipoPresenca(
            @PathVariable Long id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            AlgoritmoCalculoTurno algoritmoCalculoTurno = turnoService.eliminarAlgoritmoCalculoTurno(id);
            return new ResponseEntity<>(
                    new AlgoritmoCalculoTurnoDTO(
                            algoritmoCalculoTurno,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.CREATED
            );
        };
    }


    /**
     * @throws Exception
     */

    /**
     * @param algoritmocalculoturno_id Identificador do Algoritmo de Calculo de Turno
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(ModeloDeRepresentacao.Sumario.class)
    @RequestMapping(value = "/algoritmocalculoturno/{algoritmocalculoturno_id}/ciclo", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterPeriodosCicloTurno(
            @PathVariable Long algoritmocalculoturno_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            List<PeriodoCicloTurnoDTO> periodosCicloTurnoDTO = new LinkedList<>();
            turnoService.obterPeriodosCicloTurno(algoritmocalculoturno_id).stream()
                    .forEach(periodo -> periodosCicloTurnoDTO.add(new PeriodoCicloTurnoDTO(periodo, request, ModeloDeRepresentacao.Sumario.class)));
            return new ResponseEntity<>(periodosCicloTurnoDTO, HttpStatus.OK);
        };
    }


    /**
     * @param algoritmocalculoturno_id Identificador do Algoritmo de Calculo de Turno
     * @param ciclo_id                 Identificador do Periodo do Ciclo do Turno
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     * @throws Exception
     */
    @JsonView(ModeloDeRepresentacao.Normal.class)
    @RequestMapping(value = "/algoritmocalculoturno/{algoritmocalculoturno_id}/ciclo/{ciclo_id}", method = RequestMethod.GET)
    @ResponseBody
    public Callable<?> obterPeriodoCicloTurno(
            @PathVariable Long algoritmocalculoturno_id,
            @PathVariable Integer ciclo_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            return new ResponseEntity<>(new PeriodoCicloTurnoDTO(
                    turnoService.obterPeriodoCicloTurno(
                            algoritmocalculoturno_id,
                            ciclo_id),
                    request,
                    ModeloDeRepresentacao.Normal.class
            ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param periodocicloturno_id     A ordem do periodo do ciclo para o respectivo algoritmo de calculo de turno
     * @param algoritmocalculoturno_id Identificador do algoritmo de calculo de turno
     * @param numhoras                 Numero de horas do periodo
     * @param periododescanso          Informa se o periodo de ciclo é de descanso
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/algoritmocalculoturno/{algoritmocalculoturno_id}/ciclo/{periodocicloturno_id}", method = RequestMethod.POST)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> inserirPeriodo(
            @PathVariable Long algoritmocalculoturno_id,
            @PathVariable Integer periodocicloturno_id,
            @RequestParam(value = "numhoras", required = true) Float numhoras,
            @RequestParam(value = "periododescanso", required = true) Boolean periododescanso,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PeriodoCicloTurno periodoCicloTurno = turnoService
                    .inserirPeriodoCicloTurno(
                            periodocicloturno_id,
                            algoritmocalculoturno_id,
                            numhoras,
                            periododescanso
                    );
            return new ResponseEntity<>(
                    new PeriodoCicloTurnoDTO(
                            periodoCicloTurno,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.CREATED
            );
        };
    }

    /**
     * @param periodocicloturno_id     A ordem do periodo do ciclo para o respectivo algoritmo de calculo de turno
     * @param algoritmocalculoturno_id Identificador do algoritmo de calculo de turno
     * @param numhoras                 Numero de horas do periodo
     * @param periododescanso          Informa se o periodo de ciclo é de descanso
     * @param request                  HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                                 nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/algoritmocalculoturno/{algoritmocalculoturno_id}/ciclo/{periodocicloturno_id}", method = RequestMethod.PUT)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> actualizarPeriodo(
            @PathVariable Long algoritmocalculoturno_id,
            @PathVariable Integer periodocicloturno_id,
            @RequestParam(value = "numhoras", required = false) Optional<Float> numhoras,
            @RequestParam(value = "periododescanso", required = false) Optional<Boolean> periododescanso,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PeriodoCicloTurno periodoCicloTurno = turnoService.actualizarPeriodoCicloTurno(
                periodocicloturno_id,
                    algoritmocalculoturno_id,
                    numhoras,
                    periododescanso
            );
            return new ResponseEntity<>(
                    new PeriodoCicloTurnoDTO(
                            periodoCicloTurno,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.OK
            );
        };
    }

    /**
     * @param periodocicloturno_id     A ordem do periodo do ciclo para o respectivo algoritmo de calculo de turno
     * @param algoritmocalculoturno_id Identificador do algoritmo de calculo de turno
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Wrapper Spring para a resposta, código HTTP e cabeçalhos HTTP
     */
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    @RequestMapping(value = "/algoritmocalculoturno/{algoritmocalculoturno_id}/ciclo/{periodocicloturno_id}", method = RequestMethod.DELETE)
    @ResponseBody //Retorno do método no corpo da resposta
    public Callable<?> eliminarPeriodo(
            @PathVariable Long algoritmocalculoturno_id,
            @PathVariable Integer periodocicloturno_id,
            HttpServletRequest request
    ) throws Exception {
        logger.debug(String.format("Logging from controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
        return () -> {
            logger.debug(String.format("Logging from Callable deferred execution of controller: %s", Thread.currentThread().getStackTrace()[1].getMethodName()));
            PeriodoCicloTurno periodoCicloTurno = turnoService.eliminarPeriodoCicloTurno(
                    algoritmocalculoturno_id,
                    periodocicloturno_id
            );
            return new ResponseEntity<>(
                    new PeriodoCicloTurnoDTO(
                            periodoCicloTurno,
                            request,
                            ModeloDeRepresentacao.Verboso.class
                    ),
                    HttpStatus.CREATED
            );
        };
    }
}
