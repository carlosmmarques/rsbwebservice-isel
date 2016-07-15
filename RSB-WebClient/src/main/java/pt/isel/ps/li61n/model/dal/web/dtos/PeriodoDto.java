package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 14/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PeriodoDto {

    public final Long id;
    public final String dtInicio;
    public final String dtFim;

    public PeriodoDto( Long id, String dtInicio, String dtFim ) {
        this.id = id;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }
}
