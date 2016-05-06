package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * UnidadeOperacional - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class UnidadeOperacional extends RsbAbstractEntity{
    private String designacao;
    private Boolean operacional;
    @ManyToOne
    @JoinColumn(name = "instalacao_id")
    private UnidadeOperacional unidadeOperacional;
    @ManyToOne
    @JoinColumn(name = "tipoUnidadeOperacional_id")
    private TipoUnidadeOperacional tipoUnidadeOperacional;

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public Boolean getOperacional() {
        return operacional;
    }

    public void setOperacional(Boolean operacional) {
        this.operacional = operacional;
    }

    public UnidadeOperacional getUnidadeOperacional() {
        return unidadeOperacional;
    }

    public void setUnidadeOperacional(UnidadeOperacional unidadeOperacional) {
        this.unidadeOperacional = unidadeOperacional;
    }

    public TipoUnidadeOperacional getTipoUnidadeOperacional() {
        return tipoUnidadeOperacional;
    }

    public void setTipoUnidadeOperacional(TipoUnidadeOperacional tipoUnidadeOperacional) {
        this.tipoUnidadeOperacional = tipoUnidadeOperacional;
    }
}
