package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IUnidadeEstruturalRepository;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class UnidadeEstruturalMemRepo
                implements IUnidadeEstruturalRepository {

    private HashMap< Long, UnidadeEstrutural > _repo;
    private long _repoSize;

    public UnidadeEstruturalMemRepo( ){
        _repo = new HashMap<>();
        _repoSize = 0;

        //populate();
    }

    @Override
    public Long insert( UnidadeEstrutural unidadeEstrutural ) {
        Long l = new Long( _repoSize );
        unidadeEstrutural.setId( l );
        _repo.put( l, unidadeEstrutural );
        _repoSize++;
        return l;
    }

    @Override
    public UnidadeEstrutural selectOne( Long id ) {
        return _repo.get( id );
    }

    @Override
    public Collection<UnidadeEstrutural> selectAll() {
        return _repo.values();
    }

    @Override
    public void delete(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Long aLong) {
        throw new NotImplementedException();
    }

    /*
    public void populate(){

    }
    */
}
