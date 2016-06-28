package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPessoalRepository;
import pt.isel.ps.li61n.model.entities.Pessoal;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ConditionalOnMissingBean( IPessoalRepository.class )
@Component
public class PessoalMemRepo implements IPessoalRepository {

    private HashMap< Long, Pessoal > _repo;
    private long _repoSize;

    public PessoalMemRepo() {

        _repo = new HashMap<>();

        populate();
    }

    @Override
    public Long insert( Pessoal element ) {
        Long id = new Long( _repoSize );
        _repoSize++;
        element.setId( id );
        _repo.put( id, element );
        return id;
    }

    @Override
    public Pessoal selectOne( Long id ){
        return _repo.get( id );
    }

    @Override
    public Collection<Pessoal> selectAll() {
        return _repo.values();
    }

    @Override
    public void delete( Long integer) {
        throw new NotImplementedException();
    }

    @Override
    public void update( Long integer) {
        throw new NotImplementedException();
    }

    private void populate() {

        // ficticios
        for ( int i = 0; i < 10 ; i++ ) {
            int aux = i + i * 10 + i * 100+ i * 100 + i * 1_000 + i * 10_000 + i * 100_000;
            Pessoal p = new Pessoal(
                    aux
                    , "" + i
                    , "name " + aux
            );
            insert ( p );
        }

        //
        // pseudo-reais
        //
        String postoFuncional = "B.Sap";

        Pessoal bSap510 = new Pessoal(
            955615
            ,"510"
            ,postoFuncional
        );
        insert( bSap510 );

        Pessoal bSap503 = new Pessoal(
                955495
                ,"503"
                ,postoFuncional
        );
        insert( bSap503 );

        Pessoal bSap571 = new Pessoal(
                955603
                ,"571"
                ,postoFuncional
        );
        insert( bSap571 );

        Pessoal bSap666 = new Pessoal(
                955490
                ,"666"
                ,postoFuncional
        );
        insert( bSap666 );

        Pessoal bSap567 = new Pessoal(
                955511
                ,"567"
                ,postoFuncional
        );
        insert( bSap567 );

        Pessoal bSap900 = new Pessoal(
                764009
                ,"900"
                ,postoFuncional
        );
        insert( bSap900 );
    }
}