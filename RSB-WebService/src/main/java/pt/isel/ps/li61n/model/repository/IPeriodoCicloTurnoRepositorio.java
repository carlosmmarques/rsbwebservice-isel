package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.AlgoritmoCalculoTurno;
import pt.isel.ps.li61n.model.entities.PeriodoCicloTurno;

import java.util.List;

/**
 * IPeriodoCicloTurnoRepositorio - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface IPeriodoCicloTurnoRepositorio extends JpaRepository<PeriodoCicloTurno, PeriodoCicloTurno.CicloTurnoPK>{

    /**
     * @param algoritmocalculoturno Algoritmo de Calculo de Turno
     * @return
     */
    @Query("SELECT p FROM PeriodoCicloTurno p WHERE p.algoritmoCalculoTurno = :algoritmocalculoturno")
    List<PeriodoCicloTurno> findPeriodosCicloByAlgoritmo(@Param("algoritmocalculoturno") AlgoritmoCalculoTurno algoritmocalculoturno);

    List<PeriodoCicloTurno> findByAlgoritmoCalculoTurno(AlgoritmoCalculoTurno algoritmoCalculoTurno);

}
