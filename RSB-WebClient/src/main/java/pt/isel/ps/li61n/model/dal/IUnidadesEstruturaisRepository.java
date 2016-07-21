package pt.isel.ps.li61n.model.dal;

import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import java.util.Collection;

/**
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IUnidadesEstruturaisRepository extends IRepository< Long, UnidadeEstrutural>{

    Collection< Instalacao > selectAllInstalacoes() throws RepositoryException;

    Collection< Instalacao > selectAllInstalacoes( Long unidadeEstruturalId ) throws RepositoryException;

    Long insertInstalacao( Instalacao newInstalacao ) throws RepositoryException;

    Instalacao selectOneInstalcao(Long unidadeEstruturalId, Long instalacaoId ) throws RepositoryException;
}
