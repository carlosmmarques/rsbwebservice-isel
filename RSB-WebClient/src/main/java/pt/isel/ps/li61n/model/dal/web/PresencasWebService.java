package pt.isel.ps.li61n.model.dal.web;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPresencasRepository;
import pt.isel.ps.li61n.model.dal.web.dtos.PresencaDto;
import pt.isel.ps.li61n.model.entities.Pessoal;
import pt.isel.ps.li61n.model.entities.PostoFuncional;
import pt.isel.ps.li61n.model.entities.Presenca;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.RsbWebClientApplication.PESSOAL;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PresencasWebService extends AbstractWebService implements IPresencasRepository {

    @Override
    public Long insert(Presenca element) {
        throw new NotImplementedException();
    }

    @Override
    public Presenca selectOne(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Presenca> selectAll() {

        CompletableFuture< PresencaDto[] > getPresencas =
                _rsbWebService
                    .callActionAndConvert(
                        PresencaDto[].class
                        ,"/presenca"
                    )
        ;
        PresencaDto[] presencas = null;

        try{
            presencas = getPresencas.get();
        }
        catch (InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e ); // TODO: Tratar o erro!
        }

        Collection< Presenca > result = new LinkedList<>();

        // V2 -> Cache
        HashMap< String, Pessoal > cachePessoal = new HashMap<>();
        HashMap< String, PostoFuncional> cachePosto = new HashMap<>();

        // Converter para Presencas
        for( PresencaDto dto : presencas ){
            Presenca p = new Presenca();
            p.setId( dto.id );
            p.setData( LocalDate.parse( dto.data ) );
            p.setHoraInicio( LocalTime.parse( dto.horaInicio ) );
            p.setNumHoras( dto.numHoras );

            //p.setPeriodoId(  );

            // obter o elemento do registo de presença
            Pessoal elemento = getElementoPessoal(
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
            Pessoal reforçoReforcado =  getElementoPessoal(
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

            //
            // Instalacao
            //
            //p.setInstalacaoId();

            //
            // Posto Funcional
            //
            PostoFuncional posto = getElemento(
                                    dto.uri_postofuncionalefectivo
                                    ,cachePosto
                                    ,PostoFuncional.class
            );
            p.setPostoFuncional( posto );

            //
            // Turno
            //
            //p.setTurnoId();

            result.add( p );
        }
        return result;
    }

    private Pessoal getElementoPessoal( String uri, HashMap<String, Pessoal> cachePessoal ) {
        return getElemento( uri, cachePessoal, Pessoal.class );
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
