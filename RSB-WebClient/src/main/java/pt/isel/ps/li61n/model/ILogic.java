package pt.isel.ps.li61n.model;

import java.util.Collection;

/**
 * Created on 27/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public interface ILogic< Id, Type > {

    Collection< Type > getAll();

    Type getOne( Id id );

    Id create( Type element ); // return id??

    //void update ( E e );
}
