package pt.isel.ps.li61n.model.dal.web;

import org.asynchttpclient.Param;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPresencasRepository;
import pt.isel.ps.li61n.model.dal.web.dtos.*;
import pt.isel.ps.li61n.model.entities.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.PRESENCAS_URL;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PresencasWebService extends AbstractWebService implements IPresencasRepository {

    public PresencasWebService() {
        System.out.println( "New PrsencasWebService" );
    }

    @Override
    public Long insert( Presenca element ) {
        throw new NotImplementedException();
    }

    @Override
    public Presenca selectOne( Long aLong ) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Presenca> selectAll() {

        CompletableFuture< PresencaDto[] > getPresencas;

        getPresencas = RsbWebServiceAsync
                        .callActionAndConvert(
                            PresencaDto[].class
                            ,"/presenca"
                        );

        Collection< Presenca > result = convertFromDto( getPresencas );
        return result;
    }

    private static Elemento getElementoPessoal( String uri, HashMap<String, Elemento> cachePessoal ) {
        Elemento result = cachePessoal.get( uri );
        if( result == null  ){
            // Obter o elemento
            ElementoDto dto = getResource( uri, ElementoDto.class );
            if( dto != null ){
                result = PessoalWebService.convertDtoToModel( dto, false );

                // colocar em cache
                cachePessoal.put( uri, result );
            }
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

    @Override
    public Collection<Presenca> selectPresencasByPeriodo( Long periodoId ) {

        List< Param > parameters = new LinkedList<>();

        parameters.add( new Param( "periodo_id", periodoId.toString() ) );

        Collection< Presenca > result =  getPresencas( parameters );
        return result;
    }

    @Override
    public Collection<Presenca> selectPresencasByPeriodoAndInstalacao(Long periodoId, Long instalacaoId ) {
        List< Param > parameters = new LinkedList<>();

        parameters.add( new Param( "periodo_id", periodoId.toString() ) );
        parameters.add( new Param ( "instalacao_id", instalacaoId.toString() ) );

        Collection< Presenca > result =  getPresencas( parameters );
        return result;
    }

    private Collection< Presenca > getPresencas( List< Param > parameters ){
        CompletableFuture< PresencaDto[] > getPresencas =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                PresencaDto[].class
                                ,parameters
                                ,PRESENCAS_URL
                        );

        Collection< Presenca > result = convertFromDto( getPresencas );
        return  result;
    }







    private static Presenca convertFromDto( PresencaDto dto ){
        Presenca p = new Presenca();
        p.setId( dto.id );
        p.setData( LocalDate.parse( dto.data ) );
        p.setHoraInicio( LocalTime.parse( dto.horaInicio ) );
        p.setNumHoras( dto.numHoras );
        return p;
    }

    private static Collection<Presenca> convertFromDto( CompletableFuture< PresencaDto[] > getPresencas ){

        PresencaDto[] dtos = null;

        try{
            dtos = getPresencas.get();
        }
        catch (InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e ); // TODO: Tratar o erro!
        }

        Collection< Presenca > result = new ArrayList<>( dtos.length );

        // V2 -> Cache
        HashMap< String, Elemento> cachePessoal = new HashMap<>();
        HashMap< String, PostoFuncional> cachePosto = new HashMap<>();
        HashMap< String, Instalacao > cacheInstalacao = new HashMap<>();
        HashMap< String, Turno > cacheTurno = new HashMap<>();
        HashMap< String, Periodo > cachePeriodo = new HashMap<>();

        // Converter para Presencas
        for( PresencaDto dto : dtos ){

            Presenca p = convertFromDto( dto );

            // obter periodo
            String uriPeriodo = dto.uri_periodo;
            Periodo periodo = cachePeriodo.get( uriPeriodo );
            if( periodo == null ){

                PeriodoDto periodoDto = RsbWebServiceAsync
                                                    .getResource(
                                                        uriPeriodo
                                                        ,PeriodoDto.class );

                periodo = PeriodosWebService.convertFromDto( periodoDto );

                cachePeriodo.put( uriPeriodo, periodo );
            }

            p.setPeriodo( periodo );

            // obter o elemento do registo de presença
            Elemento elemento = getElementoPessoal(
                    dto.uri_elementodopessoal
                    ,cachePessoal
            );

            p.setElemento( elemento );

            // Tipo de presença
            p.setTipoPresencaId( dto.tipopresencaefectiva );

            //
            // Elemento de reforço/reforçado
            // (assumido que não pode existir um registo com os dois "tipos" de elementos)
            //
            Elemento reforçoReforcado =  getElementoPessoal(
                    dto.uri_elementoreforco
                    ,cachePessoal
            );
            Boolean reforcoNaoReforcado = null;
            // V2
            if( reforçoReforcado != null ) {
                // há reforço
                reforcoNaoReforcado = new Boolean(true);
                p.setElementoReforcoReforcado( reforçoReforcado );
            }
            else{
                // não há reforço, mas há reforçado ?
                reforçoReforcado = getElementoPessoal(
                        dto.uri_elementoreforcado
                        ,cachePessoal
                );
                if( reforçoReforcado != null ){
                    // há reforçado
                    reforcoNaoReforcado = new Boolean( false );
                    p.setElementoReforcoReforcado( reforçoReforcado );
                }
                // Não há reforço nem reforçado
            }
            p.setReforcoNaoReforcado( reforcoNaoReforcado );

            // obter instalacao
            String uriInstalacao = dto.uri_instalacao;
            Instalacao instalacao = cacheInstalacao.get( uriInstalacao );
            if( instalacao == null ){

                InstalacaoDto instalacaoDto = RsbWebServiceAsync
                                                        .getResource(
                                                            uriInstalacao
                                                            ,InstalacaoDto.class );

                instalacao = UnidadesEstruturaisWebService.convertInstalacaoFromDto( instalacaoDto, null );

                cacheInstalacao.put( uriInstalacao, instalacao );
            }

            p.setInstalacao( instalacao );

            //
            // Posto Funcional
            //
            PostoFuncional posto = getResourceWithCache(
                    dto.uri_postofuncionalefectivo
                    ,cachePosto
                    ,PostoFuncional.class
            );
            p.setPostoFuncional( posto );

            //
            // Turno
            //
            //p.setTurnoId();
            String uriTurno = dto.uri_turnoefectivo;
            Turno turno = cacheTurno.get( uriTurno );
            if( turno == null ){

                TurnoDto turnoDto = RsbWebServiceAsync
                                                    .getResource(
                                                            uriTurno
                                                            ,TurnoDto.class );

                turno = TurnosWebService.convertFromDto( turnoDto );

                cacheTurno.put( uriTurno, turno );
            }

            p.setTurno( turno );

            result.add( p );
        }
        return result;
    }
}
