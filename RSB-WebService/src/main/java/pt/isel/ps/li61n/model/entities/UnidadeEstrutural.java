package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * UnidadeEstrutural - Unidade estrutural de comando (Regimento, Batalhão, Companhia, Secção, etc.)
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class UnidadeEstrutural extends RsbAbstractEntity{

    private String designacao;
    @ManyToOne(optional = true)
    @JoinColumn(name = "unidadeEstruturalMae_id")
    private UnidadeEstrutural unidadeEstruturalMae;
    @ManyToOne(optional = false)
    @JoinColumn(name = "tipoUnidadeEstrutural_id")
    private TipoUnidadeEstrutural tipoUnidadeEstrutural;
    private Integer nivelHierarquico;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public UnidadeEstrutural() {
    }

    /**
     * @return designação da unidade estrutural (nome curto)
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação da unidade estrutural (nome curto)
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return unidade estrutural mãe, se existir
     */
    public UnidadeEstrutural getUnidadeEstruturalMae() {
        return unidadeEstruturalMae;
    }

    /**
     * @param unidadeEstruturalMae unidade estrutural mãe, se existir
     */
    public void setUnidadeEstruturalMae(UnidadeEstrutural unidadeEstruturalMae) {
        this.unidadeEstruturalMae = unidadeEstruturalMae;
    }

    /**
     * @return tipo da unidade estrutural
     */
    public TipoUnidadeEstrutural getTipoUnidadeEstrutural() {
        return tipoUnidadeEstrutural;
    }

    /**
     * @param tipoUnidadeEstrutural tipo da unidade estrutural
     */
    public void setTipoUnidadeEstrutural(TipoUnidadeEstrutural tipoUnidadeEstrutural) {
        this.tipoUnidadeEstrutural = tipoUnidadeEstrutural;
    }

    /**
     * @return nível Hierarquico desta unidade estrutural
     */
    public Integer getNivelHierarquico() {
        return nivelHierarquico;
    }

    /**
     * @param nivelHierarquico nível Hierarquico desta unidade estrutural
     */
    public void setNivelHierarquico(Integer nivelHierarquico) {
        this.nivelHierarquico = nivelHierarquico;
    }
}
