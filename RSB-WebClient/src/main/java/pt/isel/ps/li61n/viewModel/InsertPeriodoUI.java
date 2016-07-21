package pt.isel.ps.li61n.viewModel;

import javax.validation.constraints.Pattern;

/**
 * Created on 15/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class InsertPeriodoUI {

    @Pattern( regexp = "\\d{2}/\\d{2}/\\d{4}", message = ErrorsMsg.Periodo.DataInicio.PATTERN )
    private String dataInicio;

    @Pattern( regexp = "\\d{2}/\\d{2}/\\d{4}", message = ErrorsMsg.Periodo.DataFim.PATTERN )
    private String dataFim;

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
