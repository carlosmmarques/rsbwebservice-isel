package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * UnidadeOperacional - Unidades de operação das Instalações (Viaturas, equipamentos, etc.)
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class UnidadeOperacional extends EntidadeAbstractaComIdentificador {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String designacao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Boolean operacional;
    @ManyToOne
    @JoinColumn(name = "instalacao_id")
    private Instalacao instalacao;
    @ManyToOne
    @JoinColumn(name = "tipoUnidadeOperacional_id")
    private TipoUnidadeOperacional tipoUnidadeOperacional;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public UnidadeOperacional() {
    }

    /**
     * @return designação da unidade operacional
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação da unidade operacional
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return verdadeiro se a unidade está em condições de operacionalidade, falso caso contrário.
     */
    public Boolean getOperacional() {
        return operacional;
    }

    /**
     * @param operacional verdadeiro se a unidade está em condições de operacionalidade,
     *                    falso caso contrário.
     */
    public void setOperacional(Boolean operacional) {
        this.operacional = operacional;
    }

    /**
     * @return instalação a que a unidade operacional pertence
     */
    public Instalacao getInstalacao() {
        return instalacao;
    }

    /**
     * @param instalacao instalação a que a unidade operacional pertence
     */
    public void setInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
    }

    /**
     * @return tipo de unidade operacional
     */
    public TipoUnidadeOperacional getTipoUnidadeOperacional() {
        return tipoUnidadeOperacional;
    }

    /**
     * @param tipoUnidadeOperacional tipo de unidade operacional
     */
    public void setTipoUnidadeOperacional(TipoUnidadeOperacional tipoUnidadeOperacional) {
        this.tipoUnidadeOperacional = tipoUnidadeOperacional;
    }
}
