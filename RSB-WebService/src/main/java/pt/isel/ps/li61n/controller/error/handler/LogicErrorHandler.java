package pt.isel.ps.li61n.controller.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pt.isel.ps.li61n.controller.error.ErrorInfo;
import pt.isel.ps.li61n.controller.error.exception.ConflictoException;
import pt.isel.ps.li61n.controller.error.exception.DataInvalidaException;
import pt.isel.ps.li61n.controller.error.exception.NaoEncontradoException;
import pt.isel.ps.li61n.controller.error.exception.RecursoEliminadoException;

import javax.servlet.http.HttpServletRequest;

/**
 * ErrorHandler - Handler para as excepções que definimos para a aplicação
 * Created on 30/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ControllerAdvice
public class LogicErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * Tratamento de Excepções do Tipo Conflito
     * @param exc Excepção a tratar
     * @param request HttpServletRequest
     * @return ResponseEntity para JSON
     */
    @ExceptionHandler(value = {ConflictoException.class})
    @ResponseBody
    protected ResponseEntity<ErrorInfo> handleConflictException(RuntimeException exc, HttpServletRequest request){
        ErrorInfo errorInfo = ErrorInfo.getErrorInfo(exc, request);
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
    @ExceptionHandler(value = {NaoEncontradoException.class})
    @ResponseBody
    protected ResponseEntity<ErrorInfo> handleResourceNotFoundException(RuntimeException exc, HttpServletRequest request){
        ErrorInfo errorInfo = ErrorInfo.getErrorInfo(exc, request);
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    /**
     * Tratamento de Excepções do Tipo "Recurso Eliminado"
     *
     * @param exc Excepção a tratar
     * @param request HttpServletRequest
     * @return ResponseEntity para JSON
     */
    @ExceptionHandler(value = {RecursoEliminadoException.class})
    @ResponseBody
    protected ResponseEntity<ErrorInfo> handleDeletedResourceException(RuntimeException exc, HttpServletRequest request){
        ErrorInfo errorInfo = ErrorInfo.getErrorInfo(exc, request);
        return new ResponseEntity<>(errorInfo, HttpStatus.GONE);
    }

    /**
     * Tratamento de Excepções de Tipos desconhecidos
     * @param exc Excepção a tratar
     * @param request HttpServletRequest
     * @return ResponseEntity para JSON
     */
    @ExceptionHandler(value = {DataInvalidaException.class})
    @ResponseBody
    protected ResponseEntity<ErrorInfo> handleDataInvalidaException(RuntimeException exc, HttpServletRequest request){
        ErrorInfo errorInfo = ErrorInfo.getErrorInfo(exc, request);
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_ACCEPTABLE);
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
        ErrorInfo errorInfo = ErrorInfo.getErrorInfo(exc, request);
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
