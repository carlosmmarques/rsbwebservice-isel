package pt.isel.ps.li61n.viewModel;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created on 12/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class BasicInsertViewModel {

    @NotEmpty( message = ErrorsMsg.Geral.Designacao.NOT_NULL )
    @Size( max = 50, message = ErrorsMsg.Geral.Designacao.SIZE )
    private String designacao;

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
