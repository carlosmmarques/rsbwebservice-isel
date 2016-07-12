package pt.isel.ps.li61n.model;

import pt.isel.ps.li61n.model.dal.IRepository;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import java.util.Collection;

/**
 * Created on 27/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public interface IUnidadesEstruturaisLogic extends ILogic< Long, UnidadeEstrutural> {

    Collection< TipoUnidadeEstrutural > getAllTipos();

    Collection< UnidadeEstrutural > getAllByNivelHierarquico( int nivelHierarquico );

    TipoUnidadeEstrutural getTipo( Long id );

    Collection< UnidadeEstrutural > getSubunidadesEstruturais( Long ueId );

    Collection< Instalacao > getAllInstalacoes(  );

}
