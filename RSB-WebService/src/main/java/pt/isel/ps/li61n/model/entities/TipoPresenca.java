package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;

/**
 * TipoPresenca - Caracteriza o tipo de presença. Permite apurar se se trata de uma ausencia, ou em caso de presença,
 * qual a função desempenhada, Se é uma função de reforço por ausencia de um outro elemento, etc.
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class TipoPresenca{

    @Id
    // Esta classe não herda de RSBAbstract pq o seu Id não é Inteiro.
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String id;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Boolean ausencia = false;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Boolean reforco = false;
    @ManyToOne(optional = true)
    @JoinColumn(name = "tipoPresencaEmReforco_id")
    private TipoPresenca tipoPresencaEmReforco;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String abreviatura;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String descricao;
    @Column(name = "eliminado", nullable = false, columnDefinition="BOOLEAN DEFAULT FALSE")
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    private Boolean eliminado = false;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public TipoPresenca() {
    }

    /**
     * @return identificador do tipo de presença
     */
    public String getId() {
        return id;
    }

    /**
     * @param id identificador do tipo de presença
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return verdadeiro se se trata de uma ausencia, falso em caso contrário.
     */
    public Boolean getAusencia() {
        return ausencia;
    }

    /**
     * @param ausencia verdadeiro se se trata de uma ausencia
     *                 falso em caso contrário.
     */
    public void setAusencia(Boolean ausencia) {
        this.ausencia = ausencia;
    }

    /**
     * @return verdadeiro se o tipo de presença é de reforço, falso caso contrário.
     */
    public Boolean getReforco() {
        return reforco;
    }

    /**
     * @param reforco verdadeiro se o tipo de presença é de reforço
     *                falso caso contrário.
     */
    public void setReforco(Boolean reforco) {
        this.reforco = reforco;
    }

    /**
     * @return tipo de presença quando a executar a mesma função em reforço
     */
    public TipoPresenca getTipoPresencaEmReforco() {
        return tipoPresencaEmReforco;
    }

    /**
     * @param tipoPresencaEmReforco tipo de presença quando a executar a mesma função em reforço
     */
    public void setTipoPresencaEmReforco(TipoPresenca tipoPresencaEmReforco) {
        this.tipoPresencaEmReforco = tipoPresencaEmReforco;
    }

    /**
     * @return abreviatura do tipo de presença.
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura abreviatura do tipo de presença.
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @return descrição do tipo de presença.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao descrição do tipo de presença.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    /**
     * @return Estado da Entidade (Activo / Inactivo)
     */
    public Boolean getEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado Estado da Entidade (Activo / Inactivo)
     */
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}
