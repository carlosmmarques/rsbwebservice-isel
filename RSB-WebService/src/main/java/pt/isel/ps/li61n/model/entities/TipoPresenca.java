package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;

/**
 * TipoPresenca - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class TipoPresenca extends RsbAbstractEntity{
    private Boolean ausencia;
    private Boolean reforco;
    private String abreviatura;
    private String descricao;

    public Boolean getAusencia() {
        return ausencia;
    }

    public void setAusencia(Boolean ausencia) {
        this.ausencia = ausencia;
    }

    public Boolean getReforco() {
        return reforco;
    }

    public void setReforco(Boolean reforco) {
        this.reforco = reforco;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
