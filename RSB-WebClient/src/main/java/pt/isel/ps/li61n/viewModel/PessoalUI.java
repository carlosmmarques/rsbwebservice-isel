package pt.isel.ps.li61n.viewModel;

/**
 * Created by Demo on 20/05/2016.
 */
public class PessoalUI {

    public final String idInterno;

    public final String uri_pessoa;

    public final String name;

    // corresponde ao identificador PostoFuncional.designacao
    //public final String postoFuncional;

    public PessoalUI( String idInterno, String name, String uri_pessoa ){
        this.idInterno = idInterno;
        this.name = name;
        this.uri_pessoa = uri_pessoa;
    }
}
