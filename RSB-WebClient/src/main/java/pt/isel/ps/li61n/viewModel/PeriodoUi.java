package pt.isel.ps.li61n.viewModel;

/**
 * Created on 13/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PeriodoUi {

    public final Long id;
    public final String dataInicio;
    public final String dataFim;

    public PeriodoUi(
            Long id,
            String dataInicio
            ,String dataFim
    ) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    @Override
    public String toString(){
        return String.format( "%s - %s", dataInicio, dataFim );
    }
}
