package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITiposUnidadeEstruturalRepository;
import pt.isel.ps.li61n.model.dal.IUnidadeEstruturalRepository;
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
public class UnidadeEstruturalLogic implements IUnidadeEstruturalLogic {

    private IUnidadeEstruturalRepository _ueRepository;
    private ITiposUnidadeEstruturalRepository  _tipoUeRepository;

    @Autowired
    public UnidadeEstruturalLogic(
            IUnidadeEstruturalRepository unidadeEstruturalRepository
            ,ITiposUnidadeEstruturalRepository tipoUnidadeEstruturalRepository
    ){
        _ueRepository = unidadeEstruturalRepository;
        _tipoUeRepository = tipoUnidadeEstruturalRepository;
    }

    @Override
    public Collection< UnidadeEstrutural > getAll() {

        /*
        Collection< UnidadeEstrutural > ues = _ueRepository.selectAll();
        if( ues != null ){
            for (UnidadeEstrutural ue : ues ) {
                //ue.setTipo( _tipoUeRepository.selectOne(  ));

            }
        }
        */
        return _ueRepository.selectAll();
    }

    @Override
    public UnidadeEstrutural getOne( Long aLong ) {
        UnidadeEstrutural ue = _ueRepository.selectOne( aLong );
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

        TipoUnidadeEstrutural tipo =  _tipoUeRepository.selectOne( element.getTipo_id() );

        //TODO: Criar exception para gerar página de erro
        if( tipo == null ){
            throw new RuntimeException( "Invalid tipo!" );
        }

        Long ueMae_id = element.getUnidadeEstruturalMae_id();

        if( ueMae_id != null ){
            //obter a mãe
            UnidadeEstrutural ueMae = _ueRepository.selectOne( ueMae_id );

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

        return _ueRepository.insert( element );
    }


    @Override
    public Collection< TipoUnidadeEstrutural > getAllTipos() {
        System.out.println( "getAllTipos() Called" );
        return _tipoUeRepository.selectAll();
    }

    @Override
    public Collection<UnidadeEstrutural> getAllByNivelHierarquico( int nivelHierarquico ) {
        //_ueRepository.selectAll().stream().filter( (a) -> { a.g} )
        return _ueRepository.selectAll();
    }

    @Override
    public TipoUnidadeEstrutural getTipo(Long id) {
        return _tipoUeRepository.selectOne( id );
    }

    @Override
    public Collection< UnidadeEstrutural > getSubunidadesEstruturais( Long ueId ) {
        return _ueRepository.selectAll()
                                .stream()
                                .filter( el -> ueId.equals( el.getUnidadeEstruturalMae_id() ) )
                                .collect( Collectors.toList() );
    }
}