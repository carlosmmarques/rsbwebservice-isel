package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;

/**
 * TipoUnidadeOperacionalRepositorio - Description
 * Created on 04/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface ITipoUnidadeOperacionalRepositorio extends JpaRepository<TipoUnidadeOperacional, Long> {
}
