package pt.isel.ps.li61n.controller.error.exception;

/**
 * ErroNãoDeterminadoException - Description
 * Created on 13/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ErroNãoDeterminadoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ErroNãoDeterminadoException(String message) {
            super(message == "" ? "Erro indeterminado!" : String.format("Erro! %s", message));
    }
}
