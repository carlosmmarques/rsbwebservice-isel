package pt.isel.ps.li61n.viewModel;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created on 12/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class InsertInstalacaoViewModel extends BasicInsertViewModel{

    @NotEmpty( message = ErrorsMsg.Instalacao.Localizacao.NOT_NULL )
    @Size( max = 100, message = ErrorsMsg.Instalacao.Localizacao.SIZE )
    private String localizacao;

    @NotEmpty( message = ErrorsMsg.Instalacao.Descricao.NOT_NULL )
    @Size( max = 200, message = ErrorsMsg.Instalacao.Descricao.SIZE )
    private String descricao;

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
