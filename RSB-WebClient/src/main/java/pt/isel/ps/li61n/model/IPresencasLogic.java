package pt.isel.ps.li61n.model;

import pt.isel.ps.li61n.model.entities.Elemento;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.model.entities.TipoPresenca;

import java.util.Collection;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IPresencasLogic extends ILogic< Long, Presenca > {

    Collection< TipoPresenca > getAllTiposPresenca();

    Collection< TipoPresenca > getTiposPresencaBy( boolean isAusente, boolean isReforco );

    boolean registarTroca( Long presencaId, Long elementoReforcoId );

    Collection< Elemento > getElementosReforco( Long presencaId );
}
