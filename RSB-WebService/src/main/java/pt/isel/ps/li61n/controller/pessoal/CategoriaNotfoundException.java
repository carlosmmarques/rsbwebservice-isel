package pt.isel.ps.li61n.controller.pessoal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CategoriaNotfoundException - Description
 * Created on 29/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Categoria não existe")
public class CategoriaNotfoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CategoriaNotfoundException(Long categoria_id) {
        super(String.format("Categoria com id %s não existe no repositório", categoria_id));
    }
}
