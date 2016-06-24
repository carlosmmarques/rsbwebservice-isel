package pt.isel.ps.li61n.model.dal;

import java.util.Collection;

/**
 * Created on 16/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IRepository < Id, Type > {

    Id insert( Type element );

    Type selectOne( Id id );

    Collection< Type > selectAll();

    void delete( Id id );

    void update( Id id );
}