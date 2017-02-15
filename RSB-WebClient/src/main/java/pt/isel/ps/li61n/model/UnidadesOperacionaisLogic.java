package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITiposUnidadesOperacionaisRepository;
import pt.isel.ps.li61n.model.dal.IUnidadesEstruturaisRepository;
import pt.isel.ps.li61n.model.dal.IUnidadesOperacionaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Guarnicao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;

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
    private ITiposUnidadesOperacionaisRepository _tpUnOpsRepo;
    private IUnidadesEstruturaisRepository _unEstRepo;

    @Autowired
    public UnidadesOperacionaisLogic(
                IUnidadesOperacionaisRepository unOpsRepo
                ,ITiposUnidadesOperacionaisRepository tpUnOpsRepo
    ){
        this._unOpsRepo = unOpsRepo;
        this._tpUnOpsRepo = tpUnOpsRepo;
    }

    @Override
    public Collection< UnidadeOperacional > getAll() {

        Collection< UnidadeOperacional > res = null;

        try {
            res =  _unOpsRepo.selectAll();
        }
        catch (RepositoryException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public UnidadeOperacional getOne( Long aLong ) {
        UnidadeOperacional uo = null;
        try {
            // obter detalhes de UnOp (inclui tipo e instalacao)
            uo = _unOpsRepo.selectOne( aLong );
        }
        catch( RepositoryException e ) {
            throw new RuntimeException( e );
        }
        return uo;
    }

    @Override
    public Long create( UnidadeOperacional element ) throws PropertyEntityException {

        // atributos obrigatórios
        if( element.getDesignacao() == null // TODO: Separar em throws diferentes para poder especificar campo
                || element.getTipoUnidadeOperacionalId() == null
                ){
            throw new RuntimeException( "Campos por preencher" );
        }

        TipoUnidadeOperacional tipo = null;
        try {
            tipo = _tpUnOpsRepo.selectOne( element.getTipoUnidadeOperacionalId() );
        }
        catch( RepositoryException e ){
            throw new PropertyEntityException( "tipo", e.getMessage() );
        }

        //TODO: Criar exception para gerar página de erro
        //if( tipo == null ){
        //    throw new RuntimeException( "Invalid tipo!" );
        //}

        element.setTipo( tipo );

        try {
            return _unOpsRepo.insert( element );
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public Collection< TipoUnidadeOperacional > getAllTipos() {

        Collection< TipoUnidadeOperacional > result = null;
        try {
            result =  _tpUnOpsRepo.selectAll();
        }
        catch (RepositoryException e) {
            e.printStackTrace();
        }
        return result;
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

        return _unOpsRepo.selectGuarnicao( unidadeOperacionalId );
    }

}
