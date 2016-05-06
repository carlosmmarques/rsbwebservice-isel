package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;

/**
 * Formacao - Registo de Formação de Elementos do Pessoal
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Formacao extends RsbAbstractEntity{

    /**
     * Validade da formação (numero de anos - Fraccionario)
     */
    private Float validade;
    /**
     * Designação da Formação
     */
    private String designacao;
    /**
     * Descrição da Formação
     */
    private String descricao;

    public Float getValidade() {
        return validade;
    }

    public void setValidade(Float validade) {
        this.validade = validade;
    }

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
}
