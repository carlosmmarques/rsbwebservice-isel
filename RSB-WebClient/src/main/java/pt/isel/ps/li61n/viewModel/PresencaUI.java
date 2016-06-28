package pt.isel.ps.li61n.viewModel;

/**
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PresencaUI {

    //
    //Dados do elemento que realizou a presença
    //
    public final String idInternoElemento;
    public final String postoElemento;
    public final String[] presencas;

    // Url para página de detalhe
    public final String url_detalhesElemento;

    public PresencaUI(
            String idInternoElemento
            ,String postoElemento
            ,String[] presencas
            ,String urlDetalhesElemento
    ) {
        this.idInternoElemento = idInternoElemento;
        this.postoElemento = postoElemento;
        this.presencas = presencas;
        this.url_detalhesElemento = urlDetalhesElemento;
    }
}
