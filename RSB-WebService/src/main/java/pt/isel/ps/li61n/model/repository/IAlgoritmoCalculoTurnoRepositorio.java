package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.AlgoritmoCalculoTurno;

/**
 * IAlgoritmoCalculoTurnoRepositorio - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface IAlgoritmoCalculoTurnoRepositorio extends JpaRepository<AlgoritmoCalculoTurno, Long> {

}
