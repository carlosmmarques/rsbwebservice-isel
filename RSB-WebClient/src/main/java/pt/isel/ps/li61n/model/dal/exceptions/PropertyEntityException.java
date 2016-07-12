package pt.isel.ps.li61n.model.dal.exceptions;

import org.springframework.validation.FieldError;

/**
 * Created on 07/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PropertyEntityException extends Exception {

    public final String fieldName;

    public PropertyEntityException( String fieldName, String message ){
        super( message );
        this.fieldName = fieldName;
    }
}
