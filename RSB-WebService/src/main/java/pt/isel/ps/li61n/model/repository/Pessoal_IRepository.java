package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.Pessoal;

import java.util.List;

/**
 * UnidadeEstrutural_IRepository - Description
 * Created on 04/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface Pessoal_IRepository extends JpaRepository<Pessoal, Long> {

    @Query(value =
            "SELECT * FROM pessoal " +
            "  INNER JOIN pessoal_possui_categoria " +
            "    ON (pessoal.id = pessoal_possui_categoria.pessoal_id)",
    nativeQuery = true)
    List<Pessoal> obtemPessoalPorCategoria();
}
