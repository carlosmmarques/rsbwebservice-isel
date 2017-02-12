package pt.isel.ps.li61n.model.dal;

import pt.isel.ps.li61n.model.entities.Guarnicao;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;

import java.util.Collection;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public interface IUnidadesOperacionaisRepository extends IRepository< Long, UnidadeOperacional > {

    Collection< Guarnicao > selectGuarnicao( Long unidadeOperacionalId );
}
