package pt.isel.ps.li61n.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pt.isel.ps.li61n.controller.pessoal.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ErrorHandler - Handler para as excepções que definimos para a aplicação
 * Created on 30/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * Tratamento de Excepções do Tipo Conflito
     * @param exc Excepção a tratar
     * @param request HttpServletRequest
     * @return ResponseEntity para JSON
     */
    @ExceptionHandler(value = {
            ConflictException.class
    })
    @ResponseBody
    protected ResponseEntity<ErrorInfo> handleConflictException(RuntimeException exc, HttpServletRequest request){
        String completeURL = request.getRequestURL().toString();
        if (request.getQueryString() != null)
            completeURL += request.getQueryString();
        ErrorInfo errorInfo = new ErrorInfo(completeURL, exc.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }

    /**
     * Tratamento de Excepções do Tipo "Recurso Não existente"
     * TODO Perceber melhor se este comportamento (404) é adequado para queries bem formadas, em que não existam recursos, ou devolver um 204
     *
     * @param exc Excepção a tratar
     * @param request HttpServletRequest
     * @return ResponseEntity para JSON
     */
    @ExceptionHandler(value = {
            NotFoundException.class,
    })
    @ResponseBody
    protected ResponseEntity<ErrorInfo> handleResourceNotFoundException(RuntimeException exc, HttpServletRequest request){
        String completeURL = request.getRequestURL().toString();
        if (request.getQueryString() != null)
            completeURL += request.getQueryString();
        ErrorInfo errorInfo = new ErrorInfo(completeURL, exc.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    /**
     * Tratamento de Excepções de Tipos desconhecidos
     * TODO Melhor solução do que isto. Assumir erro interno por omissão é horrivel!
     * @param exc Excepção a tratar
     * @param request HttpServletRequest
     * @return ResponseEntity para JSON
     */
    @ExceptionHandler()
    @ResponseBody
    protected ResponseEntity<ErrorInfo> handleUnknownException(RuntimeException exc, HttpServletRequest request){
        String completeURL = request.getRequestURL().toString();
        if (request.getQueryString() != null)
            completeURL += request.getQueryString();
        ErrorInfo errorInfo = new ErrorInfo(completeURL, exc.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
