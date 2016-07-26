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
import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.PRESENCA_URL;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PresencasWebService extends AbstractWebService implements IPresencasRepository {

    //public PresencasWebService() {
    //    System.out.println( "New PrsencasWebService" );
    //}

    /*
    Date    data
    Float   numhoras
    Long    periodo_id
    Long    turno_id
    Long    instalacao_id
    Long    postofuncional_id

    String  tipopresenca_id
    Long    elementodopessoal_id
    Long    elementoreforco_id
    Long    elementorefocado_id
     */

    @Override
    public void update( Presenca presenca ) {

        List< Param > parameters = new LinkedList<>();

        // Atributo data
        LocalDate dataValue = presenca.getData();
        String dataStr = dataValue == null ? "" : dataValue.toString() ;
        Param data = new Param( "data", dataStr );
        parameters.add( data );

        // Atributo numHoras
        Float numHorasValue = presenca.getNumHoras();
        String numHorasStr = numHorasValue == null ? "" : numHorasValue.toString();
        Param numHoras= new Param( "numhoras", numHorasStr );
        parameters.add( numHoras );

        // Atributo periodo_id
        Periodo p = presenca.getPeriodo();
        Long periodoValue = null;
        if( p != null ){
            periodoValue = p.getId();
        }
        String periodoValueStr = periodoValue == null ? "" : periodoValue.toString();
        Param periodo= new Param( "periodo_id", periodoValueStr );
        parameters.add( periodo );

        // Atributo turno_id
        Turno t = presenca.getTurno();
        Long turnoValue = null;
        if( t != null ){
            turnoValue = t.getId();
        }
        String turnoValueStr = turnoValue == null ? "" : turnoValue.toString();
        Param turno = new Param( "turno_id", turnoValueStr );
        parameters.add( turno );

        // Atributo instalacao_id
        Instalacao i = presenca.getInstalacao();
        Long instalacaoValue = null;
        if( i != null ){
            instalacaoValue = i.getId();
        }
        String instalacaoValueStr = instalacaoValue == null ? "" : instalacaoValue.toString();
        Param instalacao = new Param( "instalacao_id", instalacaoValueStr );
        parameters.add( instalacao );

        // Atributo postofuncional_id
        PostoFuncional pf = presenca.getPostoFuncional();
        Long postoFuncionalValue = null;
        if( pf != null ){
            postoFuncionalValue = pf.getId();
        }
        String postoFuncionalValueStr = postoFuncionalValue == null ? "" : postoFuncionalValue.toString();
        Param postoFuncional = new Param( "postofuncional_id", postoFuncionalValueStr );
        parameters.add( postoFuncional );

        // Atributo tipopresenca_id
        String tipoPresencaValue = presenca.getTipoPresencaId();
        Param tipoPresenca = new Param( "tipopresenca_id", tipoPresencaValue );
        parameters.add( tipoPresenca );

        // Atributo elementodopessoal_id
        Elemento elem = presenca.getElemento();
        Long elemValue = null;
        if( elem != null ){
            elemValue = elem.getId();
        }
        String elemValueStr = elemValue == null ? "" : elemValue.toString();
        Param elemento = new Param( "elementodopessoal_id", elemValueStr );
        parameters.add( elemento );

        // Atributo elementoReforcoReforcado
        Boolean isElementoReforcoReforcado = presenca.getReforcoNaoReforcado();
        Param elementoReforco;
        Param elementoReforcado;
        if( isElementoReforcoReforcado == null ){
            elementoReforco = new Param( "elementoreforco_id", "" );
            elementoReforcado = new Param( "elementoreforcado_id", "" );
        }
        else{
            if( isElementoReforcoReforcado ){
                Long elemReforco = presenca.getElementoReforcoReforcado().getId();
                elementoReforco = new Param( "elementoreforco_id", elemReforco.toString() );
                elementoReforcado = new Param( "elementoreforcado_id", "" );
            }
            else{
                Long elemReforcado = presenca.getElementoReforcoReforcado().getId();
                elementoReforco = new Param( "elementoreforco_id", "" );
                elementoReforcado = new Param( "elementoreforcado_id", elemReforcado.toString() );
            }
        }
        parameters.add( elementoReforco );
        parameters.add( elementoReforcado );

        CompletableFuture<PresencaDTO> updatePresenca =  RsbWebServiceAsync.callPutActionAndConvert(
                                        PresencaDTO.class
                                        ,parameters
                                        ,PRESENCA_URL
                                        ,presenca.getId().toString()
        );

        PresencaDTO dto = null;

        try {
            dto = updatePresenca.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }

    }

    @Override
    public Long insert( Presenca element ) {
        throw new NotImplementedException();
    }

    @Override
    public Presenca selectOne( Long presencaId ) {

        PresencaDTO dto = null;
        try {
            dto = RsbWebServiceAsync
                    .callActionAndConvert(
                            PresencaDTO.class
                            ,PRESENCA_URL
                            ,presencaId.toString()
                    )
                    .get();
        } catch( InterruptedException | ExecutionException e) {
            throw new RuntimeException( e );
        }
        Presenca presenca = convertFromDto( dto );
        return presenca;
    }

    @Override
    public Collection<Presenca> selectAll() {

        CompletableFuture< PresencaDTO[] > getPresencas;

        getPresencas = RsbWebServiceAsync
                        .callActionAndConvert(
                            PresencaDTO[].class
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
    public void delete( Long aLong ) {
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
        CompletableFuture< PresencaDTO[] > getPresencas =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                PresencaDTO[].class
                                ,parameters
                                ,PRESENCAS_URL
                        );

        Collection< Presenca > result = convertFromDto( getPresencas );
        return  result;
    }


    @Override
    public boolean registarTroca( Long presencaId, Long elementoReforcoId ) {
        List< Param > parameters = new LinkedList<>();
        parameters.add( new Param( "elementodereforco_id", elementoReforcoId.toString() ) );

        PresencaDTO dto = null;
        try {
            dto = RsbWebServiceAsync
                        .callPostActionAndConvert(
                                    PresencaDTO.class
                                    ,parameters
                                    ,PRESENCA_URL + "/realizarreforco"
                                    ,presencaId.toString()
                        )
                        .get();
        } catch( InterruptedException | ExecutionException e) {
            throw new RuntimeException( e );
        }
        boolean result = dto.id != null ;
        return result;
    }

    @Override
    public Collection< Elemento > selectElemeReforcos(Long presencaId) {
        CompletableFuture< ElementoDto[] > getPessoal =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                ElementoDto[].class
                                ,PRESENCAS_URL + "/%s/pessoalreforco"
                                ,presencaId.toString()
                        );
        ElementoDto[] pessoal = null;

        try {
            pessoal = getPessoal.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }

        Collection<Elemento> result = new LinkedList<>();

        // Converter ElementoDto -> Elemento
        for( ElementoDto dto : pessoal ) {
            Elemento elemento = PessoalWebService.convertDtoToModel(dto, false);
            result.add( elemento );
        }
        return result;
    }


    private static Presenca convertFromDto( PresencaDTO dto ){
        Presenca p = new Presenca();
        p.setId( dto.id );
        p.setData( LocalDate.parse( dto.data ) );
        p.setHoraInicio( LocalTime.parse( dto.horaInicio ) );
        p.setNumHoras( dto.numHoras );
        return p;
    }

    private static Collection<Presenca> convertFromDto( CompletableFuture< PresencaDTO[] > getPresencas ){

        PresencaDTO[] dtos = null;

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
        for( PresencaDTO dto : dtos ){

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
