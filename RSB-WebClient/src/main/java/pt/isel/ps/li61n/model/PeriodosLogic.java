package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPeriodosRepository;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Periodo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

/**
 * Created on 13/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PeriodosLogic implements IPeriodosLogic {

    private IPeriodosRepository _repo;

    @Autowired
    public PeriodosLogic( IPeriodosRepository _repo ) {
        this._repo = _repo;
    }

    @Override
    public Collection< Periodo > getAll() {
        try {
            return _repo.selectAll();
        }
        catch( RepositoryException e ){
            throw new RuntimeException( e );
        }
    }

    @Override
    public Periodo getOne(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Long create( Periodo element ) throws PropertyEntityException {
        throw new NotImplementedException();
    }
}
