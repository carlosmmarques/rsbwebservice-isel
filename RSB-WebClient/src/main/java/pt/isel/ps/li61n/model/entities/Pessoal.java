package pt.isel.ps.li61n.model.entities;

/**
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Pessoal extends Identity< Long >{

    private int numMecanografico;

    private String idInterno;

    private String nome;

    // FK de PostoFuncional
    private PostoFuncional postoFuncional;

    private String postoFuncionalId;

    private Categoria categoria;

    public Pessoal() {
    }

    public Pessoal(
            int numMecanografico
            ,String idInterno
            ,String postoFuncionalId
            ,long id
    ) {
        this.numMecanografico = numMecanografico;
        this.idInterno = idInterno;
        this.postoFuncionalId = postoFuncionalId;
        this.setId( id );
    }

    public Pessoal(
            int numMecanografico
            ,String idInterno
            ,String postoFuncionalId
    ) {
        this.numMecanografico = numMecanografico;
        this.idInterno = idInterno;
        this.postoFuncionalId = postoFuncionalId;
        this.setId( new Long ( numMecanografico ) );
    }

    //
    // getters & setters
    //

    public int getNumMecanografico() {
        return numMecanografico;
    }

    public void setNumMecanografico( int numMecanografico ) {
        this.numMecanografico = numMecanografico;
    }

    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno( String idInterno ) {
        this.idInterno = idInterno;
    }

    public String getPostoFuncionalId() {
        return postoFuncionalId;
    }

    public void setPostoFuncionalId( String postoFuncionalId ) {
        this.postoFuncionalId = postoFuncionalId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PostoFuncional getPostoFuncional() {
        return postoFuncional;
    }

    public void setPostoFuncional( PostoFuncional postoFuncional ){
        this.postoFuncional = postoFuncional;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
