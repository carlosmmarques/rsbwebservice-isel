package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;

/**
 * UnidadeEstrutural - Description
 * Created on 03/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 */
@Entity
public class UnidadeEstrutural {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String designacao;
    @ManyToOne(optional = true)
    //@JoinColumn(name = "unidadeEstruturalMae_id")
    private UnidadeEstrutural unidadeEstruturalMae;
    @ManyToOne(optional = false)
    //@JoinColumn(name = "tipoUnidadeEstrutural_id")
    private TipoUnidadeEstrutural tipoUnidadeEstrutural;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
