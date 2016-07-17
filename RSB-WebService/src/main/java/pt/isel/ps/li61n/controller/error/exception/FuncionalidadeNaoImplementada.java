package pt.isel.ps.li61n.controller.error.exception;

/**
 * FuncionalidadeNaoImplementada - Description
 * Created on 17/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class FuncionalidadeNaoImplementada extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FuncionalidadeNaoImplementada(String message) {
        super(message == "" ? "Funcionalidade não implementada!" : String.format("Erro! %s", message));
    }
}
