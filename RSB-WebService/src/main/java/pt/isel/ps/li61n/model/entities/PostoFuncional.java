package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;

/**
 * PostoFuncional - Posto Funcional ocupado por elementos no ambito do serviço operacional
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class PostoFuncional extends RsbAbstractEntity{
    /**
     * Designação - Designaçao do Posto Funcional (e.g., CPO, Chefe, etc.)
     */
    private String designacao;
    /**
     * Descrição - Descrição extensa do Posto Funcional.
     */
    private String descricao;

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
