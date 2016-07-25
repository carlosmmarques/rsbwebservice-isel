package pt.isel.ps.li61n.model.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.error.exception.*;
import pt.isel.ps.li61n.model.entities.*;

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
public class EscalaService implements IEscalaService {

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     * Instâncias dos repositórios
     */
    @Autowired
    private IPresencaService presencaService;
    @Autowired
    IPessoalService pessoalService;

    /**
     * @param periodo O periodo em relação ao qual pretendemos popular as presenças
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void popularPresencas(Periodo periodo) throws Exception {

        logger.debug("A popular presenças para o periodo [" + periodo.getDtInicio() + " - " + periodo.getDtFim() + "]");

        Calendar cal = Calendar.getInstance();
        cal.setTime(periodo.getDtInicio());
        cal.add(Calendar.DATE, -1);

       pessoalService.obterElementosDoPessoal(
               Optional.empty(),
               Optional.empty(),
               Optional.empty(),
               Optional.empty(),
               Optional.empty(),
               Optional.empty(),
               Optional.empty()
       ).stream()
                .forEach(elementoDoPessoal -> {
                    try {
                        popularPresencas(periodo, elementoDoPessoal);
                    } catch (TurnoPosteriorAoPeriodoException e) {
                        logger.info(e.getMessage());
                    } catch (ConflictoException
                            | NoSuchElementException
                            | RecursoEliminadoException
                            | NaoEncontradoException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new ErroNaoDeterminadoException(e.getMessage());
                    }
                });
    }

    /**
     * @param periodo           O periodo em relação ao qual pretendemos popular as presenças do elemento
     * @param elementoDoPessoal O elemento em relação pretendemos popular as presenças no periodo
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY) // forçar a execução no contexto de uma transação
    public Collection<Presenca> popularPresencas(Periodo periodo, ElementoDoPessoal elementoDoPessoal) throws Exception {

        List<Presenca> presencas = new LinkedList<>();

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

        for (LocalDate date = dtInicio; !date.isEqual(dtFim.plusDays(1)); date = date.plusDays(1)) {

            CalculadorDeNumeroDeHorasEDataHoraDeInicioDaPresenca calculadorDeNumeroDeHorasEDataHoraDeInicioDaPresenca =
                    new CalculadorDeNumeroDeHorasEDataHoraDeInicioDaPresenca(turno, date)
                            .invoke();

            float numHoras = calculadorDeNumeroDeHorasEDataHoraDeInicioDaPresenca.getNumHoras();
            LocalDateTime dataHrInicio = calculadorDeNumeroDeHorasEDataHoraDeInicioDaPresenca.getDataHrInicio();

            if (numHoras == 0f) continue;

            logger.debug(" - - - a tentar inserir presença para a data: " + date.toString() + ".");

            try {
                final Date d = Date.valueOf(date);
                presencas.add(presencaService.inserirPresenca(
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
                throw new ErroNaoDeterminadoException(e.getMessage());
            }
        }
        return presencas;
    }

    /**
     * Obter elementos para cedencia de troca, ordenados por ordem de favorabilidade de solução
     *
     * @param presenca_id Identificador da Presença, em relação à qual queremos obter elementos para troca
     * @return Lista de elementos disponíveis para troca
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Collection<ElementoDoPessoal> obterElementosDoPessoalParaReforco(Long presenca_id) throws Exception {
        throw new FuncionalidadeNaoImplementada("Esta funcionalidade não se encontra ainda implementada");
    }

    /**
     * Realizar reforço de uma presenca de um elemento
     *
     * @param data               Data de relização da troca
     * @param elementoAusente_id Identificador do elemento ausente
     * @param elementoReforco_id Identificador do elemento de reforço
     * @return a Presença com o elemento de reforço
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Presenca realizarReforco(Date data, Long elementoAusente_id, Long elementoReforco_id) throws Exception {
        throw new FuncionalidadeNaoImplementada("Esta funcionalidade não se encontra ainda implementada");
    }

    /**
     * Calculador de Numero de Horas e Datas de Inicio das presenças
     * Esta classe realiza os calculos necessários para determinar:
     *  - O atraso existente à data em relação às datas de renovaçõo dos ciclos de turno, e realizar o necessário ajuste
     *  - apurar a data e hora de inicio da presença
     *  - apurar o numero de horas das presenças (periodos de descanso são ignorados)
     */
    private class CalculadorDeNumeroDeHorasEDataHoraDeInicioDaPresenca {

        private Turno turno;
        private LocalDate date;
        private LocalDateTime dataHrInicio;
        private float numHoras;

        public CalculadorDeNumeroDeHorasEDataHoraDeInicioDaPresenca(Turno turno, LocalDate date) {
            this.turno = turno;
            this.date = date;
        }

        public LocalDateTime getDataHrInicio() {
            return dataHrInicio;
        }

        public float getNumHoras() {
            return numHoras;
        }

        public CalculadorDeNumeroDeHorasEDataHoraDeInicioDaPresenca invoke() {
            logger.debug(" - - - A calcular o numero de horas e hora de inicio da presença na data " +
                    date.toString() + ".");

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
            dataHrInicio = LocalDateTime.of(
                    dtInicioTurno.toLocalDate(), timeInicioTurno.toLocalTime());
            numHoras = 0;

            // apuramento do numero de horas e data e hora de inicio da presença por ajuste do atraso do ciclo.
            for (PeriodoCicloTurno p : periodos) {
                numHoras = p.getNumHoras();
                if (atrasoCiclo <= 0) {
                    if (p.getPeriodoDescanso()) {
                        logger.debug(" - - - - - O periodo em causa é de descanso, não serão registadas horas");
                        numHoras = 0f;
                    }
                    logger.debug(" - - - - - - Ok! Periodo com ordem " + p.getOrdemPeriodoCiclo() + " elegido!");
                    break;
                }
                atrasoCiclo = atrasoCiclo - numHoras;
                logger.debug(" - - - - - Atraso ajustado para " + atrasoCiclo + " dias.");
                if (atrasoCiclo < 0){
                    logger.debug(" - - - - - - Periodo com ordem " + p.getOrdemPeriodoCiclo() +
                            " ultrapassa o atraso.");
                    if (p.getPeriodoDescanso()) numHoras = 0f;
                }
            }
            dataHrInicio = dataHrInicio.plusMinutes((diasDecorridos * 24 * 60) + ((int) atrasoCiclo * 60));
            logger.debug(" - - - - - Calculo da data e hora para o registo de presença: " + dataHrInicio);
            return this;
        }
    }
}
