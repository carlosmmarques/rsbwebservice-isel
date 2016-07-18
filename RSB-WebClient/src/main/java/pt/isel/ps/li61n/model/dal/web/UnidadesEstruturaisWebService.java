package pt.isel.ps.li61n.model.dal.web;


import org.aspectj.weaver.ast.Not;
import org.asynchttpclient.Param;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IUnidadesEstruturaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.InstalacaoDto;
import pt.isel.ps.li61n.model.dal.web.dtos.TipoUnidadeEstruturalDto;
import pt.isel.ps.li61n.model.dal.web.dtos.UnidadeEstruturalDto;
import pt.isel.ps.li61n.model.dal.web.exceptions.WebServiceException;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.INSTALACAO_URL;
import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.INSTALACOES_URL;
import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.UNIDADES_ESTRUTURAIS_URL;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class UnidadesEstruturaisWebService extends AbstractWebService implements IUnidadesEstruturaisRepository {

    @Override
    public Long insert( UnidadeEstrutural newElemento ) throws RepositoryException {

        Long result = null;

        if( newElemento != null ){

            List<Param> parameters = new LinkedList<>();

            /*
            @RequestParam(value = "", required = true) Long unidadeestruturalmae_id,
            @RequestParam(value = "nivelhierarquico"
             */

            //designacao - obrigatorio (server)
            String designacaoValue = newElemento.getDesignacao();
            if( designacaoValue == null ){
                designacaoValue = "";
            }
            Param designacao = new Param( "designacao", designacaoValue );
            parameters.add( designacao );


            // Id do tipo de unidade estrutural (FK) - obrigatorio
            Long tipoUnidadeEstruturalId = newElemento.getTipo_id();
            if( tipoUnidadeEstruturalId == null ){
                tipoUnidadeEstruturalId = 4L; // secção
            }
            Param tipoUnidadede = new Param( "tipounidadeestrutural_id", tipoUnidadeEstruturalId.toString() );
            parameters.add( tipoUnidadede );


            // Id da unidade estrutural mãe (FK)
            Long unidadedeEstruturaMaelId = newElemento.getUnidadeEstruturalMae_id();
            if( unidadedeEstruturaMaelId != null ){
                Param unidadeMae = new Param( "unidadeestruturalmae_id", unidadedeEstruturaMaelId.toString() );
                parameters.add( unidadeMae );
            }

            try {
                UnidadeEstruturalDto dto = RsbWebServiceAsync.callPostActionAndConvert(
                        UnidadeEstruturalDto.class
                        ,parameters
                        ,UNIDADES_ESTRUTURAIS_URL
                ).get();  //TODO retorna o objecto

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
    public UnidadeEstrutural selectOne( Long aLong ) throws RepositoryException {

        CompletableFuture< UnidadeEstruturalDto > getUnidadeEstrutural =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                UnidadeEstruturalDto.class
                                , UNIDADES_ESTRUTURAIS_URL + "/%s"
                                , aLong.toString()
                        )
                ;
        UnidadeEstruturalDto dto = null;

        try{
            dto = getUnidadeEstrutural.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        UnidadeEstrutural result = convertFromDto( dto );
        return result;
    }

    @Override
    public Collection< UnidadeEstrutural > selectAll() throws RepositoryException {

        CompletableFuture< UnidadeEstruturalDto[] > getUnidadesEstruturais =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                UnidadeEstruturalDto[].class
                                , UNIDADES_ESTRUTURAIS_URL
                        )
                ;
        UnidadeEstruturalDto[] dtos = null;

        try{
            dtos = getUnidadesEstruturais.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        Collection< UnidadeEstrutural > result = new ArrayList<>( dtos.length );

        // cache
        HashMap< String, UnidadeEstrutural > cacheUe = new HashMap<>();
        HashMap< String, TipoUnidadeEstrutural > cacheTipo = new HashMap<>();

        for( UnidadeEstruturalDto dto : dtos ) {

            UnidadeEstrutural ue = convertFromDtoWithCache( dto, cacheUe,cacheTipo );

            result.add( ue );
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
    public Collection< Instalacao > selectAllInstalacoes() throws RepositoryException {

        // obter todas as unidades estruturais (ue)
        Collection< UnidadeEstrutural > ues = selectAll();

        // percorrer cada uma das ue e obter as instalacoes respectivas
        Collection< Instalacao > result = new LinkedList<>();

        for( UnidadeEstrutural ue : ues ){

            Collection< Instalacao > instalacoes = selectAllInstalacoes( ue.getId() );

            result.addAll( instalacoes );
        }
        return result;
    }

    @Override
    public Collection<Instalacao> selectAllInstalacoes( Long unidadeEstruturalId ) throws RepositoryException {

        // obter as instalacoes
        CompletableFuture< InstalacaoDto[] > getInstalacoes =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                InstalacaoDto[].class
                                ,INSTALACOES_URL
                                , unidadeEstruturalId
                        )
                ;

        InstalacaoDto[] dtos = null;

        try{
            dtos = getInstalacoes.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        // Converter dto -> model
        Collection< Instalacao > instalacoes = new ArrayList<>( dtos.length );
        for( InstalacaoDto dto : dtos ) {

            Instalacao instalacao = convertInstalacaoFromDto( dto, unidadeEstruturalId );

            instalacoes.add( instalacao );
        }
        return instalacoes;
    }

    @Override
    public Long insertInstalacao( Instalacao newInstalacao ) throws RepositoryException {
        Long result = null;

        if( newInstalacao != null ){
            List<Param> parameters = new LinkedList<>();

            // Identificador da unidade estrutural a que pertence
            // (Server: unidadeestrutural_id)
            Long unidadaEstruturalIdValue = newInstalacao.getUnidadeEstruturalId();

            if( unidadaEstruturalIdValue == null ){
                throw new NullPointerException( "Identificador da unidadade estrutural é obrigatório!" );
            }

            Param unidadeEstruralId = new Param( "unidadeestrutural_id", unidadaEstruturalIdValue.toString() );
            parameters.add( unidadeEstruralId );

            //designacao - obrigatorio (server)
            String designacaoValue = newInstalacao.getDesignacao();
            if( designacaoValue == null ){
                designacaoValue = "";
            }
            Param designacao = new Param( "designacao", designacaoValue );
            parameters.add( designacao );

            // descricao
            String descricaoValue = newInstalacao.getDescricao();
            if( descricaoValue == null ){
                descricaoValue = "";
            }
            Param descricao = new Param( "descricao", descricaoValue );
            parameters.add( descricao );

            // localizacao
            String localizacaoValue = newInstalacao.getLocalizacao();
            if( localizacaoValue == null ){
                localizacaoValue = "";
            }
            Param localizacao = new Param( "localizacao", localizacaoValue );
            parameters.add( localizacao );

            try {
                UnidadeEstruturalDto dto = RsbWebServiceAsync
                                                .callPostActionAndConvert(
                                                    UnidadeEstruturalDto.class
                                                    ,parameters
                                                    ,INSTALACOES_URL
                                                    ,unidadaEstruturalIdValue
                                                )
                                                .get();  //TODO retorna o objecto?

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
    public Instalacao selectOneInstalcao(Long unidadeEstruturalId, Long instalacaoId ) throws RepositoryException {

        CompletableFuture< InstalacaoDto > getInstalacao =
                RsbWebServiceAsync
                        .callActionAndConvert(
                                InstalacaoDto.class
                                ,INSTALACAO_URL
                                ,unidadeEstruturalId.toString()
                                ,instalacaoId.toString()
                        )
                ;
        InstalacaoDto dto = null;

        try{
            dto = getInstalacao.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }

        Instalacao result = convertInstalacaoFromDto( dto, unidadeEstruturalId );
        return result;
    }

    private UnidadeEstrutural convertFromDto( UnidadeEstruturalDto dto ) throws RepositoryException {

        UnidadeEstrutural result = new UnidadeEstrutural();

        result.setId( dto.id );
        result.setDesignacao( dto.designacao );

        // "Pedir" para obter o tipo de unidade estrutural
        CompletableFuture< TipoUnidadeEstruturalDto > getTipo =
                RsbWebServiceAsync.callAndConvert(
                        TipoUnidadeEstruturalDto.class
                        , dto.uri_tipoUnidadeEstrutural
                );

        // "Pedir" para obter, se existir, a unidadae estrutural mae
        String uri_mae = dto.uri_unidadeEstruturalMae;
        boolean existeMae = uri_mae != null && !uri_mae.isEmpty();
        CompletableFuture< UnidadeEstruturalDto > getMae = null;
        if( existeMae ){
            getMae = RsbWebServiceAsync.callAndConvert( UnidadeEstruturalDto.class, uri_mae );;
        }

        // Obter tipo de unidade estrutural
        TipoUnidadeEstruturalDto tipoDto = null;
        try{
            tipoDto = getTipo.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RepositoryException( e.getMessage() );
        }
        TipoUnidadeEstrutural tipo = TiposUnidadeEstruturalWebService.convertFromDto( tipoDto );
        result.setTipo( tipo );

        // Obter, se existir, a unidade estrutural mãe
        if( existeMae ){
            UnidadeEstruturalDto maeDto = null;
            try{
                maeDto = getMae.get() ;
            }
            catch( InterruptedException | ExecutionException e ) {
                throw new RepositoryException( e.getMessage() );
            }
            UnidadeEstrutural mae = convertFromDto( maeDto );
            result.setUnidadeEstruturalMae( mae );
        }
        return result;
    }

    private UnidadeEstrutural convertFromDtoWithCache(
                                UnidadeEstruturalDto dto
                                ,Map< String, UnidadeEstrutural > cacheUe
                                ,Map< String, TipoUnidadeEstrutural > cacheTipo
    ){

        UnidadeEstrutural ue = new UnidadeEstrutural();

        ue.setId( dto.id );
        ue.setDesignacao( dto.designacao );


        TipoUnidadeEstrutural tipo = getResourceWithCache(
                                        dto.uri_tipoUnidadeEstrutural
                                        ,cacheTipo
                                        ,TipoUnidadeEstrutural.class
        );
        ue.setTipo( tipo );

        UnidadeEstrutural mae = getResourceWithCache(
                                    dto.uri_unidadeEstruturalMae
                                    ,cacheUe
                                    ,UnidadeEstrutural.class
        );
        ue.setUnidadeEstruturalMae( mae );


        return ue;
    }

    public static Instalacao convertInstalacaoFromDto( InstalacaoDto dto, Long ueId ){

        Instalacao instalacao = new Instalacao();

        instalacao.setId( dto.id );
        instalacao.setDesignacao( dto.designacao );
        instalacao.setDescricao( dto.descricao );
        instalacao.setLocalizacao( dto.localizacao );
        instalacao.setUnidadeEstruturalId( ueId );

        return instalacao;
    }
}
