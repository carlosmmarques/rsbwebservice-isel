package pt.isel.ps.li61n.controller.pessoal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe para tratamento de excepções via HTTP relativos a ElementoDoPessoal
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Este elemento do pessoal não existe")
public class PessoalNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PessoalNotFoundException(String id) {
        super(String.format("Elemento com id %s não existe no repositório", id));
    }
}
