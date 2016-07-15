package pt.isel.ps.li61n.viewModel;

/**
 * Created on 13/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PeriodoUI {

    public final String url;
    public final String dataInicio;
    public final String dataFim;

    public PeriodoUI(
            String url
            ,String dataInicio
            ,String dataFim
    ) {
        this.url = url;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
