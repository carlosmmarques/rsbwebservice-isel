package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ICategoriasRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Categoria;
import pt.isel.ps.li61n.model.entities.Periodo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created on 10/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ConditionalOnMissingBean( ICategoriasRepository.class )
@Component
public class CategoriasRepository implements ICategoriasRepository {

    private HashMap< Long, Periodo > _repo;
    private long _repoSize;

    public CategoriasRepository() {

        _repo = new HashMap<>();
        _repoSize = 0;

        populate();

    }

    @Override
    public Long insert( Categoria element ) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public Categoria selectOne(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Categoria> selectAll() {
        return null;
    }

    @Override
    public void delete(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Long aLong) {
        throw new NotImplementedException();
    }

    private void populate() {


    }
}
