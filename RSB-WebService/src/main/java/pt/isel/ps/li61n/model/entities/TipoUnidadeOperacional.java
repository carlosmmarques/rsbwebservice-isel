package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.Entity;

/**
 * TipoUnidadeOperacional - Tipo de unidade operacional (Viaturas como VUCI, VOPE, etc., ou outros equipamentos)
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class TipoUnidadeOperacional extends RsbEntidadeAbstracta {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String designacao;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String descricao;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public TipoUnidadeOperacional() {
    }

    /**
     * @return designação do tipo de unidade operacional (texto curto).
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação do tipo de unidade operacional (texto curto).
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return descrição do tipo de unidade operacional.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao descrição do tipo de unidade operacional.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
