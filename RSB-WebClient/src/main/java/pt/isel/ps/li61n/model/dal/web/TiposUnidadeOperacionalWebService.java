package pt.isel.ps.li61n.model.dal.web;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITiposUnidadesOperacionaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.TipoUnidadeOperacionalDto;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.TIPOS_UNIDADES_OPERACIONAIS_URL;

/**
 * Created on 12/02/2017.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

@Component
public class TiposUnidadeOperacionalWebService extends AbstractWebService implements ITiposUnidadesOperacionaisRepository {
    @Override
    public Long insert(TipoUnidadeOperacional element) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public TipoUnidadeOperacional selectOne( Long tipoId ) throws RepositoryException {

        CompletableFuture< TipoUnidadeOperacionalDto > getTipo =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                TipoUnidadeOperacionalDto.class
                                ,TIPOS_UNIDADES_OPERACIONAIS_URL + "/%s"
                                ,tipoId.toString()
                        );

        TipoUnidadeOperacionalDto dto = null;
        try {
            dto = getTipo.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        TipoUnidadeOperacional result = convertFromDto( dto );

        return result;

    }

    @Override
    public Collection< TipoUnidadeOperacional > selectAll() throws RepositoryException {
        CompletableFuture< TipoUnidadeOperacionalDto[] > getTipos =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                TipoUnidadeOperacionalDto[].class
                                ,TIPOS_UNIDADES_OPERACIONAIS_URL
                        )
                ;
        TipoUnidadeOperacionalDto[] dtos = null;

        try{
            dtos = getTipos.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        Collection< TipoUnidadeOperacional > result = new ArrayList<>( dtos.length );
        for( TipoUnidadeOperacionalDto dto : dtos ) {

            TipoUnidadeOperacional tipo = convertFromDto( dto );
            result.add( tipo );
        }
        return result;
    }


    public static TipoUnidadeOperacional convertFromDto( TipoUnidadeOperacionalDto dto ) {
        TipoUnidadeOperacional tipo = new TipoUnidadeOperacional();

        tipo.setId( dto.id );
        tipo.setDesignacao( dto.designacao );

        String descricao = dto.descricao;
        if( descricao != null ){
            tipo.setDescricao( descricao );
        }

        return tipo;
    }

    @Override
    public void delete(Long aLong) { throw new NotImplementedException(); }

    @Override
    public void update(TipoUnidadeOperacional id) { throw new NotImplementedException(); }
}
