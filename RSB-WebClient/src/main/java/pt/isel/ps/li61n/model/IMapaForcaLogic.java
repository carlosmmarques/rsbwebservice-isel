package pt.isel.ps.li61n.model;

import com.sun.prism.PresentableState;
import pt.isel.ps.li61n.model.entities.Periodo;
import pt.isel.ps.li61n.model.entities.Presenca;

import java.util.Collection;

/**
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IMapaForcaLogic {

    Collection< Presenca > getPresencasByPeriodo( Long periodoId );

    Collection< Periodo > getAllPeriodos();

    Long insertPeriodo( Periodo novoPeriodo );

    Periodo getOnePeriodo( Long periodoId );

    Collection< Presenca > getPresencasByPeriodoAndInstalacao( Long periodoId , Long InstalacaoId );
}
