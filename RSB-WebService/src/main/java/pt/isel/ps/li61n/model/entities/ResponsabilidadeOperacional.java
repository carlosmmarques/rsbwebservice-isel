package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;

/**
 * ResponsabilidadeOperacional - Responsabilidade operacional assumida por um elemento do pessoal.
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class ResponsabilidadeOperacional extends RsbAbstractEntity{

    @ManyToOne
    @JoinColumn(name = "tipoPresenca_id")
    private TipoPresenca tipoPresenca;
    @ManyToOne
    @JoinColumn(name = "formacao_id")
    private Formacao formacao;
    private String sigla;
    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;
    private Boolean dependeFactorElegibilidade;
    private String designacao;

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
