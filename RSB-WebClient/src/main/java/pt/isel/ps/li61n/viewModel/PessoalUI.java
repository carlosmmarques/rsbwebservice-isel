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

    public final String operacaoTroca;

    public final Long id;

    public PessoalUI(
            Long id
            ,String idInterno
            ,String nome
            ,String categoria
            ,String uri_pessoa
    ){
        this( id, idInterno, nome, categoria, uri_pessoa, -1 );
    }

    public PessoalUI(
            Long id
            ,String idInterno
            ,String nome
            ,String categoria
            ,String uri_pessoa
            ,int numMecanografico
    ){
        this.id = id;
        this.idInterno = idInterno;
        this.nome = nome;
        this.uri_pessoa = uri_pessoa;

        this.categoria = categoria;

        this.numMecanografico = numMecanografico;
        this.operacaoTroca = uri_pessoa + "/presencas";


    }
}
