package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;

/**
 * Instalacao - Description
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
