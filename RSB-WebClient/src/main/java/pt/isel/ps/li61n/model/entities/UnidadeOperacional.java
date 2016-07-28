package pt.isel.ps.li61n.model.entities;

import java.util.Collection;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadeOperacional extends Identity< Long >{

    private Instalacao instalacao;

    private String tipoUnidadeOperacional;

    private String designacao;

    private Boolean operacional;

    private Collection< Guarnicao > guarnicao;

    //
    //  Getters & Setters
    //

    public Instalacao getInstalacao(){
        return instalacao;
    }

    public void setInstalacao( Instalacao instalacao ) {
        this.instalacao = instalacao;
    }

    public String getTipoUnidadeOperacional() {
        return tipoUnidadeOperacional;
    }

    public void setTipoUnidadeOperacional(String tipoUnidadeOperacional) {
        this.tipoUnidadeOperacional = tipoUnidadeOperacional;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public Boolean getOperacional() {
        return operacional;
    }

    public void setOperacional(Boolean operacional) {
        this.operacional = operacional;
    }

    public Collection<Guarnicao> getGuarnicao() {
        return guarnicao;
    }

    public void setGuarnicao( Collection<Guarnicao> guarnicao ) {
        this.guarnicao = guarnicao;
    }
}
