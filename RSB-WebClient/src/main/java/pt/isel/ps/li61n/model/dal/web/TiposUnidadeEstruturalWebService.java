package pt.isel.ps.li61n.model.dal.web;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITiposUnidadeEstruturalRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.TipoUnidadeEstruturalDto;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.TIPOS_UNIDADE_ESTRUTURAL_URL;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class TiposUnidadeEstruturalWebService extends AbstractWebService implements ITiposUnidadeEstruturalRepository {


    @Override
    public Long insert( TipoUnidadeEstrutural element ) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public TipoUnidadeEstrutural selectOne( Long tipoId ) throws RepositoryException {
        CompletableFuture< TipoUnidadeEstruturalDto > getTipo =
                RsbWebServiceAsync
                    .callActionAndConvert(
                        TipoUnidadeEstruturalDto.class
                        ,TIPOS_UNIDADE_ESTRUTURAL_URL + "/%s"
                        ,tipoId.toString()
                    );

        TipoUnidadeEstruturalDto dto = null;
        try {
            dto = getTipo.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        TipoUnidadeEstrutural result = convertFromDto( dto );

        return result;

    }

    @Override
    public Collection<TipoUnidadeEstrutural> selectAll() throws RepositoryException {
        CompletableFuture< TipoUnidadeEstruturalDto[] > getTipos =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                TipoUnidadeEstruturalDto[].class
                                ,TIPOS_UNIDADE_ESTRUTURAL_URL
                        )
                ;
        TipoUnidadeEstruturalDto[] dtos = null;

        try{
            dtos = getTipos.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        Collection< TipoUnidadeEstrutural > result = new ArrayList<>( dtos.length );
        for( TipoUnidadeEstruturalDto dto : dtos ) {

            TipoUnidadeEstrutural tipo = convertFromDto( dto );
            result.add( tipo );
        }
        return result;
    }


    public static TipoUnidadeEstrutural convertFromDto( TipoUnidadeEstruturalDto dto ) {
        TipoUnidadeEstrutural tipo = new TipoUnidadeEstrutural();

        tipo.setId( dto.id );
        tipo.setDesignacao( dto.designacao );

        String descricao = dto.descricao;
        if( descricao != null ){
            tipo.setDescricao(descricao );
        }

        Integer nivelHierarquico = dto.nivelHierarquicoMaximoMae;
        if( nivelHierarquico != null ){
            tipo.setNivelHierarquico( nivelHierarquico );
        }

        return tipo;
    }


    @Override
    public void delete(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public void update( TipoUnidadeEstrutural aLong ) {
        throw new NotImplementedException();
    }
}
