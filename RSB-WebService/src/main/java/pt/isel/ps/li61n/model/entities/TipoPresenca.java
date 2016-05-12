package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;

/**
 * TipoPresenca - Caracteriza o tipo de presença. Permite apurar se se trata de uma ausencia, ou em caso de presença,
 * qual a função desempenhada, Se é uma função de reforço por ausencia de um outro elemento, etc.
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

    /**
     * @return verdadeiro se se trata de uma ausencia, falso em caso contrário.
     */
    public Boolean getAusencia() {
        return ausencia;
    }

    /**
     * @param ausencia verdadeiro se se trata de uma ausencia
     *                 falso em caso contrário.
     */
    public void setAusencia(Boolean ausencia) {
        this.ausencia = ausencia;
    }

    /**
     * @return verdadeiro se o tipo de presença é de reforço, falso caso contrário.
     */
    public Boolean getReforco() {
        return reforco;
    }

    /**
     * @param reforco verdadeiro se o tipo de presença é de reforço
     *                falso caso contrário.
     */
    public void setReforco(Boolean reforco) {
        this.reforco = reforco;
    }

    /**
     * @return abreviatura do tipo de presença.
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura abreviatura do tipo de presença.
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @return descrição do tipo de presença.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao descrição do tipo de presença.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
