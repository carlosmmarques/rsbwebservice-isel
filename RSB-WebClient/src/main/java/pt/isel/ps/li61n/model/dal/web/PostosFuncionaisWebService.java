package pt.isel.ps.li61n.model.dal.web;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPostosFuncionaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.PostoDto;
import pt.isel.ps.li61n.model.entities.PostoFuncional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.POSTOS_FUNCIONAIS_URL;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PostosFuncionaisWebService extends AbstractWebService implements IPostosFuncionaisRepository {

    @Override
    public Long insert(PostoFuncional element) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public PostoFuncional selectOne(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Collection< PostoFuncional > selectAll() throws RepositoryException {

        CompletableFuture< PostoDto[] > getPostos =
                                            RsbWebServiceAsync
                                                .callActionAndConvert(
                                                    PostoDto[].class
                                                    , POSTOS_FUNCIONAIS_URL
                                                );
        PostoDto[] dtos = null;

        try{
            dtos = getPostos.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        Collection< PostoFuncional > result = new ArrayList<>( dtos.length );
        for( PostoDto dto : dtos ) {

            PostoFuncional posto = new PostoFuncional();

            posto.setId( dto.id );
            posto.setDesignacao( dto.designacao );

            result.add( posto );
        }

        return result;
    }

    @Override
    public void delete(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public void update( PostoFuncional aLong ) {
        throw new NotImplementedException();
    }
}
