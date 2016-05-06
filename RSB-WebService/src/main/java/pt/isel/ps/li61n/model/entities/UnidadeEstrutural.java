package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;

/**
 * UnidadeEstrutural - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class UnidadeEstrutural extends RsbAbstractEntity{

    private String designacao;
    @ManyToOne(optional = true)
    //@JoinColumn(name = "unidadeEstruturalMae_id")
    private UnidadeEstrutural unidadeEstruturalMae;
    @ManyToOne(optional = false)
    //@JoinColumn(name = "tipoUnidadeEstrutural_id")
    private TipoUnidadeEstrutural tipoUnidadeEstrutural;

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public UnidadeEstrutural getUnidadeEstruturalMae() {
        return unidadeEstruturalMae;
    }

    public void setUnidadeEstruturalMae(UnidadeEstrutural unidadeEstruturalMae) {
        this.unidadeEstruturalMae = unidadeEstruturalMae;
    }

    public TipoUnidadeEstrutural getTipoUnidadeEstrutural() {
        return tipoUnidadeEstrutural;
    }

    public void setTipoUnidadeEstrutural(TipoUnidadeEstrutural tipoUnidadeEstrutural) {
        this.tipoUnidadeEstrutural = tipoUnidadeEstrutural;
    }

}
