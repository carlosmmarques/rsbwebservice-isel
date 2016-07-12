package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITurnosRepository;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Turno;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class TurnosLogic implements ITurnosLogic {

    private ITurnosRepository _repo;

    @Autowired
    public TurnosLogic( ITurnosRepository _repo ) {
        this._repo = _repo;
    }

    @Override
    public Collection<Turno> getAll() {

        try {
            return _repo.selectAll();
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public Turno getOne(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Long create(Turno element) throws PropertyEntityException {
        throw new NotImplementedException();
    }
}
