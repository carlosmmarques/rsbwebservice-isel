package pt.isel.ps.li61n.model.entities;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Instalacao extends Identity<Long> {

    private String localizacao;
    private String designacao;
    private String descricao;

    private Long unidadeEstruturalId;

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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

    public Long getUnidadeEstruturalId() {
        return unidadeEstruturalId;
    }

    public void setUnidadeEstruturalId(Long unidadeEstruturalId) {
        this.unidadeEstruturalId = unidadeEstruturalId;
    }
}
