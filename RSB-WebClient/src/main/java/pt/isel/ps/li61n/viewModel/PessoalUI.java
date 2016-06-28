package pt.isel.ps.li61n.viewModel;

/**
 * Created on 20/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public class PessoalUI {

    public final String idInterno;

    public final String uri_pessoa;

    public final String nome;

    // corresponde à categoria.abreviatura
    public final String categoria;

    public final int numMecanografico;

    public PessoalUI(
            String idInterno
            ,String nome
            ,String categoria
            ,String uri_pessoa
    ){
        this.idInterno = idInterno;
        this.nome = nome;
        this.uri_pessoa = uri_pessoa;

        this.categoria = categoria;

        this.numMecanografico = -1;
    }

    public PessoalUI(
            String idInterno
            ,String nome
            ,String categoria
            ,String uri_pessoa
            ,int numMecanografico
    ){
        this.idInterno = idInterno;
        this.nome = nome;
        this.uri_pessoa = uri_pessoa;

        this.categoria = categoria;

        this.numMecanografico = numMecanografico;
    }
}
