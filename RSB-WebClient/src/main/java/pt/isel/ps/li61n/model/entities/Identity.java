package pt.isel.ps.li61n.model.entities;

/**
 * Created on 17/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public abstract class Identity< T > {

    private T id;

    public void setId( T id ){
        this.id = id;
    }

    public T getId(){
        return this.id;
    }
}
