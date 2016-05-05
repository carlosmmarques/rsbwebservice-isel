package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;

/**
 * Formacao - Description
 * Created on 03/05/2016.
 *
 * @author
 * Carlos Marques - carlosmmarques@gmail.com
 * Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Formacao extends RsbAbstractEntity{

    private Integer validade;
    private String designacao;
    private String descricao;

    public Integer getValidade() {
        return validade;
    }

    public void setValidade(Integer validade) {
        this.validade = validade;
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
