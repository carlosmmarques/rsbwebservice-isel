package pt.isel.ps.li61n.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
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
     * Método de pesquisa específica. Obtem objecto do tipo Presença para uma data e elemento.
     *
     * @param data              Data da presença
     * @param elementoDoPessoal Identificador do elemento do pessoal que executa a presença
     * @return Optional<Presenca)
     */
    @Query(value = "SELECT p FROM Presenca p WHERE p.data = :data AND p.elementoDoPessoal = :elementodopessoal AND p.eliminado = false")
    public Optional<Presenca> findByDataAndElementoDoPessoal(
            @Param("data") Date data,
            @Param("elementodopessoal") ElementoDoPessoal elementoDoPessoal
    );

}
