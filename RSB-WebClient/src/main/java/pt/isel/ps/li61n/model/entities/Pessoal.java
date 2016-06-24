package pt.isel.ps.li61n.model.entities;

/**
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Pessoal {

    private int numMecanografico;

    private String idInterno;

    // FK de PostoFuncional
    private String postoFuncionalId;


    public Pessoal() {
    }

    public Pessoal(
            int numMecanografico
            ,String idInterno
            ,String postoFuncionalId
    ) {
        this.numMecanografico = numMecanografico;
        this.idInterno = idInterno;
        this.postoFuncionalId = postoFuncionalId;
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
}
