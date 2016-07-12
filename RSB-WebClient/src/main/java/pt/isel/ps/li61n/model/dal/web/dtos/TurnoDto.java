package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TurnoDto {

    public final Long id;
    public final String dtInicioCiclo;
    public final String designacao;
    public final String hrInicioCiclo;

    public TurnoDto(
            Long id
            ,String dtInicioCiclo
            ,String designacao
            ,String hrInicioCiclo
    ) {
        this.id = id;
        this.dtInicioCiclo = dtInicioCiclo;
        this.designacao = designacao;
        this.hrInicioCiclo = hrInicioCiclo;
    }
}
