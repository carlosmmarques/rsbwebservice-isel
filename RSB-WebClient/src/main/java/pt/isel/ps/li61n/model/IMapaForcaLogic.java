package pt.isel.ps.li61n.model;

import pt.isel.ps.li61n.model.entities.Presenca;

import java.util.Collection;

/**
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IMapaForcaLogic {

    Collection<Presenca> getAllPresencas();
}
