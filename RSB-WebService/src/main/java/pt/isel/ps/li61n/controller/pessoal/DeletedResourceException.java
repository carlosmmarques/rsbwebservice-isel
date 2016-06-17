package pt.isel.ps.li61n.controller.pessoal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * DeletedResourceException - Description
 * Created on 17/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.GONE, reason = "Elemento foi eliminado")
public class DeletedResourceException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    public DeletedResourceException(String message) {
        super(message == "" ? "Erro! Não existem elementos" : String.format("Erro! %s", message));
    }
}
