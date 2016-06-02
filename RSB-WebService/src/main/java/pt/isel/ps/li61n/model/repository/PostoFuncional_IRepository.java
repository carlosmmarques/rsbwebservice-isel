package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.PostoFuncional;

/**
 * PostoFuncional_IRepository - Description
 * Created on 27/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface PostoFuncional_IRepository extends JpaRepository<PostoFuncional, Long> {
}