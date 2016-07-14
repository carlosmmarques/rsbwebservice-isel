package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;

/**
 * Instalacao - Identifica uma instalação (localização específica)
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Instalacao extends EntidadeAbstractaComIdentificador {

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidadeEstrutural_id", nullable = false)
    private UnidadeEstrutural unidadeEstrutural;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String designacao;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String descricao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String localizacao;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public Instalacao() {
    }

    /**
     * @return unidade estrutural a que esta instalação é adstrita.
     */
    public UnidadeEstrutural getUnidadeEstrutural() {
        return unidadeEstrutural;
    }

    /**
     * @param unidadeEstrutural unidade estrutural a que esta instalação é adstrita.
     */
    public void setUnidadeEstrutural(UnidadeEstrutural unidadeEstrutural) {
        this.unidadeEstrutural = unidadeEstrutural;
    }

    /**
     * @return designação desta instalação (texto curto, e.g., Quartel de Alvalade).
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação desta instalação (texto curto, e.g., Quartel de Alvalade).
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return descrição desta Instalação (texto mais extenso).
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao descrição desta Instalação (texto mais extenso).
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return identificação da localização.
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * @param localizacao identificação da localização.
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
