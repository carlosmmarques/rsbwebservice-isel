package pt.isel.ps.li61n.controller.pessoal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InstalacaoNotfoundException - Description
 * Created on 29/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Instalação não existe")
public class InstalacaoNotfoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InstalacaoNotfoundException(String tipopresenca_id) {
        super(String.format("Instalação com id %s não existe no repositório", tipopresenca_id));
    }
}
