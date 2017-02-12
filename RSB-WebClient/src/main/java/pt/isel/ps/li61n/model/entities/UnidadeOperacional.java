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

    private Long instalacaoId;

    private Long tipoUnidadeOperacionalId;

    private String designacao;

    private boolean operacional;

    private Collection< Guarnicao > guarnicao;

    // ADDED
    private boolean ePrioritario;

    private TipoUnidadeOperacional tipo;

    //
    //  Getters & Setters
    //

    public Instalacao getInstalacao(){
        return instalacao;
    }

    public void setInstalacao( Instalacao instalacao ) {
        this.instalacao = instalacao;
    }

    public Long getTipoUnidadeOperacionalId() {
        return tipoUnidadeOperacionalId;
    }

    public void setTipoUnidadeOperacionalId(Long tipoUnidadeOperacionalId) {
        this.tipoUnidadeOperacionalId = tipoUnidadeOperacionalId;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public boolean getOperacional() {
        return operacional;
    }

    public void setOperacional( boolean operacional ) {
        this.operacional = operacional;
    }

    public Collection<Guarnicao> getGuarnicao() {
        return guarnicao;
    }

    public void setGuarnicao( Collection<Guarnicao> guarnicao ) {
        this.guarnicao = guarnicao;
    }


    // Added
    public boolean isePrioritario() {
        return ePrioritario;
    }

    public void setePrioritario( boolean ePrioritario ) {
        this.ePrioritario = ePrioritario;
    }

    public TipoUnidadeOperacional getTipo() {
        return tipo;
    }

    public void setTipo(TipoUnidadeOperacional tipo) {
        this.tipo = tipo;
    }

    public Long getInstalacaoId() {
        return instalacaoId;
    }

    public void setInstalacaoId(Long instalacaoId) {
        this.instalacaoId = instalacaoId;
    }
}
