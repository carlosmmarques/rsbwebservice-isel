package pt.isel.ps.li61n.model.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.error.*;
import pt.isel.ps.li61n.model.entities.*;
import pt.isel.ps.li61n.model.repository.IPeriodoRepositorio;
import pt.isel.ps.li61n.model.repository.IPessoalRepositorio;
import pt.isel.ps.li61n.model.repository.ITurnoRepositorio;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GeradorPresencasService - Description
 * Created on 13/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public class GeradorPresencasService implements IGeradorPresencasService {

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     * Instâncias dos repositórios
     */
    @Autowired
    private IPessoalRepositorio pessoalRepo;
    @Autowired
    private ITurnoRepositorio turnoRepo;
    @Autowired
    private IPeriodoRepositorio periodoRepo;
    @Autowired
    private IPresencaService presencaService;

    /**
     * @param periodo O periodo em relação ao qual pretendemos popular as presenças
     * @throws Exception
     */
    @Override
    public void popularPresencas(Periodo periodo) throws Exception {

        logger.debug("A popular presenças para o periodo [" + periodo.getDtInicio() + " - " + periodo.getDtFim() + "]");

        Calendar cal = Calendar.getInstance();
        cal.setTime(periodo.getDtInicio());
        cal.add(Calendar.DATE, -1);

        pessoalRepo.findAll().stream()
                .forEach(elementoDoPessoal -> {
                    try {
                        popularPresenças(periodo, elementoDoPessoal);
                    } catch (TurnoPosteriorAoPeriodoException e) {
                        logger.info(e.getMessage());
                    } catch (ConflictoException
                            | NoSuchElementException
                            | RecursoEliminadoException
                            | NaoEncontradoException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new ErroNãoDeterminadoException(e.getMessage());
                    }
                });
    }

    /**
     * @param periodo           O periodo em relação ao qual pretendemos popular as presenças do elemento
     * @param elementoDoPessoal O elemento em relação pretendemos popular as presenças no periodo
     * @throws Exception
     */
    @Override
    public Collection<Presenca> popularPresenças(Periodo periodo, ElementoDoPessoal elementoDoPessoal) throws Exception {

        List<Presenca> presenças = new LinkedList<>();

        final LocalDate
                dtInicio = periodo.getDtInicio().toLocalDate(),
                dtFim = periodo.getDtFim().toLocalDate();

        logger.debug(" - elemento: " + elementoDoPessoal.getId() + " - " + elementoDoPessoal.getNome() + ".");

        Turno turno = elementoDoPessoal.getTurno();
        if (turno.getDtInicioCiclo().compareTo(periodo.getDtInicio()) > 0) throw new TurnoPosteriorAoPeriodoException(
                String.format("O turno a que o elemento (%s - %s) pertence tem data de inicio posterior à do periodo. " +
                                "Não é possivel popular as presenças para este elemento",
                        elementoDoPessoal.getId(), elementoDoPessoal.getNome())
        );

        logger.debug(" - - Turno " + turno.getId() + " - " + turno.getDesignacao() + ".");

        for (LocalDate date = dtInicio; !date.isEqual(dtFim); date = date.plusDays(1)) {
            logger.debug(" - - - A calcular o numero de horas e hora de inicio da " +
                    "presença na data " + date.toString() + ".");
            final Date dtInicioTurno = turno.getDtInicioCiclo();
            final Time timeInicioTurno = turno.getHrInicioCiclo();
            // periodos de ciclo ordenados de forma ascendente.
            Collection<PeriodoCicloTurno> periodos = turno.getAlgoritmoCalculoTurno()
                    .getCiclos().stream()
                    .sorted((o1, o2) -> o1.getOrdemPeriodoCiclo().compareTo(o2.getOrdemPeriodoCiclo()))
                    .collect(Collectors.toList());
            // dimensão do ciclo: numero de dias totais de um ciclo de turno (sempre múltiplos de 24 Horas)
            final float dimensaoDoCiclo = periodos.stream()
                    .map(PeriodoCicloTurno::getNumHoras)
                    .reduce((aFloat, aFloat2) -> aFloat + aFloat2)
                    .get()
                    / 24;
            logger.debug(" - - - - Dimensão do ciclo do turno: " + dimensaoDoCiclo + ".");
            // dias decorridos desde a data de inicio do turno
            final long diasDecorridos = ChronoUnit.DAYS.between(turno.getDtInicioCiclo().toLocalDate(), date);
            logger.debug(" - - - - Dias decorridos desde o inicio do turno: " +
                    diasDecorridos + ".");
            // atraso do ciclo - Representa o numero de horas decorridas desde o ultimo reinicio do ciclo
            float atrasoCiclo = (diasDecorridos % dimensaoDoCiclo) * 24;
            logger.debug(" - - - - Atraso do ciclo à data: " + atrasoCiclo +
                    " horas decorridas desde o ultimo reinicio de ciclo.");
            LocalDateTime dataHrInicio = LocalDateTime.of(
                    dtInicioTurno.toLocalDate(), timeInicioTurno.toLocalTime());
            float numHoras = 0;

            for (PeriodoCicloTurno p : periodos) {
                numHoras = p.getNumHoras();
                if (atrasoCiclo <= 0) {
                    if (p.getPeriodoDescanso()) {
                        logger.debug(" - - - - - O periodo em causa é de descanso, não serão registadas horas");
                        numHoras = 0f;
                    }
                    break;
                }
                atrasoCiclo = atrasoCiclo - numHoras;
                if (atrasoCiclo < 0)
                    if (p.getPeriodoDescanso()) numHoras = 0f;
            }
            dataHrInicio = dataHrInicio.plusMinutes((diasDecorridos * 24 * 60) + ((int) atrasoCiclo * 60));
            if (numHoras == 0f) continue;
            logger.debug(" - - - a tentar inserir presença para a data: " + date.toString() + ".");
            try {
                final Date d = Date.valueOf(date);
                presenças.add(presencaService.inserirPresenca(
                        Date.valueOf(date),
                        Time.valueOf(dataHrInicio.toLocalTime()),
                        numHoras,
                        periodo.getId(),
                        turno.getId(),
                        elementoDoPessoal.getInstalacao().getId(),
                        elementoDoPessoal.getPostoFuncional().getId(),
                        elementoDoPessoal.getTipoPresenca().getId(),
                        elementoDoPessoal.getId(),
                        Optional.empty(),
                        Optional.empty()
                ));
            } catch (ConflictoException | NoSuchElementException | RecursoEliminadoException | NaoEncontradoException e) {
                throw e;
            } catch (Exception e) {
                throw new ErroNãoDeterminadoException(e.getMessage());
            }
        }
        return presenças;
    }
}
