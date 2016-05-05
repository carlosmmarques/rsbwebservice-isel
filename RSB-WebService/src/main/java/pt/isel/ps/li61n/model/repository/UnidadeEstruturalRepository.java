package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import java.util.List;

/**
 * UnidadeEstruturalRepository - Description
 * Created on 04/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface UnidadeEstruturalRepository extends JpaRepository<UnidadeEstrutural, Long>{

   List<UnidadeEstrutural> findAll();

   /**
   * Retrieves an entity by its id.
   *
   * @param aLong must not be {@literal null}.
   * @return the entity with the given id or {@literal null} if none found
   * @throws IllegalArgumentException if {@code id} is {@literal null}
   */
   UnidadeEstrutural findById(Long aLong);
}
