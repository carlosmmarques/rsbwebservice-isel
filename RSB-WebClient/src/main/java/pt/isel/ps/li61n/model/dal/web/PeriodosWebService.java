package pt.isel.ps.li61n.model.dal.web;

import org.omg.SendingContext.RunTime;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPeriodosRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.PeriodoDto;
import pt.isel.ps.li61n.model.entities.Periodo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.PERIODOS_URL;

/**
 * Created on 15/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PeriodosWebService extends AbstractWebService implements IPeriodosRepository {


    @Override
    public Long insert(Periodo element) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public Periodo selectOne(Long aLong) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Periodo> selectAll() throws RepositoryException {

        CompletableFuture< PeriodoDto[] > getPeriodos = RsbWebServiceAsync
                                                            .callActionAndConvert(
                                                                PeriodoDto[].class
                                                                ,PERIODOS_URL
                                                            );

        PeriodoDto[] dtos = null;

        try {
            dtos = getPeriodos.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        // Converter dto -> Model

        Collection< Periodo > periodos = new ArrayList<>( dtos.length );
        for( PeriodoDto dto :  dtos ){
            Periodo p = convertFromDto( dto );
            periodos.add( p );
        }

        return periodos;
    }

    private static Periodo convertFromDto( PeriodoDto dto ) {
        Periodo p = new Periodo();
        p.setId( dto.id );
        p.setDataInicio( LocalDate.parse( dto.dtInicio ) );
        p.setDataFim( LocalDate.parse( dto.dtFim ) );
        return  p;
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
