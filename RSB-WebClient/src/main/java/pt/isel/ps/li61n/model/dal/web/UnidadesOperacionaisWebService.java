package pt.isel.ps.li61n.model.dal.web;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IUnidadesOperacionaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.*;
import pt.isel.ps.li61n.model.entities.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.GUARNICAO_URL;
import static pt.isel.ps.li61n.model.dal.web.RsbWebServiceAsync.UNIDADES_OPERACIONAIS_URL;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class UnidadesOperacionaisWebService extends AbstractWebService implements IUnidadesOperacionaisRepository {

    @Override
    public Long insert(UnidadeOperacional element) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public UnidadeOperacional selectOne(Long aLong) throws RepositoryException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public void update(UnidadeOperacional id) {
        throw new NotImplementedException();
    }

    @Override
    public Collection< UnidadeOperacional > selectAll() throws RepositoryException {
        CompletableFuture< UnidadeOperacionalDto[] > getUnOps;

        getUnOps = RsbWebServiceAsync
                .callActionAndConvert(
                        UnidadeOperacionalDto[].class
                        ,UNIDADES_OPERACIONAIS_URL
                );

        UnidadeOperacionalDto[] dtos = null;
        try {
            dtos = getUnOps.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }

        Collection< UnidadeOperacional > result = convertFromDto( dtos );
        return result;
    }

    private Collection< UnidadeOperacional > convertFromDto( UnidadeOperacionalDto[] dtos ) throws RepositoryException {

        Collection< UnidadeOperacional > result = new ArrayList<>( dtos.length );

        // Cache
        HashMap< String, Instalacao > cacheInstalacao = new HashMap<>();
        HashMap< String, TipoUnidadeOperacionalDto > cacheTipo = new HashMap<>();

        for( UnidadeOperacionalDto dto : dtos ){

            UnidadeOperacional unOp = new UnidadeOperacional();

            unOp.setId( dto.id );

            unOp.setDesignacao( dto.designacao );

            unOp.setOperacional( dto.operacional );

            // Obter o tipo unidade operacional
            String uriTipo = dto.uri_tipoUnidadeOperacional;
            TipoUnidadeOperacionalDto tipoDto = cacheTipo.get( uriTipo );

            if( tipoDto == null ){
                tipoDto = RsbWebServiceAsync.getResource(
                        uriTipo,
                        TipoUnidadeOperacionalDto.class
                );
                if( tipoDto != null ){
                    cacheTipo.put( uriTipo, tipoDto );
                }
            }
            unOp.setTipoUnidadeOperacional( tipoDto.designacao );

            // obter a instalação
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
            unOp.setInstalacao( instalacao );

            result.add( unOp );
        }
        return result;
    }

    @Override
    public Collection< Guarnicao > selectGuarnicaoOfUnidadeOperacional( Long unidadeOperacionalId ) {

        CompletableFuture< GuarnicaoDto[] > getGuarnicoes;

        getGuarnicoes = RsbWebServiceAsync
                .callActionAndConvert(
                        GuarnicaoDto[].class
                        ,GUARNICAO_URL
                        ,unidadeOperacionalId.toString()
                );

        GuarnicaoDto[] dtos = null;
        try {
            dtos = getGuarnicoes.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }

        Collection< Guarnicao > result = convertFromDto( dtos, unidadeOperacionalId );
        return result;
    }

    private Collection< Guarnicao > convertFromDto( GuarnicaoDto[] dtos, Long unidadeOperacionalId ) {

        Collection< Guarnicao > result = new ArrayList<>( dtos.length );

        // Cache
        HashMap< String, ResponsabilidadeOperacional > cache = new HashMap<>();

        for( GuarnicaoDto dto : dtos ){

            Guarnicao guarnicao = new Guarnicao();
            guarnicao.setId( dto.id );
            guarnicao.setUnidadeOperacionalId( unidadeOperacionalId );
            guarnicao.setMinimo( dto.minimo );
            guarnicao.setMaximo( dto.maximo );

            //
            // obter responsabilidade operacional
            //
            String uri_ResponsabilidadeOperacional = dto.uri_responsabilidadeoperacional;
            ResponsabilidadeOperacional responsabilidadeOperacional = cache.get( uri_ResponsabilidadeOperacional );

            if( responsabilidadeOperacional == null ) {
                ResponsabilidadeOperacionalDto respOpDto =
                                                    RsbWebServiceAsync
                                                            .getResource(
                                                                    uri_ResponsabilidadeOperacional
                                                                    ,ResponsabilidadeOperacionalDto.class
                                                            );
                if( respOpDto != null ){
                    // Converter dto -> Model
                    responsabilidadeOperacional = convertFromDto( respOpDto );

                    if( responsabilidadeOperacional != null ){
                        // guardar em cache
                        cache.put( uri_ResponsabilidadeOperacional, responsabilidadeOperacional );
                    }
                }
            }
            guarnicao.setResponsabilidadeOperacional( responsabilidadeOperacional );

            result.add( guarnicao );
        }
        return result;
    }

    private ResponsabilidadeOperacional convertFromDto( ResponsabilidadeOperacionalDto respOpDto ) {

        ResponsabilidadeOperacional respOper = new ResponsabilidadeOperacional();

        TipoPresencaDto dto = RsbWebServiceAsync.getResource(
                                                    respOpDto.uri_tipoPresenca
                                                    ,TipoPresencaDto.class
        );

        TipoPresenca tipoPresenca = new TipoPresenca();

        tipoPresenca.setId( dto.id );
        tipoPresenca.setReforco( dto.reforco );
        tipoPresenca.setAusencia( dto.ausencia );
        tipoPresenca.setAbreviatura( dto.abreviatura );
        tipoPresenca.setDescricao( dto.descricao );

        // Obter o id tipo de reforço, se existir
        String uri_tipoPresencaReforco = dto.uri_tipopresencaemreforco;

        TipoPresencaDto dtoReforco = RsbWebServiceAsync.getResource(
                                                            uri_tipoPresencaReforco
                                                            ,TipoPresencaDto.class
        );

        tipoPresenca.setTipoPresencaReforcoId( dtoReforco.id );

        respOper.setTipoPresenca( tipoPresenca );

        // TODO: Acrescentar restantes propriedades de ResponsabilidadeOperacional

        return respOper;
    }
}
