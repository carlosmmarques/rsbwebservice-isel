package pt.isel.ps.li61n.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe para tratamento de excepções via HTTP relativos a ElementoDoPessoal
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Recurso não encontrado.")
public class NaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NaoEncontradoException(String message) {
        super(message == "" ? "Erro! Recurso não encontrado" : String.format("Erro! %s", message));
    }
}
