package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.Entity;

/**
 * PostoFuncional - Posto Funcional ocupado por elementos no ambito do serviço operacional
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class PostoFuncional extends EntidadeAbstractaComIdentificador {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String designacao;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String descricao;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public PostoFuncional() {
    }

    /**
     * @return Designaçao do Posto Funcional (e.g., CPO, Chefe, etc.)
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao Designaçao do Posto Funcional (e.g., CPO, Chefe, etc.)
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return Descrição extensa do Posto Funcional.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao Descrição extensa do Posto Funcional.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
