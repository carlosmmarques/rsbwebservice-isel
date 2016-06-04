package pt.isel.ps.li61n.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created on 27/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class UnidadeEstruturalLogic implements IUnidadeEstruturalLogic<UnidadeEstrutural, String > {

    private HashMap< String, UnidadeEstrutural > _repo;
    private int _repoSize;

    public UnidadeEstruturalLogic(){
        this._repo = new HashMap<>();
        this._repoSize = 0;
    }

    @Override
    public Collection< UnidadeEstrutural > getAll() {
        return _repo.values();
    }

    @Override
    public UnidadeEstrutural getOne( String id ) {
        return _repo.get( id );
    }

    @Override
    public UnidadeEstrutural create( UnidadeEstrutural element ) {
        String id = _repoSize + "";
        element.setId( id );
        _repo.put( id, element );
        _repoSize++;

        //logger <-Apagar
        System.out.println( "user criado" );

        return element;
    }
}