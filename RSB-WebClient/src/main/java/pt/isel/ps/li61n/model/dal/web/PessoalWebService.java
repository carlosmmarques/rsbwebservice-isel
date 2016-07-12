package pt.isel.ps.li61n.model.dal.web;

import org.asynchttpclient.Param;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPessoalRepository;
import pt.isel.ps.li61n.model.dal.exceptions.ElementoNotFoundException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.dtos.ElementoDto;
import pt.isel.ps.li61n.model.dal.web.exceptions.WebServiceException;
import pt.isel.ps.li61n.model.entities.Categoria;
import pt.isel.ps.li61n.model.entities.Elemento;
import pt.isel.ps.li61n.model.entities.PostoFuncional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.RsbWebClientApplication.PESSOAL_URL;

/**
 * Created on 26/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Profile( "dev" )
@Component
public class PessoalWebService extends AbstractWebService implements IPessoalRepository {

    @Override
    public Long insert( Elemento newElemento ) throws RepositoryException {

        Long result = null;

        if( newElemento != null ){

            List< Param > parameters = new LinkedList<>();

            //idInterno - opcional
            String idInternoValue = newElemento.getIdInterno();
            if( idInternoValue != null ){
                Param idInterno = new Param( "idInterno", idInternoValue );
                parameters.add( idInterno );
            }

            // matricula - obrigatorio
            Integer matriculaValue = newElemento.getMatricula();
            if( matriculaValue != null ){
                Param matricula = new Param( "matricula", matriculaValue.toString() );
                parameters.add( matricula );
            }

            // nummecanografico - obrigatorio
            Integer numMecanograficoValue = newElemento.getNumMecanografico();
            if( numMecanograficoValue != null ){
                Param numMecanografico = new Param( "nummecanografico", numMecanograficoValue.toString() );
                parameters.add( numMecanografico );
            }
            // abreviatura - opcional
            // String abreviaturaValue = "";
            //Param abreviatura = new Param( "abreviatura", abreviaturaValue );
            //parameters.add( abreviatura );

            // nome - obrigatorio
            String nomeValue = newElemento.getNome();
            if( nomeValue != null ){
                Param nome = new Param( "nome", nomeValue );
                parameters.add( nome );
            }

            // data de nascimento - obrigatorio
            LocalDate dataNascimentoValue = newElemento.getDataNascimento();
            if( dataNascimentoValue != null ){
                Param dataNascimento = new Param( "datanascimento", dataNascimentoValue.toString() );
                parameters.add( dataNascimento );
            }

            // telefone1

            // telefone2

            // email

            // nif

            // dataIngresso - obrigaorio
            LocalDate dataIngressoValue = newElemento.getDataIngresso();
            if( dataIngressoValue != null ){
                Param dataIngresso = new Param( "dataingresso", dataIngressoValue.toString() );
                parameters.add( dataIngresso );
            }

            // CC/BI

            //
            // FK
            //

            // Posto funcional -> obrigatorio(server)
            Long postoFuncionalId = newElemento.getPostoFuncionalId();
            if( postoFuncionalId == null ){
                postoFuncionalId = 15L; // sem posto
            }
            Param postoFuncional = new Param( "postofuncional_id", postoFuncionalId.toString() );
            parameters.add( postoFuncional );

            // Tipo de presença -> obrigatorio(server)
            String tipoPresencaId = newElemento.getTipoPresencaId();
            if( tipoPresencaId == null ){
                tipoPresencaId = "0"; // não aplicavel
            }
            Param tipoPresenca = new Param( "tipopresenca_id", tipoPresencaId );
            parameters.add( tipoPresenca );

            // Turno -> obrigatorio(server)
            Long turnoId = newElemento.getTurnoId();
            if( turnoId == null ){
                turnoId = 9L; // sem turno
            }
            Param turno = new Param( "turno_id", turnoId.toString() );
            parameters.add( turno );

            // Instalação -> obrigatório
            Long instalacaoId = newElemento.getInstalacaoId();
            if( instalacaoId == null ){
                instalacaoId = 1L; // Quartel ...
            }
            Param instalacao = new Param( "instalacao_id", instalacaoId.toString() );
            parameters.add( instalacao );

            // Categoria -> obrigatório
            Long categoriaId = newElemento.getCategoriaId();
            if( categoriaId == null ){
                categoriaId = 13L; // não disponível
            }
            Param categoria = new Param( "categoria_id", categoriaId.toString() );
            parameters.add( categoria );

            // Data de atribuição de categoria
            LocalDate dataAtribuicaoValue = newElemento.getDataAtribuicao();
            if( dataAtribuicaoValue == null ){
                dataAtribuicaoValue = LocalDate.now();
            }
            Param dataAtribuicao = new Param( "dataatribuicaocategoria", dataAtribuicaoValue.toString() );
            parameters.add( dataAtribuicao );

            // Nota na avaliação da formação de obtenção da categoria
            Float avaliacaoFormacaoCategoriaValue = newElemento.getClassificacaoFormacao();
            if( avaliacaoFormacaoCategoriaValue == null ){
                avaliacaoFormacaoCategoriaValue = new Float( 0 );
            }
            Param avaliacaoFormacao = new Param( "classificacaoformacao", avaliacaoFormacaoCategoriaValue.toString() );
            parameters.add( avaliacaoFormacao );

            try {
                ElementoDto dto = RsbWebServiceAsync
                                    .callPostActionAndConvert(
                                        ElementoDto.class
                                        ,parameters
                                        ,"/pessoal"
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
    public Elemento selectOne( Long id ) {

        // Passar para uma implementacao de IRepository
        CompletableFuture<ElementoDto> getElemento = RsbWebServiceAsync
                                                        .callActionAndConvert(
                                                            ElementoDto.class
                                                            ,PESSOAL_URL + "/%S"
                                                            ,id
                                                        );
        ElementoDto dto = null;

        try {
            dto = getElemento.get();
        }
        catch (InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }

        Elemento elemento = convertDtoToModel( dto, true );

        return elemento;
    }

    @Override
    public Collection<Elemento> selectAll() {

        CompletableFuture< ElementoDto[] > getPessoal =
                                                RsbWebServiceAsync
                                                    .callActionAndConvert(
                                                        ElementoDto[].class
                                                        , PESSOAL_URL
                                                    );
        ElementoDto[] pessoal = null;

        try {
            pessoal = getPessoal.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }

        Collection<Elemento> result = new LinkedList<>();

        // Cache
        HashMap< String, PostoFuncional > cachePosto = new HashMap<>();
        HashMap< String, Categoria > cacheCategoria = new HashMap<>();
        //HashMap< String, Instalacao> cacheInstalacao = new HashMap<>();

        // Converter ElementoDto -> Elemento
        for( ElementoDto dto : pessoal ){
            Elemento elemento = convertDtoToModel( dto, false );

            //Obter o posto
            PostoFuncional posto = getResourceWithCache(
                    dto.uri_postoFuncionalPorOmissao
                    ,cachePosto
                    ,PostoFuncional.class
            );
            elemento.setPostoFuncional( posto );

            //Obter categoria
            Categoria categoria = getResourceWithCache(
                    dto.uri_categoria
                    ,cacheCategoria
                    ,Categoria.class
            );
            elemento.setCategoria( categoria );

            result.add( elemento );
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
    public Elemento getOneByNumMecanografico( Integer numMecanografico ) throws ElementoNotFoundException {

        Elemento result = null;

        if( numMecanografico != null ){
            List< Param > parameters = new LinkedList<>();

            parameters.add( new Param( "nummecanografico", numMecanografico.toString() ) );

            CompletableFuture< ElementoDto[] > getElementos =
                    RsbWebServiceAsync
                            .callActionAndConvert(
                                    ElementoDto[].class
                                    ,parameters
                                    ,"/pessoal"
                            );

            ElementoDto[] elementos = null;
            try {
                elementos = getElementos.get();
            }
            catch( InterruptedException e ){
                throw new RuntimeException( e );
            }
            catch( ExecutionException e ) {
                Throwable t = e.getCause();
                if( t.getClass().equals( WebServiceException.class ) ){

                    WebServiceException ex = (WebServiceException) e.getCause();
                    switch( ex.statusCode ){
                        case HttpURLConnection.HTTP_NOT_FOUND:
                            throw new ElementoNotFoundException( ex.getMessage() );

                    }
                }
                throw new RuntimeException( e );
            }
            result = convertDtoToModel( elementos[0], false ); // TODO: Confirmar se há necessidade de por a true!
        }
        return result;
    }

    public static Elemento convertDtoToModel( ElementoDto dto, boolean withExtras ) {
        Elemento elemento = null;

        if( dto == null ){
            return elemento;
        }

        elemento = new Elemento();

        elemento.setId( dto.id );
        elemento.setNumMecanografico( Integer.parseInt( dto.numMecanografico ) );
        elemento.setIdInterno( dto.idInterno );

        elemento.setNome( dto.nome );

        if( withExtras ) {

            //Obter o posto
            PostoFuncional posto = null;
            try {
                posto = RsbWebServiceAsync
                        .callAndConvert(
                                PostoFuncional.class
                                , dto.uri_postoFuncionalPorOmissao
                        )
                        .get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

            elemento.setPostoFuncional(posto);

            //Obter categoria
            Categoria categoria = null;
            try {
                categoria = RsbWebServiceAsync
                        .callAndConvert(
                                Categoria.class
                                , dto.uri_categoria
                        )
                        .get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

            elemento.setCategoria(categoria);
        }
        return elemento;
    }
}
