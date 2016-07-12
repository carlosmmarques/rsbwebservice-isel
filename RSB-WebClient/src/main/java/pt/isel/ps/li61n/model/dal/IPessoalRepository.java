package pt.isel.ps.li61n.model.dal;


import pt.isel.ps.li61n.model.dal.exceptions.ElementoNotFoundException;
import pt.isel.ps.li61n.model.entities.Elemento;

/**
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IPessoalRepository extends IRepository< Long, Elemento> {

    Elemento getOneByNumMecanografico( Integer numMecanografico ) throws ElementoNotFoundException;
}
