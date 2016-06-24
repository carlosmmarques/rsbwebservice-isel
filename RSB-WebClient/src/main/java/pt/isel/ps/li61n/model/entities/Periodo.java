package pt.isel.ps.li61n.model.entities;


import java.time.LocalDate;


/**
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Periodo extends Identity< Long > {

    private LocalDate DataInicio;
    private LocalDate DataFim;

    public LocalDate getDataInicio() {
        return DataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        DataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return DataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        DataFim = dataFim;
    }
}
