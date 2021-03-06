package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.Periodo;

import java.sql.Date;
import java.util.Optional;

/**
 * IPeriodoRepositorio - Description
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface IPeriodoRepositorio extends JpaRepository<Periodo, Long> {

    /**
     * Método de pesquisa especifica. Obtem objecto do tipo Periodo enquadrado num determinado periodo.
     *
     * @param datainicio Data de Inicio
     * @param datafim    Data de fim
     * @return Optional<Periodo>
     */
    @Query("SELECT p FROM Periodo p WHERE p.dtInicio >= :datainicio AND p.dtFim <= :datafim")
    Optional<Periodo> findByDataInicioAndDataFim(
            @Param("datainicio") Date datainicio,
            @Param("datafim") Date datafim
    );

    /**
     * Método de pesquisa específica. Obtem objecto do tipo Periodo com data de inicio igual ou superior à data indicada.
     *
     * @param datainicio Data de Inicio
     * @return Optional<Periodo>
     */
    @Query("SELECT p FROM Periodo p WHERE p.dtInicio >= :datainicio")
    Optional<Periodo> findByDataInicio(
            @Param("datainicio") Date datainicio
    );

}
