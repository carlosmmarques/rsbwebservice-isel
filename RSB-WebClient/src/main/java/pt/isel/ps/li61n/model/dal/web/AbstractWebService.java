package pt.isel.ps.li61n.model.dal.web;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public abstract class AbstractWebService {

    protected RsbWebServiceAsync _rsbWebService;

    public AbstractWebService() {
        _rsbWebService = new RsbWebServiceAsync();
    }


    public <E> E getElemento( String uri, Map< String, E > cache, Class<E> klass ){

        E elemento = cache.get( uri );
        if( elemento == null && !uri.isEmpty()  ){
            //TODO: (v2) colocar um supplier para ficar com o resultado do pedido.
            try {
                elemento = _rsbWebService
                        .callAndConvert(
                                klass
                                ,uri
                        )
                        .get();
            }
            catch( InterruptedException | ExecutionException e ){
                e.printStackTrace();
            }
            cache.put( uri, elemento );
        }
        return elemento;
    }
}
