package pt.isel.ps.li61n.model.dal.web;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public abstract class AbstractWebService {

    public static  <E> E  getResourceWithCache( String uri, Map< String, E > cache, Class<E> klass ){

        E elemento = cache.get( uri );
        if( elemento == null ){
            elemento = getResource( uri, klass );
            if( elemento != null ){
                cache.put( uri, elemento );
            }
        }
        return elemento;
    }

    public static <E> E getResource( String uri, Class< E > klass ) {

        E result = null;
        if (uri != null && !uri.isEmpty()) {
            try {
                result = RsbWebServiceAsync
                            .callAndConvert(klass, uri)
                            .get();
            }
            catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
