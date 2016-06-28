package pt.isel.ps.li61n.model.dal.web;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPessoalRepository;
import pt.isel.ps.li61n.model.dal.web.dtos.PessoalDto;
import pt.isel.ps.li61n.model.entities.Categoria;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.Pessoal;
import pt.isel.ps.li61n.model.entities.PostoFuncional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static pt.isel.ps.li61n.RsbWebClientApplication.PESSOAL;

/**
 * Created on 26/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PessoalWebService extends AbstractWebService implements IPessoalRepository {

    /*
    public PessoalWebService(){
        super();
    }
    */
    @Override
    public Long insert( Pessoal element ) {

        throw new NotImplementedException();
    }

    @Override
    public Pessoal selectOne( Long id ) {

        // Passar para uma implementacao de IRepository
        CompletableFuture< PessoalDto > getElemento =
                                            _rsbWebService
                                                    .callActionAndConvert(
                                                            PessoalDto.class
                                                            ,"/pessoal/%S"
                                                            ,id
                                                    )
        ;
        PessoalDto dto = null;

        try {
            dto = getElemento.get();
        }
        catch (InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e ); // TODO: Tratar o erro!
        }

        Pessoal elemento = new Pessoal();

        elemento.setId( dto.id );
        elemento.setNumMecanografico( Integer.parseInt( dto.numMecanografico ) );
        elemento.setIdInterno( dto.idInterno );

        elemento.setNome( dto.nome );

        //Obter o posto
        PostoFuncional posto = null;
        try {
            posto = _rsbWebService
                        .callAndConvert(
                            PostoFuncional.class
                            ,dto.uri_postoFuncionalPorOmissao
                        )
                        .get();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException( e );
        }

        elemento.setPostoFuncional( posto );

        //Obter categoria
        Categoria categoria = null;
        try {
            categoria = _rsbWebService.callAndConvert(
                    Categoria.class
                    ,dto.uri_categoria
            ).get();
        }
        catch (InterruptedException | ExecutionException e) {
           throw new RuntimeException( e );
        }

        elemento.setCategoria( categoria );

        return elemento;
    }

    @Override
    public Collection<Pessoal> selectAll() {

        CompletableFuture< PessoalDto[] > getPessoal =
                                            _rsbWebService
                                                    .callActionAndConvert(
                                                        PessoalDto[].class
                                                        ,PESSOAL
                                                    )
        ;

        PessoalDto[] pessoal = null;

        try {
            pessoal = getPessoal.get();
        }
        catch( InterruptedException | ExecutionException e ) {
            e.printStackTrace();
        }

        Collection< Pessoal > result = new LinkedList<>();

        // Cache
        HashMap< String, PostoFuncional > cachePosto = new HashMap<>();
        HashMap< String, Categoria > cacheCategoria = new HashMap<>();
        //HashMap< String, Instalacao> cacheInstalacao = new HashMap<>();

        // Converter PessoalDto -> Pessoal
        for( PessoalDto dto : pessoal ){
            Pessoal elemento = new Pessoal();

            elemento.setId( dto.id );
            elemento.setNumMecanografico( Integer.parseInt( dto.numMecanografico ) );
            elemento.setIdInterno( dto.idInterno );

            elemento.setNome( dto.nome );

            //Obter o posto
            PostoFuncional posto = getElemento(
                    dto.uri_postoFuncionalPorOmissao
                    ,cachePosto
                    ,PostoFuncional.class
            );
            elemento.setPostoFuncional( posto );

            //Obter categoria
            Categoria categoria = getElemento(
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
}
