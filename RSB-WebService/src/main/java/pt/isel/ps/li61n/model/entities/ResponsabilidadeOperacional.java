package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * ResponsabilidadeOperacional - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class ResponsabilidadeOperacional extends RsbAbstractEntity{
    private String sigla;
    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;
    private Boolean dependeFactorElegibilidade;
    private String designacao;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Boolean getDependeFactorElegibilidade() {
        return dependeFactorElegibilidade;
    }

    public void setDependeFactorElegibilidade(Boolean dependeFactorElegibilidade) {
        this.dependeFactorElegibilidade = dependeFactorElegibilidade;
    }

    public String getDesignacao() {
        return designacao;
    }

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
