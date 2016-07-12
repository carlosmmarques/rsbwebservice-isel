package pt.isel.ps.li61n.model;

import pt.isel.ps.li61n.model.entities.Categoria;
import pt.isel.ps.li61n.model.entities.Elemento;
import pt.isel.ps.li61n.model.entities.PostoFuncional;

import java.util.Collection;
/**
 * Created on 24/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public interface IPessoalLogic extends ILogic< Long, Elemento >{


    // Elemento getOneById( Long id );

    Collection< Categoria > getAllCategorias();

    Collection<PostoFuncional > getAllPostosFuncionais();
}
