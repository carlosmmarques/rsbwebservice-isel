package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Formacao - Registo de Formação de Elementos do Pessoal
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Formacao extends RsbAbstractEntity{

    private Float validade;
    private String designacao;
    private String descricao;
    @ManyToMany(mappedBy = "formacoes")
    private List<ResponsabilidadeOperacional> responsabilidadesOperacionais;

    /**
     * @return validade da formação (numero de anos - Fraccionario)
     */
    public Float getValidade() {
        return validade;
    }

    /**
     * @param validade validade da formação (numero de anos - Fraccionario)
     */
    public void setValidade(Float validade) {
        this.validade = validade;
    }

    /**
     * @return designação da Formação (texto curto)
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação da Formação (texto curto).
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return descrição da Formação.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao descrição da Formação.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return lista de responsabilidades operacionais dependentes desta formação
     */
    public List<ResponsabilidadeOperacional> getResponsabilidadesOperacionais() {
        return responsabilidadesOperacionais;
    }

    /**
     * @param responsabilidadesOperacionais lista de responsabilidades operacionais dependentes desta formação
     */
    public void setResponsabilidadesOperacionais(List<ResponsabilidadeOperacional> responsabilidadesOperacionais) {
        this.responsabilidadesOperacionais = responsabilidadesOperacionais;
    }
}
