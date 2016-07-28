package pt.isel.ps.li61n.model.entities;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created on 31/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadeEstrutural extends Identity< Long > {

    @NotNull
    private String designacao;

    private Long tipo_id;

    private TipoUnidadeEstrutural tipo;

    private Long unidadeEstruturalMae_id;

    private UnidadeEstrutural unidadeEstruturalMae;

    private Collection< UnidadeEstrutural > subunidades;

    private Collection< Instalacao > instalacoes;

    public UnidadeEstrutural() {
    }

    public UnidadeEstrutural( Long id ) {
        this( id, null );
    }

    public UnidadeEstrutural( Long id, String designacao) {
        this.setId( id );
        this.designacao = designacao;
    }

    /////////////////////////////////////////////////////////////
    //
    //      Getters & Setters
    //
    /////////////////////////////////////////////////////////////

    public String getDesignacao(){
        return this.designacao;
    }

    public void setDesignacao( String designacao ){
        this.designacao = designacao;
    }

    public TipoUnidadeEstrutural getTipo(){ return tipo; }

    public void setTipo( TipoUnidadeEstrutural tipo ){
        this.tipo = tipo;
    }

    public Long getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(Long tipo_id) {
        this.tipo_id = tipo_id;
    }

    public Long getUnidadeEstruturalMae_id() {
        return unidadeEstruturalMae_id;
    }

    public void setUnidadeEstruturalMae_id( Long unidadeEstruturalMae_id ) {
        this.unidadeEstruturalMae_id = unidadeEstruturalMae_id;
    }

    public UnidadeEstrutural getUnidadeEstruturalMae() {
        return unidadeEstruturalMae;
    }

    public void setUnidadeEstruturalMae(UnidadeEstrutural unidadeEstruturalMae) {
        this.unidadeEstruturalMae = unidadeEstruturalMae;
    }

    public Collection< UnidadeEstrutural > getSubunidades() {
        return subunidades;
    }

    public void setSubunidades(Collection<UnidadeEstrutural> subunidades) {
        this.subunidades = subunidades;
    }

    public Collection<Instalacao> getInstalacoes() {
        return instalacoes;
    }

    public void setInstalacoes( Collection<Instalacao> instalacoes ) {
        this.instalacoes = instalacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnidadeEstrutural that = (UnidadeEstrutural) o;

        if (!designacao.equals(that.designacao)) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        return unidadeEstruturalMae != null ? unidadeEstruturalMae.equals(that.unidadeEstruturalMae) : that.unidadeEstruturalMae == null;

    }

    @Override
    public int hashCode() {
        int result = designacao.hashCode();
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (unidadeEstruturalMae != null ? unidadeEstruturalMae.hashCode() : 0);
        return result;
    }
}
