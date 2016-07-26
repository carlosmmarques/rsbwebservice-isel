package pt.isel.ps.li61n.model.dal.web;

import org.asynchttpclient.Param;
import org.omg.SendingContext.RunTime;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPeriodosRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.PeriodoDto;
import pt.isel.ps.li61n.model.dal.web.exceptions.WebServiceException;
import pt.isel.ps.li61n.model.entities.Periodo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.PERIODOS_URL;
import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.PERIODO_URL;
import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.PRESENCAS_URL;

/**
 * Created on 15/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PeriodosWebService extends AbstractWebService implements IPeriodosRepository {


    @Override
    public Long insert( Periodo novoPeriodo ) throws RepositoryException {
        Long result = null;

        if( novoPeriodo != null ) {

            List<Param> parameters = new LinkedList<>();

            // Data inicio - obrigatório
            String dataInicioValue = novoPeriodo.getDataInicio().toString();
            Param dataInicio = new Param("datainicio", dataInicioValue);
            parameters.add(dataInicio);

            // Data inicio - obrigatório
            String dataFimValue = novoPeriodo.getDataFim().toString();
            Param datafim = new Param( "datafim", dataFimValue );
            parameters.add( datafim );
            try {
                PeriodoDto dto = RsbWebServiceAsync
                        .callPostActionAndConvert(
                                PeriodoDto.class
                                ,parameters
                                ,PERIODOS_URL
                        )
                        .get(); //TODO: Retornar o objecto?

                result = dto.id;
            }
            catch( InterruptedException | ExecutionException e ) {
                Throwable exception = e.getCause();
                if( exception.getClass().equals( WebServiceException.class ) ){
                    WebServiceException webServiceException = (WebServiceException) exception;
                    throw new RepositoryException( webServiceException.getMessage() );
                }
                else {
                    throw new RuntimeException( e );
                }
            }
        }
        return result;
    }

    @Override
    public Periodo selectOne( Long periodoId ) throws RepositoryException {

        CompletableFuture< PeriodoDto > getPeriodo = RsbWebServiceAsync
                                                        .callActionAndConvert(
                                                            PeriodoDto.class
                                                            ,PERIODO_URL
                                                            ,periodoId.toString()
                                                        );
        PeriodoDto dto = null;

        try {
            dto = getPeriodo.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        // Converter dto -> Model
        Periodo p = convertFromDto( dto );

        return p;
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

    public static Periodo convertFromDto( PeriodoDto dto ) {
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
    public void update( Periodo aLong) {
        throw new NotImplementedException();
    }
}
