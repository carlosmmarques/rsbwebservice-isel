package pt.isel.ps.li61n.model;

import pt.isel.ps.li61n.model.entities.Guarnicao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;

import java.util.Collection;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IUnidadesOperacionaisLogic extends ILogic< Long, UnidadeOperacional > {

    Collection<TipoUnidadeOperacional> getAllTipos();

    Collection< UnidadeOperacional > getAllByInstalacao( Long instalacaoId );

    Collection< Guarnicao > getGuarnicao( Long unidadeOperacionalId );
}
