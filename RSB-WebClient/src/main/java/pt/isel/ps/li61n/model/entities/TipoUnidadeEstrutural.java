package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created on 01/06/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public class TipoUnidadeEstrutural extends Identity< Long > {

    private String designacao;

    private String descricao;

    private Integer nivelHierarquico;

    public TipoUnidadeEstrutural(){}

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

    public Integer getNivelHierarquico() {
        return nivelHierarquico;
    }

    public void setNivelHierarquico(Integer nivelHierarquico) {
        this.nivelHierarquico = nivelHierarquico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoUnidadeEstrutural that = (TipoUnidadeEstrutural) o;

        if (!this.getId().equals(that.getId())) return false;
        if (!designacao.equals(that.designacao)) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;
        return nivelHierarquico != null ? nivelHierarquico.equals(that.nivelHierarquico) : that.nivelHierarquico == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + designacao.hashCode();
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (nivelHierarquico != null ? nivelHierarquico.hashCode() : 0);
        return result;
    }



}