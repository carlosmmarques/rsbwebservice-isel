package pt.isel.ps.li61n.controller.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RecursoEliminadoException - Description
 * Created on 17/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.GONE, reason = "O recurso foi eliminado.")
public class RecursoEliminadoException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public RecursoEliminadoException(String message) {
        super(message == "" ? "Erro! Recurso eliminado." : String.format("Erro! %s", message));
    }
}
