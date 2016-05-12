package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;

/**
 * Instalacao - Identifica uma instalação (localização específica)
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Instalacao extends RsbAbstractEntity{

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidadeEstrutural_id")
    private UnidadeEstrutural unidadeEstrutural;
    private String designacao;
    private String descricao;
    private String localizacao;

    /**
     * @return unidade estrutural a que esta instalação é adstrita.
     */
    public UnidadeEstrutural getUnidadeEstrutural() {
        return unidadeEstrutural;
    }

    /**
     * @param unidadeEstrutural unidade estrutural a que esta instalação é adstrita.
     */
    public void setUnidadeEstrutural(UnidadeEstrutural unidadeEstrutural) {
        this.unidadeEstrutural = unidadeEstrutural;
    }

    /**
     * @return designação desta instalação (texto curto, e.g., Quartel de Alvalade).
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação desta instalação (texto curto, e.g., Quartel de Alvalade).
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return descrição desta Instalação (texto mais extenso).
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao descrição desta Instalação (texto mais extenso).
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return identificação da localização.
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * @param localizacao identificação da localização.
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
