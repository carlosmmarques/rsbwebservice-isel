package pt.isel.ps.li61n.controller.error.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pt.isel.ps.li61n.controller.error.ErrorInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * RequestErrorHandler - Description
 * Created on 14/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ControllerAdvice
public class RequestErrorHandler{

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleValidationException(RuntimeException ex, HttpServletRequest request) {
        ErrorInfo errorInfo = ErrorInfo.getErrorInfo(ex, request);
        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }


}
