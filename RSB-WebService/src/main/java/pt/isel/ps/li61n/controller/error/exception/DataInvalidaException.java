package pt.isel.ps.li61n.controller.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * DataInvalidaException - Description
 * Created on 15/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Data Inválida.")
public class DataInvalidaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataInvalidaException(String message) {
        super(message == "" ? "Data inválida!" : String.format("Erro! %s", message));

    }
}
