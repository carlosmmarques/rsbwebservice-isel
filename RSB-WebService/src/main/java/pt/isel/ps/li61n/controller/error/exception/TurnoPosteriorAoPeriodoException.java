package pt.isel.ps.li61n.controller.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TurnoPosteriorAoPeriodoException - Description
 * Created on 13/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Turno posterior ao periodo.")
public class TurnoPosteriorAoPeriodoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TurnoPosteriorAoPeriodoException(String message) {
        super(message == "" ? "Data de Inicio do Turno posterior à data de inicio do periodo!" : String.format("Erro! %s", message));
    }
}
