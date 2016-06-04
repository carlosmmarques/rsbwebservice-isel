package pt.isel.ps.li61n.model;

import java.util.Collection;

/**
 * Created on 27/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public interface ILogic< E, T > {

    Collection< E > getAll();

    E getOne( T id );

    E create( E element ); // return id??

    //void update ( E e );
}
