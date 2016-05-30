package pt.isel.ps.li61n.controller.pessoal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PessoalNumMecanograficoExistsException - Description
 * Created on 29/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Já existe um elemento com este numero mecanográfico.")
public class PessoalNumMecanograficoExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PessoalNumMecanograficoExistsException(String numMecanografico) {
        super(String.format("Elemento com numero mecanográfico %s já existe no repositório", numMecanografico));
    }
}
