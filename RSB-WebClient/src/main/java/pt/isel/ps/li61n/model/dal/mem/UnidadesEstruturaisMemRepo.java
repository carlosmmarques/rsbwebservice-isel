package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IUnidadesEstruturaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Instalacao;
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
@ConditionalOnMissingBean( IUnidadesEstruturaisRepository.class )
@Component
public class UnidadesEstruturaisMemRepo implements IUnidadesEstruturaisRepository {

    private HashMap< Long, UnidadeEstrutural > _repo;
    private long _repoSize;

    public UnidadesEstruturaisMemRepo( ){
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

    @Override
    public Collection< Instalacao > selectAllInstalacoes() {
        throw new NotImplementedException();
    }

    @Override
    public Long insertInstalacao(Instalacao newInstalacao) {
        throw new NotImplementedException();
    }

    @Override
    public Instalacao selectOneInstalcao(Long unidadeEstruturalId, Long instalacaoId) throws RepositoryException {
        throw new NotImplementedException();
    }

    /*
    public void populate(){

    }
    */
}
