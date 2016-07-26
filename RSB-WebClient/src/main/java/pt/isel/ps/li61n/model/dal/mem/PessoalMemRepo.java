package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPessoalRepository;
import pt.isel.ps.li61n.model.entities.Elemento;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Profile( "mem" )
//@ConditionalOnMissingBean( IPessoalRepository.class )
@Component
public class PessoalMemRepo implements IPessoalRepository {

    private HashMap< Long, Elemento> _repo;
    private long _repoSize;

    public PessoalMemRepo() {
        _repo = new HashMap<>();

        populate();
    }

    @Override
    public Long insert( Elemento element ) {
        Long id = new Long( _repoSize );
        _repoSize++;
        element.setId( id );
        _repo.put( id, element );
        return id;
    }

    @Override
    public Elemento selectOne(Long id ){
        return _repo.get( id );
    }

    @Override
    public Collection<Elemento> selectAll() {
        return _repo.values();
    }

    @Override
    public void delete( Long integer) {
        throw new NotImplementedException();
    }

    @Override
    public void update( Elemento integer) {
        throw new NotImplementedException();
    }

    private void populate() {
        /*
        // ficticios
        for ( int i = 0; i < 10 ; i++ ) {
            int aux = i + i * 10 + i * 100+ i * 100 + i * 1_000 + i * 10_000 + i * 100_000;
            Elemento p = new Elemento(
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

        Elemento bSap510 = new Elemento(
            955615
            ,"510"
            ,postoFuncional
        );
        insert( bSap510 );

        Elemento bSap503 = new Elemento(
                955495
                ,"503"
                ,postoFuncional
        );
        insert( bSap503 );

        Elemento bSap571 = new Elemento(
                955603
                ,"571"
                ,postoFuncional
        );
        insert( bSap571 );

        Elemento bSap666 = new Elemento(
                955490
                ,"666"
                ,postoFuncional
        );
        insert( bSap666 );

        Elemento bSap567 = new Elemento(
                955511
                ,"567"
                ,postoFuncional
        );
        insert( bSap567 );

        Elemento bSap900 = new Elemento(
                764009
                ,"900"
                ,postoFuncional
        );
        insert( bSap900 );
        */
    }

    @Override
    public Elemento getOneByNumMecanografico(Integer numMecanografico) {
        throw new NotImplementedException();
    }
}