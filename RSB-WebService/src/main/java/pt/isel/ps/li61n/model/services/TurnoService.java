package pt.isel.ps.li61n.model.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException;
import pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException;
import pt.isel.ps.li61n.model.entities.AlgoritmoCalculoTurno;
import pt.isel.ps.li61n.model.entities.PeriodoCicloTurno;
import pt.isel.ps.li61n.model.entities.Turno;
import pt.isel.ps.li61n.model.repository.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TurnoService - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public class TurnoService implements ITurnoService{


    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     * Instâncias dos repositórios
     */
    @Autowired
    private ITurnoRepositorio turnoRepo;
    @Autowired
    private IAlgoritmoCalculoTurnoRepositorio algoritmoCalculoTurnoRepo;
    @Autowired
    private IPeriodoCicloTurnoRepositorio periodoCicloTurnoRepo;

    /**
     * @return Colecção de Turnos
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Turno> obterturnos() throws Exception {
        return turnoRepo.findAll().stream()
                .filter(turno -> turno.getEliminado() != null && !turno.getEliminado())
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador da Turno
     * @return Elemento do Pessoal
     */
    @Override
    @Transactional(readOnly = true)
    public Turno obterTurno(Long id) throws Exception {
        Turno turno = turnoRepo.findOne(id);
        if (turno == null)
            throw new NaoEncontradoException(String.format("Não é possível obter o Turno com id: %s", id));
        if (turno.getEliminado())
            throw new RecursoEliminadoException(String.format("O turno solicitado foi eliminado: %s", id));
        return turno;
    }

    /**
     * @param designacao               Designação do Turno
     * @param dtiniciociclo            Data de incio do Turno
     * @param hrInicioCiclo            Hora de incio do Turno
     * @param algoritmocalculoturno_id Identificado do Algoritmos de Calculo de Turno
     * @return Turno inserido
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Turno inserirTurno(String designacao, Date dtiniciociclo, Time hrInicioCiclo, Long algoritmocalculoturno_id) throws Exception {

        Turno turno = new Turno();
        AlgoritmoCalculoTurno algoritmoCalculoTurno = obterAlgoritmoCalculoTurno(algoritmocalculoturno_id);

        turno.setDesignacao(designacao);
        turno.setDtInicioCiclo(dtiniciociclo);
        turno.setHrInicioCiclo(hrInicioCiclo);
        turno.setAlgoritmoCalculoTurno(algoritmoCalculoTurno);

        return turnoRepo.save(turno);
    }

    /**
     * @param id                       Identificador do Turno a actualizar
     * @param designacao               Designação do Turno
     * @param dtiniciociclo            Data de incio do Turno
     * @param hrInicioCiclo            Hora de incio do Turno
     * @param algoritmocalculoturno_id Identificado do Algoritmos de Calculo de Turno
     * @return Turno actuaizado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Turno actualizarTurno(
            Long id,
            Optional<String> designacao,
            Optional<Date> dtiniciociclo,
            Optional<Time> hrInicioCiclo,
            Optional<Long> algoritmocalculoturno_id
    ) throws Exception {

        Turno turno = obterTurno(id);

        designacao.ifPresent(turno::setDesignacao);
        dtiniciociclo.ifPresent(turno::setDtInicioCiclo);
        hrInicioCiclo.ifPresent(turno::setHrInicioCiclo);
        algoritmocalculoturno_id.ifPresent(aLong -> {
            try {
                turno.setAlgoritmoCalculoTurno(obterAlgoritmoCalculoTurno(aLong));
            } catch (Exception e){
                throw new NaoEncontradoException(String.format("Não é possível obter o Algoritmo de Calculo de Turno com id: %s", id));
            }
        });

        return turnoRepo.save(turno);
    }

    /**
     * @param id Identificador do Turno
     * @return Turno eliminado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Turno eliminarTurno(Long id) throws Exception {
        Turno turno = turnoRepo.findOne(id);
        turno.setEliminado(true);
        return turnoRepo.save(turno);
    }

    /**
     * @return Colecção de Algoritmos de Cálculo de Turno
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<AlgoritmoCalculoTurno> obterAlgoritmosCalculoTurno() throws Exception {
        return algoritmoCalculoTurnoRepo.findAll().stream()
                .filter(algoritmo -> algoritmo.getEliminado() != null && !algoritmo.getEliminado())
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador do Algoritmo de Cálculo de Turno
     * @return Algoritmo de Calculo de Turno
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public AlgoritmoCalculoTurno obterAlgoritmoCalculoTurno(Long id) throws Exception {
        AlgoritmoCalculoTurno algoritmoCalculoTurno = algoritmoCalculoTurnoRepo.findOne(id);
        if (algoritmoCalculoTurno== null)
            throw new NaoEncontradoException(String.format("Não é possível obter o Algoritmo de Calculo de Turno com id: %s", id));
        if (algoritmoCalculoTurno.getEliminado())
            throw new RecursoEliminadoException(String.format("O turno solicitado foi eliminado: %s", id));
        return algoritmoCalculoTurno;

    }

    /**
     * @param designacao        Designação do Algoritmo de Cálculo de Turno
     * @param descricao         Descrição do Algoritmo de Cálculo de Turno
     * @param servicopermanente Indica se o Algortimo atende ao critério de manutenção de serviço permanente
     * @return Algoritmo de Cálculo de Turno inserido
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public AlgoritmoCalculoTurno inserirAlgoritmoCalculoTurno(
            String designacao,
            String descricao,
            Boolean servicopermanente
    ) throws Exception {

        AlgoritmoCalculoTurno algoritmoCalculoTurno = new AlgoritmoCalculoTurno();

        algoritmoCalculoTurno.setDesignacao(designacao);
        algoritmoCalculoTurno.setDescricao(descricao);
        algoritmoCalculoTurno.setServicoPermanente(servicopermanente);

        return algoritmoCalculoTurnoRepo.save(algoritmoCalculoTurno);
    }

    /**
     * @param id                Identificador do Algoritmo de Cálculo de Turno
     * @param designacao        Designação do Algortimo d Cálculo de Turno
     * @param descricao         Descrição do Algoritmo de Cálculo de Turno
     * @param servicopermanente Indica se o Algortimo atende ao critério de manutenção de serviço permanente
     * @return Algoritmo de Cálculo de Turno actualizado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public AlgoritmoCalculoTurno actualizarAlgoritmoCalculoTurno(
            Long id, Optional<String> designacao,
            Optional<String> descricao,
            Optional<Boolean> servicopermanente
    ) throws Exception {
        AlgoritmoCalculoTurno algoritmoCalculoTurno = obterAlgoritmoCalculoTurno(id);
        designacao.ifPresent(algoritmoCalculoTurno::setDesignacao);
        descricao.ifPresent(algoritmoCalculoTurno::setDescricao);
        servicopermanente.ifPresent(algoritmoCalculoTurno::setServicoPermanente);
        return algoritmoCalculoTurnoRepo.save(algoritmoCalculoTurno);
    }

    /**
     * @param id Identificador do Algoritmo de Cálculo de Turno
     * @return Algoritmo de Cálculo de Turno eliminado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public AlgoritmoCalculoTurno eliminarAlgoritmoCalculoTurno(Long id) throws Exception {
        AlgoritmoCalculoTurno algoritmoCalculoTurno = obterAlgoritmoCalculoTurno(id);
        algoritmoCalculoTurno.setEliminado(true);
        return algoritmoCalculoTurnoRepo.save(algoritmoCalculoTurno);
    }

    /**
     * @param algoritmocalculoturno_id Identificador do algoritmo de Calculo de Turno
     * @return Colecção de Periodos de Ciclos de Turno (Opcionalmente, respeitantes ao algoritmo passado por parametro)
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<PeriodoCicloTurno> obterPeriodosCicloTurno(
            Long algoritmocalculoturno_id
    ) throws Exception {
//        return periodoCicloTurnoRepo.findPeriodosCicloByAlgoritmo(
//                algoritmoCalculoTurnoRepo.findOne(algoritmocalculoturno_id));
//        return periodoCicloTurnoRepo.findByAlgoritmoCalculoTurno(algoritmoCalculoTurnoRepo.findOne(algoritmocalculoturno_id));
        Collection<PeriodoCicloTurno> periodosCicloTurno = algoritmoCalculoTurnoRepo.findOne(algoritmocalculoturno_id).getCiclos();
        return periodosCicloTurno;
    }

    /**
     * @param algoritmocalculoturno_id Identificador do Algoritmo de Cálculo de Turno
     * @param ordemperiodociclo        Identificador da Ordem do Periodo do Ciclo
     * @return Periodo de Ciclo de Turno
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public PeriodoCicloTurno obterPeriodoCicloTurno(
            Long algoritmocalculoturno_id,
            Integer ordemperiodociclo
    ) throws Exception {
        PeriodoCicloTurno.CicloTurnoPK pk = new PeriodoCicloTurno.CicloTurnoPK(
                ordemperiodociclo, obterAlgoritmoCalculoTurno(algoritmocalculoturno_id));
        PeriodoCicloTurno periodoCicloTurno = periodoCicloTurnoRepo.findOne(pk);
        if (periodoCicloTurno == null)
            throw new NaoEncontradoException(String.format(
                    "Não é possível obter o Periodo de Calculo de Turno com Ordem %s para o Algoritmo %s.",
                    ordemperiodociclo, algoritmocalculoturno_id));
        if (periodoCicloTurno.getEliminado())
            throw new RecursoEliminadoException(String.format(
                    "O o Periodo de Calculo de Turno com Ordem %s para o Algoritmo %s foi eliminado.",
                    ordemperiodociclo, algoritmocalculoturno_id));
        return periodoCicloTurno;
    }

    /**
     * @param ordemperiodociclo        A ordem do periodo do ciclo para o respectivo algoritmo de calculo de turno
     * @param algoritmocalculoturno_id Identificador do algoritmo de calculo de turno
     * @param numhoras                 Numero de horas do periodo
     * @param periododescanso          Informa se o periodo de ciclo é de descanso
     * @return Periodo de Ciclo de Turno inserido
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public PeriodoCicloTurno inserirPeriodoCicloTurno(
            Integer ordemperiodociclo,
            Long algoritmocalculoturno_id,
            Float numhoras,
            Boolean periododescanso
    ) throws Exception {
        PeriodoCicloTurno periodoCicloTurno = new PeriodoCicloTurno();
        periodoCicloTurno.setAlgoritmoCalculoTurno(obterAlgoritmoCalculoTurno(algoritmocalculoturno_id));
        periodoCicloTurno.setNumHoras(numhoras);
        periodoCicloTurno.setPeriodoDescanso(periododescanso);
        return periodoCicloTurnoRepo.save(periodoCicloTurno);
    }

    /**
     * @param ordemperiodociclo        A ordem do periodo do ciclo para o respectivo algoritmo de calculo de turno
     * @param algoritmocalculoturno_id Identificador do algoritmo de calculo de turno
     * @param numhoras                 Numero de horas do periodo (Opcional)
     * @param periododescanso          Informa se o periodo de ciclo é de descanso (Opcional)
     * @return Periodo de Ciclo de Turno actualizado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public PeriodoCicloTurno actualizarPeriodoCicloTurno(
            Integer ordemperiodociclo,
            Long algoritmocalculoturno_id,
            Optional<Float> numhoras,
            Optional<Boolean> periododescanso
    ) throws Exception {
        PeriodoCicloTurno periodoCicloTurno = obterPeriodoCicloTurno(algoritmocalculoturno_id, ordemperiodociclo);
        numhoras.ifPresent(periodoCicloTurno::setNumHoras);
        periododescanso.ifPresent(periodoCicloTurno::setPeriodoDescanso);
        return periodoCicloTurnoRepo.save(periodoCicloTurno);
    }

    /**
     * @param algoritmocalculoturno_id Identificador do Algoritmo de Cálculo de Turno
     * @param ordemperiodociclo        Identificador da Ordem do Periodo do Ciclo
     * @return Periodo de Ciclo de Turno eliminado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public PeriodoCicloTurno eliminarPeriodoCicloTurno(Long algoritmocalculoturno_id, Integer ordemperiodociclo) throws Exception {
        PeriodoCicloTurno periodoCicloTurno = obterPeriodoCicloTurno(algoritmocalculoturno_id, ordemperiodociclo);
        periodoCicloTurno.setEliminado(true);
        return periodoCicloTurnoRepo.save(periodoCicloTurno);
    }
}
