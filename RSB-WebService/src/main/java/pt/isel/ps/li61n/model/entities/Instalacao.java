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

    /**
     * A unidade estrutural a que esta instalação é adstrita
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "unidadeEstrutural_id")
    private UnidadeEstrutural unidadeEstrutural;
    /**
     * A designação desta instalação (ex.: Quartel de Alvalade)
     */
    private String designacao;
    /**
     * A descrição desta Instalação (texto mais extenso)
     */
    private String descricao;
    /**
     * A localização. Para já é uma simples String de texto com a identificação da localização.
     */
    private String localizacao;

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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }


}
