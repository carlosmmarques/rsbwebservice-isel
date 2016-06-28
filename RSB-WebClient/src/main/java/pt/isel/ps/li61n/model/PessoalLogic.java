package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pt.isel.ps.li61n.model.dal.IPessoalRepository;
import pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync;

import pt.isel.ps.li61n.model.entities.Pessoal;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class PessoalLogic implements IPessoalLogic {

    private IPessoalRepository _pessoal;

    @Autowired
    public PessoalLogic( IPessoalRepository pessoal ) {
        this._pessoal = pessoal;
        System.out.println( "Using " + pessoal.getClass().getSimpleName() );
    }

    @Override
    public Collection< Pessoal > getAll() {
        return _pessoal.selectAll();
    }

    @Override
    public Pessoal getOne( Long id ) {

        Pessoal elemento = _pessoal.selectOne( id );

        /*
        Pessoal pessoal = new Pessoal(
                elemento.  int numMecanografico
        ,String idInterno
        ,String postoFuncionalId
        ,long id
        );
        */
        //return _pessoal.selectOne( id );
        return elemento;
    }

    @Override
    public Long create( Pessoal element ) {
        return _pessoal.insert( element );
    }
}