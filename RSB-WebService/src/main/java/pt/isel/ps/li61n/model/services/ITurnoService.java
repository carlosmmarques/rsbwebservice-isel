package pt.isel.ps.li61n.model.services;

import org.springframework.stereotype.Service;
import pt.isel.ps.li61n.model.entities.AlgoritmoCalculoTurno;
import pt.isel.ps.li61n.model.entities.PeriodoCicloTurno;
import pt.isel.ps.li61n.model.entities.Turno;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Optional;

/**
 * IPresencasService - Serviço de Fachada para acesso a dados da secção nuclear de gestão de Turno
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public interface ITurnoService {


    /**
     * @return Colecção de Turnos
     * @throws Exception
     */
    Collection<Turno> obterturnos(
    ) throws Exception;


    /**
     * @param id Identificador da Turno
     * @return Turno
     */
    Turno obterTurno(
            Long id
    ) throws Exception;


    /**
     * @param designacao Designação do Turno
     * @param dtiniciociclo Data de incio do Turno
     * @param hrInicioCiclo Hora de incio do Turno
     * @param algoritmocalculoturno_id Identificado do Algoritmos de Calculo de Turno
     * @return Turno inserido
     * @throws Exception
     */
    Turno inserirTurno(
            String designacao,
            Date dtiniciociclo,
            Time hrInicioCiclo,
            Long algoritmocalculoturno_id
    ) throws Exception;


    /**
     *
     * @param id Identificador do Turno a actualizar
     * @param designacao Designação do Turno
     * @param dtiniciociclo Data de incio do Turno
     * @param hrInicioCiclo Hora de incio do Turno
     * @param algoritmocalculoturno_id Identificado do Algoritmos de Calculo de Turno
     * @throws Exception
     * @return Turno actuaizado
     */
    Turno actualizarTurno(
            Long id,
            Optional<String> designacao,
            Optional<Date> dtiniciociclo,
            Optional<Time> hrInicioCiclo,
            Optional<Long> algoritmocalculoturno_id
    ) throws Exception;


    /**
     *
     * @param id Identificador do Turno
     * @return Turno eliminado
     * @throws Exception
     */
    Turno eliminarTurno(
            Long id
    ) throws Exception;


    /**
     * @return Colecção de Algoritmos de Cálculo de Turno
     * @throws Exception
     */
    Collection<AlgoritmoCalculoTurno> obterAlgoritmosCalculoTurno() throws Exception;


    /**
     * @param id Identificador do Algoritmo de Cálculo de Turno
     * @return Algoritmo de Calculo de Turno
     * @throws Exception
     */
    AlgoritmoCalculoTurno obterAlgoritmoCalculoTurno(Long id) throws Exception;


    /**
     *
     * @param designacao Designação do Algoritmo de Cálculo de Turno
     * @param descricao Descrição do Algoritmo de Cálculo de Turno
     * @param servicopermanente Indica se o Algortimo atende ao critério de manutenção de serviço permanente
     * @return Algoritmo de Cálculo de Turno inserido
     * @throws Exception
     */
    AlgoritmoCalculoTurno inserirAlgoritmoCalculoTurno(
            String designacao,
            String descricao,
            Boolean servicopermanente
    ) throws Exception;


    /**
     *
     * @param id Identificador do Algoritmo de Cálculo de Turno
     * @param designacao Designação do Algortimo d Cálculo de Turno
     * @param descricao Descrição do Algoritmo de Cálculo de Turno
     * @param servicopermanente Indica se o Algortimo atende ao critério de manutenção de serviço permanente
     * @return Algoritmo de Cálculo de Turno actualizado
     * @throws Exception
     */
    AlgoritmoCalculoTurno actualizarAlgoritmoCalculoTurno(
            Long id,
            Optional<String> designacao,
            Optional<String> descricao,
            Optional<Boolean> servicopermanente
    ) throws Exception;


    /**
     * @param id Identificador do Algoritmo de Cálculo de Turno
     * @return Algoritmo de Cálculo de Turno eliminado
     * @throws Exception
     */
    AlgoritmoCalculoTurno eliminarAlgoritmoCalculoTurno(Long id) throws Exception;


    /**
     * @param algoritmocalculoturno_id Identificador do algoritmo de Calculo de Turno
     * @return Colecção de Periodos de Ciclos de Turno (Opcionalmente, respeitantes ao algoritmo passado por parametro)
     * @throws Exception
     */
    Collection<PeriodoCicloTurno> obterPeriodosCicloTurno(
            Long algoritmocalculoturno_id
    ) throws Exception;


    /**
     * @param algoritmocalculoturno_id Identificador do Algoritmo de Cálculo de Turno
     * @param ordemperiodociclo Identificador da Ordem do Periodo do Ciclo
     * @return Periodo de Ciclo de Turno
     * @throws Exception
     */
    PeriodoCicloTurno obterPeriodoCicloTurno(
            Long algoritmocalculoturno_id,
            Integer ordemperiodociclo
    ) throws Exception;


    /**
     * @param ordemperiodociclo A ordem do periodo do ciclo para o respectivo algoritmo de calculo de turno
     * @param algoritmocalculoturno_id Identificador do algoritmo de calculo de turno
     * @param numhoras Numero de horas do periodo
     * @param periododescanso Informa se o periodo de ciclo é de descanso
     * @return Periodo de Ciclo de Turno inserido
     * @throws Exception
     */
    PeriodoCicloTurno inserirPeriodoCicloTurno(
            Integer ordemperiodociclo,
            Long algoritmocalculoturno_id,
            Float numhoras,
            Boolean periododescanso
    ) throws Exception;


    /**
     * @param ordemperiodociclo A ordem do periodo do ciclo para o respectivo algoritmo de calculo de turno
     * @param algoritmocalculoturno_id Identificador do algoritmo de calculo de turno
     * @param numhoras Numero de horas do periodo (Opcional)
     * @param periododescanso Informa se o periodo de ciclo é de descanso (Opcional)
     * @return Periodo de Ciclo de Turno actualizado
     * @throws Exception
     */
    PeriodoCicloTurno actualizarPeriodoCicloTurno(
            Integer ordemperiodociclo,
            Long algoritmocalculoturno_id,
            Optional<Float> numhoras,
            Optional<Boolean> periododescanso
    ) throws Exception;


    /**
     * @param algoritmocalculoturno_id Identificador do Algoritmo de Cálculo de Turno
     * @param ordemperiodociclo Identificador da Ordem do Periodo do Ciclo
     * @return Periodo de Ciclo de Turno eliminado
     * @throws Exception
     */
    PeriodoCicloTurno eliminarPeriodoCicloTurno(
            Long algoritmocalculoturno_id,
            Integer ordemperiodociclo
    ) throws Exception;

}