package pt.isel.ps.li61n.model.dal;

import pt.isel.ps.li61n.model.entities.Presenca;

import java.util.Collection;

/**
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IPresencasRepository extends IRepository< Long, Presenca > {

    Collection< Presenca > selectPresencasByPeriodo( Long periodoId );

    Collection< Presenca > selectPresencasByPeriodoAndInstalacao( Long periodoId, Long InstalacaoId );
}
