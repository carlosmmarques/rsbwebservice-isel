package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IUnidadesOperacionaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Guarnicao;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class UnidadesOperacionaisLogic implements IUnidadesOperacionaisLogic{

    private IUnidadesOperacionaisRepository _unOpsRepo;


    @Autowired
    public UnidadesOperacionaisLogic( IUnidadesOperacionaisRepository _unOpsRepo ){
        this._unOpsRepo = _unOpsRepo;
    }

    @Override
    public Collection<UnidadeOperacional> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public UnidadeOperacional getOne( Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Long create( UnidadeOperacional element ) throws PropertyEntityException {
        throw new NotImplementedException();
    }

    @Override
    public Collection< UnidadeOperacional > getAllByInstalacao( Long instalacaoId ) {
        Collection< UnidadeOperacional > unOps = null;
        try {
            unOps = _unOpsRepo.selectAll();
        }
        catch( RepositoryException e ){
            throw new RuntimeException( e );
        }
        return unOps.stream()
                        .filter( el -> el.getInstalacao().getId().equals( instalacaoId ) )
                        .collect( Collectors.toList() );
    }

    @Override
    public Collection<Guarnicao> getGuarnicao( Long unidadeOperacionalId ) {

        return _unOpsRepo.selectGuarnicaoOfUnidadeOperacional( unidadeOperacionalId );
    }
}
