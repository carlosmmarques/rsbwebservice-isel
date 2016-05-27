package pt.isel.ps.li61n.controller.pessoal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RegistoFormacaoNotFoundException - Classe para tratamento de excepções via HTTP relativos a Pessoal
 * Created on 27/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Não existe registo de formação com os critérios especificados.")
public class RegistoFormacaoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RegistoFormacaoNotFoundException(String formacao_id, String elemento_id) {
        super(String.format("Não existe registo da formação com id %s para o elemento com id %s no repositório", formacao_id, elemento_id));
    }
}
