package pt.isel.ps.li61n.model.dal.web;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITurnosRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.TurnoDto;
import pt.isel.ps.li61n.model.entities.Turno;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.TURNOS_URL;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class TurnosWebService extends AbstractWebService implements ITurnosRepository {

    @Override
    public Long insert(Turno element) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public Turno selectOne(Long aLong) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public Collection< Turno > selectAll() throws RepositoryException {
        CompletableFuture< TurnoDto[] > getTurnos =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                TurnoDto[].class
                                ,TURNOS_URL
                        )
                ;
        TurnoDto[] dtos = null;

        try{
            dtos = getTurnos.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        Collection< Turno > result = new ArrayList<>( dtos.length );
        for( TurnoDto dto : dtos ) {

            Turno turno = convertFromDto( dto );
            result.add( turno );
        }

        return result;
    }

    @Override
    public void delete(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public void update( Turno aLong) {
        throw new NotImplementedException();
    }

    public static Turno convertFromDto( TurnoDto dto ){
        Turno turno = new Turno();

        turno.setId( dto.id );
        turno.setDesignacao( dto.designacao );
        return  turno;
    }
}
