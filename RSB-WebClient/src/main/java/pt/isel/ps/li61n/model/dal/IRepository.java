package pt.isel.ps.li61n.model.dal;

import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;

import java.util.Collection;

/**
 * Created on 16/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IRepository < Id, Type > {

    Id insert( Type element ) throws RepositoryException;

    Type selectOne( Id id ) throws RepositoryException;

    Collection< Type > selectAll() throws RepositoryException;

    void delete( Id id );

    void update( Id id );
}