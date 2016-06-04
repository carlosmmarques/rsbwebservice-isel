package pt.isel.ps.li61n.model.entities;

/**
 * Created on 31/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadeEstrutural {

    private String id;

    private String designacao;

    private String tipo;

    public UnidadeEstrutural(){}

    public UnidadeEstrutural( String id ){
        this.id = id;
    }

    public UnidadeEstrutural( String id, String designacao, String tipo ){
        this.id = id;
        this.designacao = designacao;
        this.tipo = tipo;
    }

    public String getDesignacao(){
        return this.designacao;
    }

    public void setDesignacao( String designacao ){
        this.designacao = designacao;
    }

    public String getTipo(){ return tipo; }

    public void setTipo( String tipo ){
        this.tipo = tipo;
    }

    public void setId( String id ){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    /*
    public boolean equals( UnidadeEstrutural other ){
        //TODO: Melhorar equals
        return this.getId().equalsIgnoreCase( other.id );
    }
    */
}
