package pt.isel.ps.li61n.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.Presenca;

import java.sql.Date;
import java.util.Optional;

/**
 * UnidadeEstrutural_IRepository - Description
 * Created on 04/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface IPresencaRepositorio extends JpaRepository<Presenca, Long> {


    /**
     * @param date                 Data da presença
     * @param elementodopessoal_id Identificador do elemento do pessoal que executa a presença
     * @return
     */
    @Query("SELECT p FROM Presenca p WHERE p.data = :data AND p.elementoDoPessoal = :elementodopessoal")
    public Optional<Presenca> findByDataAndElementoDoPessoal(
            @Param("data") Date date,
            @Param("elementodopessoal") Long elementodopessoal_id
    );

}
