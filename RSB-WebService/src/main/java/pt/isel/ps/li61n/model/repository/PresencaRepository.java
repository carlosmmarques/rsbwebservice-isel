package pt.isel.ps.li61n.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pt.isel.ps.li61n.model.entities.Presenca;

/**
 * UnidadeEstruturalRepository - Description
 * Created on 04/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface PresencaRepository extends JpaRepository<Presenca, Long>{

//    /**
//     * Obtem Lista de Periodos
//     * @return Conjunto de Periodos
//     */
//    public Set<Periodo> obterPeriodos();
//
//    /**
//     * Obtem Lista de Periodos para um ano especifico
//     * @return Conjunto de Periodos para o ano especificado
//    */
//    public Set<Periodo> obterPeriodosPorAno(Integer ano);
//
//    /**
//     * Obtem uma instancia de Periodo a partir do repositório
//     * @param id - Identificador do Periodo
//     * @return Periodo
//     */
//    public Periodo obterPeriodo(Integer id);
//
//    /**
//     * Inserir Periodo
//     * @return Id do novo periodo
//     */
//    public Integer inserirPeriodo(Periodo periodo);

}
