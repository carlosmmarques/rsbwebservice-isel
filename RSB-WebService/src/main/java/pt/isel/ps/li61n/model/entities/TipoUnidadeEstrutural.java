package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;

/**
 * TipoUnidadeEstrutural - Tipo de unidade estrutural (Regimento, Companhia, etc.)
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class TipoUnidadeEstrutural extends RsbAbstractEntity{

    private String designacao;
    private String descricao;
    private Integer nivelHierarquicoMaximo;

    /**
     * @return designação do tipo de Unidade Estrutural (nome curto).
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação do tipo de Unidade Estrutural (nome curto).
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return descrição do tipo da unidade Estrutural (extenso).
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao descrição do tipo da unidade Estrutural (extenso).
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return nível hierarquico máximo que um tipo de Unidade Estrutural pode aceitar.
     */
    public Integer getNivelHierarquicoMaximo() {
        return nivelHierarquicoMaximo;
    }

    /**
     * @param nivelHierarquicoMaximo nível hierarquico máximo que um tipo de Unidade Estrutural pode aceitar.
     */
    public void setNivelHierarquicoMaximo(Integer nivelHierarquicoMaximo) {
        this.nivelHierarquicoMaximo = nivelHierarquicoMaximo;
    }
}
