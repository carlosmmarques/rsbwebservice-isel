package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.isel.ps.li61n.model.entities.Turno;

/**
 * UnidadeEstruturalRepository - Description
 * Created on 04/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface TurnoRepository extends JpaRepository<Turno, Long>{
}
