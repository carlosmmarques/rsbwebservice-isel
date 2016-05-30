package pt.isel.ps.li61n.controller.pessoal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TurnoNotfoundException - Description
 * Created on 29/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Este Turno não existe")
public class TurnoNotfoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TurnoNotfoundException(Long turno_id) {
        super(String.format("Turno com id %s não existe no repositório", turno_id));
    }
}
