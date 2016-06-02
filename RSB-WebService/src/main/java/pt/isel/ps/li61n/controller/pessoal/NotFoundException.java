package pt.isel.ps.li61n.controller.pessoal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe para tratamento de excepções via HTTP relativos a ElementoDoPessoal
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Elemento do pessoal não existe")
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(String.format(message == "" ? "Não existem elementos" : "Elemento com message %s não existe no repositório", message));
    }
}
