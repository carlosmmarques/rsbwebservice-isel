package pt.isel.ps.li61n.model.dal.web.exceptions;


import org.springframework.http.HttpStatus;

/**
 * Created on 07/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class WebServiceException extends RuntimeException {

    public final int statusCode;
    public final String uri;

    public WebServiceException(  String uri, String message, int statusCode ){
        super( message );
        this.uri = uri;
        this.statusCode = statusCode;
    }
}
