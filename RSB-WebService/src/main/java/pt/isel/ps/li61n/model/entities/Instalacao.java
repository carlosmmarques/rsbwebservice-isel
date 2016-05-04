package pt.isel.ps.li61n.model.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Instalacao - Description
 * Created on 03/05/2016.
 *
 * @author
 * Carlos Marques - carlosmmarques@gmail.com
 * Tiago Venturinha - tventurinha@gmail.com
 */
public class Instalacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String designacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    private String descricao;
    private String localizacao;

}
