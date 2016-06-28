package pt.isel.ps.li61n.model.entities;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PostoFuncional extends Identity< Long > {

    private String designacao;
    private String descricao;

    public PostoFuncional() {
    }

    public PostoFuncional(String designacao, String descricao) {
        this.designacao = designacao;
        this.descricao = descricao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
