package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;
import java.util.List;

/**
 * ResponsabilidadeOperacional - Responsabilidade operacional assumida por um elemento do pessoal.
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class ResponsabilidadeOperacional extends RsbEntidadeAbstracta {

    @ManyToOne
    @JoinColumn(name = "tipoPresenca_id")
    private TipoPresenca tipoPresenca;
    @ManyToMany
    @JoinTable(
            name = "responsabilidade_formacao",
            joinColumns = @JoinColumn(name = "responsabilidade_operacional_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "formacao_id", referencedColumnName = "id"))
    private List<Formacao> formacoes;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String sigla;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;
    private Boolean dependeFactorElegibilidade;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String designacao;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public ResponsabilidadeOperacional() {
    }

    /**
     * @return tipo de presença em que o elemento assume esta responsabilidade operacional
     */
    public TipoPresenca getTipoPresenca() {
        return tipoPresenca;
    }

    /**
     * @param tipoPresenca tipo de presença em que o elemento assume esta responsabilidade operacional
     */
    public void setTipoPresenca(TipoPresenca tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }

    /**
     * @return lista de formações que habilitam o desempenho de tarefas desta responsabilidade operacional
     */
    public List<Formacao> getFormacoes() {
        return formacoes;
    }

    /**
     * @param formacoes lista de formações que habilitam o desempenho de tarefas desta responsabilidade operacional
     */
    public void setFormacoes(List<Formacao> formacoes) {
        this.formacoes = formacoes;
    }

    /**
     * @return sigla da responsabilidade operacional.
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * @param sigla sigla da responsabilidade operacional.
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    /**
     * @return tipo de serviço a ser realizado (Interno ou Externo).
     */
    public TipoServico getTipoServico() {
        return tipoServico;
    }

    /**
     * @param tipoServico tipo de serviço a ser realizado (Interno ou Externo).
     */
    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    /**
     * @return verdadeiro se esta Resp. Oper. depende do Factor de Elegibilidade, falso caso contrario.
     */
    public Boolean getDependeFactorElegibilidade() {
        return dependeFactorElegibilidade;
    }

    /**
     * @param dependeFactorElegibilidade verdadeiro se esta Resp. Oper. depende do Factor de Elegibilidade
     *                                   falso caso contrario.
     */
    public void setDependeFactorElegibilidade(Boolean dependeFactorElegibilidade) {
        this.dependeFactorElegibilidade = dependeFactorElegibilidade;
    }

    /**
     * @return designação da responsabilidade operacional.
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação da responsabilidade operacional.
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * TipoServico - Description
     * Created on 06/05/2016.
     *
     * @author Carlos Marques - carlosmmarques@gmail.com
     *         Tiago Venturinha - tventurinha@gmail.com
     */
    public enum TipoServico {
        EXTERNO,
        INTERNO
    }
}
