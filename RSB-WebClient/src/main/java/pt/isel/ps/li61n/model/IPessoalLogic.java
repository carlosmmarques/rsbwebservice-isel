package pt.isel.ps.li61n.model;

import java.util.Collection;

/**
 * Created by Demo on 24/05/2016.
 */
public interface IPessoalLogic<E,T>{

    Collection<E> getAll();

    E getOne( T id );
}
