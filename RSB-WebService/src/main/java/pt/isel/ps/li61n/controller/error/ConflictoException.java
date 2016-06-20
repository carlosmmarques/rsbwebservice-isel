package pt.isel.ps.li61n.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ConflictoException - Description
 * Created on 29/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Conflito.")
public class ConflictoException extends RuntimeException {
    private static final long serialVersionUID = 3L;

    public ConflictoException(String message) {
        super(message == "" ? "Erro! Conflicto." : String.format("Erro! Conflicto: %s", message));
    }
}
