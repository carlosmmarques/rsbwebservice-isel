package pt.isel.ps.li61n.model.entities;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Turno extends Identity< Long > {

    private String designacao;

    public Turno() {
    }

    public Turno( Long id, String designacao ){
        this.setId( id );
        this.designacao = designacao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        Turno turno = (Turno) o;

        return this.getId().equals( turno.getId() ) && designacao.equals( turno.designacao );

    }

    @Override
    public int hashCode() {
        return designacao != null ? designacao.hashCode() : 0;
    }

    @Override
    public String toString(){
        return this.designacao;
    }
}
