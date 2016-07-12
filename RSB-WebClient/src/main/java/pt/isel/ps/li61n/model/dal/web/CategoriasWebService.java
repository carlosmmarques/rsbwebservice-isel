package pt.isel.ps.li61n.model.dal.web;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ICategoriasRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.CategoriaDto;
import pt.isel.ps.li61n.model.entities.Categoria;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.CATEGORIAS_URL;

/**
 * Created on 10/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class CategoriasWebService  extends AbstractWebService implements ICategoriasRepository {



    @Override
    public Long insert( Categoria element ) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public Categoria selectOne(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Collection< Categoria > selectAll() throws RepositoryException {

        CompletableFuture< CategoriaDto[] > getCategorias =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                CategoriaDto[].class
                                , CATEGORIAS_URL
                        )
                ;

        CategoriaDto[] dtos = null;
        try{
            dtos = getCategorias.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        Collection< Categoria > result = new ArrayList<>( dtos.length );
        for (CategoriaDto dto : dtos ) {

            Categoria categoria = new Categoria();

            categoria.setId( dto.id );
            categoria.setDescricao( dto.descrição );
            categoria.setAbreviatura( dto.abreviatura );

            result.add( categoria );

        }

        return result;
    }

    @Override
    public void delete(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Long aLong) {
        throw new NotImplementedException();
    }
}
