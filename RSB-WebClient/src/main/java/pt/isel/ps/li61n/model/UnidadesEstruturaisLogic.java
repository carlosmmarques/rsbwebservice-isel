package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITiposUnidadeEstruturalRepository;
import pt.isel.ps.li61n.model.dal.IUnidadesEstruturaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created on 27/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class UnidadesEstruturaisLogic implements IUnidadesEstruturaisLogic {

    private IUnidadesEstruturaisRepository _ueRepo;
    private ITiposUnidadeEstruturalRepository _tipoUeRep;

    @Autowired
    public UnidadesEstruturaisLogic(
            IUnidadesEstruturaisRepository unidadeEstruturalRepository
            ,ITiposUnidadeEstruturalRepository tipoUnidadeEstruturalRepository
    ){
        _ueRepo = unidadeEstruturalRepository;
        _tipoUeRep = tipoUnidadeEstruturalRepository;
    }

    @Override
    public Collection< UnidadeEstrutural > getAll() {

        try {
            return _ueRepo.selectAll();
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public UnidadeEstrutural getOne( Long aLong ) {
        UnidadeEstrutural ue = null;
        try {
            ue = _ueRepo.selectOne( aLong );
        }
        catch( RepositoryException e ) {
            throw new RuntimeException( e );
        }
        return ue;
    }


    @Override
    public Long create( UnidadeEstrutural element ) {

        // atributos obrigatórios
        if( element.getDesignacao() == null
                || element.getTipo_id() == null
        ){
            throw new RuntimeException( "Campos por preencher" );
        }

        TipoUnidadeEstrutural tipo = null;
        try {
            tipo = _tipoUeRep.selectOne( element.getTipo_id() );
        }
        catch( RepositoryException e ){
            throw new RuntimeException( e );
        }

        //TODO: Criar exception para gerar página de erro
        if( tipo == null ){
            throw new RuntimeException( "Invalid tipo!" );
        }

        Long ueMae_id = element.getUnidadeEstruturalMae_id();

        if( ueMae_id != null ){
            //obter a mãe
            UnidadeEstrutural ueMae = null;
            try {
                ueMae = _ueRepo.selectOne( ueMae_id );
            }
            catch( RepositoryException e ){
                throw new RuntimeException( e );
            }

            if( ueMae != null ) { // <- TODO: validar se isto não acontecer

                // obter o nível hierárquico do tipo escolhido
                int nivelHierarquico = tipo.getNivelHierarquico();


                //obter o nivel hierarquico da mãe
                long nivelHierarquicoMae = ueMae.getTipo().getNivelHierarquico();

                // verificar que tem um nivel hierarquico superior (valor menor)
                if( nivelHierarquico <= nivelHierarquicoMae ) {
                    throw new RuntimeException( "Hierarquia incompativel" );
                }
                element.setUnidadeEstruturalMae( ueMae );
            }
            else{
                throw new RuntimeException(
                        "Não devia ser lançada! " +
                        "Existe ueMae_id que não está no repostitório.!!"
                );
            }
        }
        element.setTipo( tipo );

        try {
            return _ueRepo.insert( element );
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }


    @Override
    public Collection< TipoUnidadeEstrutural > getAllTipos() {
        System.out.println( "getAllTipos() Called" );
        try {
            return _tipoUeRep.selectAll();
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public Collection<UnidadeEstrutural> getAllByNivelHierarquico( int nivelHierarquico ) {
        //_ueRepo.selectAll().stream().filter( (a) -> { a.g} )
        try {
            return _ueRepo.selectAll();
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public TipoUnidadeEstrutural getTipo(Long id) {
        try {
            return _tipoUeRep.selectOne( id );
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public Collection< UnidadeEstrutural > getSubunidadesEstruturais( Long ueId ) {

        Collection< UnidadeEstrutural > result = null;

        try {
            result = _ueRepo.selectAll()
                                    .stream()
                                    .filter(
                                        el ->{
                                            UnidadeEstrutural ueMae = el.getUnidadeEstruturalMae();
                                            if( ueMae == null ){
                                                return false;
                                            }
                                            //System.out.println( "Ue mae: " + u );
                                            Long ueMaeId = ueMae.getId();
                                            System.out.println( "UE mae id : " + ueMaeId );
                                            return ueId.equals( ueMaeId );
                                    } )
                                    .collect( Collectors.toList() );
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
        return result;
    }

    @Override
    public Collection< Instalacao > getAllInstalacoes() {

        try {
            return _ueRepo.selectAllInstalacoes();
        }
        catch( RepositoryException e ){
            throw new RuntimeException( e );
        }
    }

    @Override
    public Collection<Instalacao> getAllInstalacoes( Long ueId ) {

        try {
            return  _ueRepo.selectAllInstalacoes( ueId );
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public Long createInstalacao( Instalacao novaInstalacao ) {
        Long id = null;
        try {
            id = _ueRepo.insertInstalacao( novaInstalacao );
        }
        catch (RepositoryException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public Instalacao getOneInstalacao(
                        Long unidadeEstruturalId
                        ,Long instalacaoId
    ) throws PropertyEntityException {

        // validar unidadeEstruturalId
        if( unidadeEstruturalId == null ){
            throw new PropertyEntityException(
                            "unidadeEstruturalId"
                            ,"O identificador da unidade estrutural é obrigatório."
            );
        }

        // validar instalacaoId
        if( instalacaoId == null ) {
            throw new PropertyEntityException(
                    "id"
                    , "O identificador da instalação é obrigatório."
            );
        }

        Instalacao result = null;
        try {
            result = _ueRepo.selectOneInstalcao( unidadeEstruturalId, instalacaoId );
        }
        catch( RepositoryException e ) {
            throw new RuntimeException( e );
        }

        return result;
    }
}