package pt.isel.ps.li61n.controller.pessoal;

/**
 * PostoFuncionalNotFoundException - Description
 * Created on 29/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PostoFuncionalNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PostoFuncionalNotFoundException(Long postofuncional_id) {
        super(String.format("Posto Funcional com id %s não existe no repositório", postofuncional_id));
    }
}
