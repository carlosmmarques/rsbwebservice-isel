package pt.isel.ps.li61n.controller.error;

/**
 * DataInvalidaException - Description
 * Created on 15/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class DataInvalidaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataInvalidaException(String message) {
        super(message == "" ? "Data inválida!" : String.format("Erro! %s", message));

    }
}
