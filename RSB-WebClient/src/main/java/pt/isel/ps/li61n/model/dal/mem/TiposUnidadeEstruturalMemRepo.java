package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITiposUnidadeEstruturalRepository;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created on 16/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ConditionalOnMissingBean( ITiposUnidadeEstruturalRepository.class )
@Component
public class TiposUnidadeEstruturalMemRepo implements ITiposUnidadeEstruturalRepository {

    private HashMap< Long, TipoUnidadeEstrutural > _repo;
    private long _repoSize;

    public TiposUnidadeEstruturalMemRepo( ){
        _repo = new HashMap<>();
        _repoSize = 1;

        populate();
    }

    @Override
    public Long insert( TipoUnidadeEstrutural element) {
        Long l = _repoSize;
        element.setId( l );
        _repo.put( l, element );
        return _repoSize++;
    }

    @Override
    public TipoUnidadeEstrutural selectOne(Long aLong) {
        return _repo.get( aLong );
    }

    @Override
    public Collection<TipoUnidadeEstrutural> selectAll() {
        return _repo.values();
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update( TipoUnidadeEstrutural aLong) {

    }

    private void populate(){
        TipoUnidadeEstrutural regimento = new TipoUnidadeEstrutural();
        regimento.setDesignacao( "Regimento" );
        regimento.setDescricao( "Unidade composta por dois ou mais batalhões" );
        regimento.setNivelHierarquico( 0 );
        insert( regimento );


        TipoUnidadeEstrutural batalhao = new TipoUnidadeEstrutural();
        batalhao.setDesignacao( "Batalhão" );
        batalhao.setDescricao( "Subdivisão de um regimento, formada por um número determinado de companhias" );
        batalhao.setNivelHierarquico( 1 );
        insert( batalhao );


        TipoUnidadeEstrutural companhia = new TipoUnidadeEstrutural();
        companhia.setDesignacao( "Companhia" );
        companhia.setDescricao( "Subunidade de nível inferior ao batalhão" );
        companhia.setNivelHierarquico( 2 );
        insert( companhia );


        TipoUnidadeEstrutural seccao = new TipoUnidadeEstrutural();
        seccao.setDesignacao( "Secção" );
        seccao.setDescricao( "unidades elementares que são subdivisão de um órgão ou de um serviço" );
        seccao.setNivelHierarquico( 3 );
        insert( seccao );
    }
}
