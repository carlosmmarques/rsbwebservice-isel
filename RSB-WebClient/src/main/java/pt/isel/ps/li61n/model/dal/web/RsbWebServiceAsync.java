/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pt.isel.ps.li61n.model.dal.web;

import com.google.gson.Gson;
import org.asynchttpclient.Param;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.web.dtos.ErrorDto;
import pt.isel.ps.li61n.model.dal.web.exceptions.WebServiceException;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.util.web.HttpAsync;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

/**
 * Created on 24/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class RsbWebServiceAsync {

    public static final String
        API_BASE_URL = "http://localhost:1234"
        ,CATEGORIAS_URL = "/pessoal/categoria"
        ,POSTOS_FUNCIONAIS_URL = "/pessoal/postofuncional"
        ,PRESENCAS_URL = "/presenca"
        ,PRESENCA_URL = "/presenca/%s"
        ,TIPOS_PRESENCA_URL = "/presenca/tipo"
        ,PERIODOS_URL = "/presenca/periodo"
        ,PERIODO_URL = "/presenca/periodo/%s"
        ,UNIDADES_ESTRUTURAIS_URL = "/unidadeestrutural"
        ,TIPOS_UNIDADE_ESTRUTURAL_URL = "/unidadeestrutural/tipo"
        ,INSTALACOES_URL = "/unidadeestrutural/%s/instalacao"
        ,INSTALACAO_URL = "/unidadeestrutural/%s/instalacao/%s"
        ,TURNOS_URL = "/turno"
    ;

    //TODO: MELHORAR
    private static final String user = "user";
    private static final String password = "password";

    private static final Gson _jsonReader = new Gson();
    private static final HttpAsync _httpAsync = new HttpAsync();

    /*
    public RsbWebServiceAsync( Gson _jsonReader, HttpAsync _httpAsync ) {
        this._jsonReader = _jsonReader;
        this._httpAsync = _httpAsync;
    }

    public RsbWebServiceAsync(){
        this( new Gson(), new HttpAsync() );
        System.out.println( "RsbWebServiceAsync" );
    }
    */

    // TODO: Reflectir sobre a possibilidade de trabalhar com Type em vez de 'Class'

    public static < T > CompletableFuture< T >  callActionAndConvert(
        Class< T >  destKlass
        ,String action
        ,Object...args
    ) {
        final String uri = String.format( action, args );
        return _httpAsync
                .getDataAsyncWithBasicAuth(
                        API_BASE_URL + uri
                        ,user
                        ,password
                )
                .thenApply(
                    resp -> {
                        int statusCode = resp.getStatusCode();
                        String msg = "Status code = " + statusCode;
                        System.out.println( msg );
                        if( statusCode != 200 ) {
                            ErrorDto errorDto = fromJson( resp, ErrorDto.class );
                            throw new WebServiceException( errorDto.url, errorDto.message, statusCode );
                        }
                        return  fromJson( resp, destKlass);
                    }
                );
    }

    public static < T > CompletableFuture< T >  callActionAndConvert(
                                            Class< T >  destKlass
                                            ,List<Param> parameters
                                            ,String action
                                            ,Object...args
    ) {
        final String uri = String.format( action, args );

        CompletableFuture< Response > aux = _httpAsync
                                        .getDataAsyncWithBasicAuth(
                                            API_BASE_URL + uri
                                            ,user
                                            ,password
                                            ,parameters
                                        );
        Response resp = null;
        try {
            resp = aux.get();
        }
        catch(InterruptedException | ExecutionException e ) {
            throw new RuntimeException( e );
        }
        CompletableFuture result = new CompletableFuture();

        int statusCode = resp.getStatusCode();
        String msg = "Status code = " + statusCode;
        System.out.println( msg );
        if( statusCode != 200 ) {
            ErrorDto errorDto = fromJson( resp, ErrorDto.class );
            result.completeExceptionally( new WebServiceException( errorDto.url, errorDto.message, statusCode ) );
        }
        else{
            result.complete(  fromJson( resp, destKlass)  );
        }
        return result;
    }


    public static < T > CompletableFuture< T >  callPostActionAndConvert(
                                                Class< T >  destKlass
                                                ,List< Param > parameters
                                                ,String action
                                                ,Object...args
    ) {
        final String uri = String.format( action, args );
        return _httpAsync
                .postDataAsyncWithBasicAuth(
                        API_BASE_URL + uri
                        ,user
                        ,password
                        ,parameters
                )
                .thenApply(
                        resp -> {
                            int statusCode = resp.getStatusCode();
                            String msg = "Status code = " + statusCode;
                            System.out.println( msg );
                            if( statusCode >= 300 ) {
                                ErrorDto errorDto = fromJson( resp, ErrorDto.class );
                                throw new WebServiceException( errorDto.url, errorDto.message, statusCode );
                            }
                            return  fromJson( resp, destKlass );
                        }
                );
    }


    private static <T> T fromJson( Response resp, Class<T> destKlass  ) {
        return _jsonReader.fromJson( resp.getResponseBody(), destKlass );
    }


    public static <T> CompletableFuture< T >  callAndConvert( Class<T> destKlass, String uri ) {
        //final String uri = String.format( action, args );
        return _httpAsync
                .getDataAsyncWithBasicAuth(
                        uri
                        ,user
                        ,password
                )
                .thenApply(
                        resp -> {
                            int statusCode = resp.getStatusCode();
                            String msg = "Status code = " + statusCode;
                            System.out.println( msg );
                            if( statusCode != 200 ) {
                                ErrorDto errorDto = fromJson( resp, ErrorDto.class );
                                throw new WebServiceException( errorDto.url, errorDto.message, statusCode );
                            }
                            return  fromJson( resp, destKlass );
                        }
                );
    }


    public static < T > CompletableFuture< T >  callPutActionAndConvert(
            Class< T >  destKlass
            ,List< Param > parameters
            ,String action
            ,Object...args
    ) {
        final String uri = String.format( action, args );
        return _httpAsync
                .putDataAsyncWithBasicAuth(
                        API_BASE_URL + uri
                        ,user
                        ,password
                        ,parameters
                )
                .thenApply(
                        resp -> {
                            int statusCode = resp.getStatusCode();
                            String msg = "Status code = " + statusCode;
                            System.out.println( msg );
                            if( statusCode >= 300 ) {
                                ErrorDto errorDto = fromJson( resp, ErrorDto.class );
                                throw new WebServiceException( errorDto.url, errorDto.message, statusCode );
                            }
                            return  fromJson( resp, destKlass );
                        }
                );
    }


    //
    // Para lidar com colecções //TODO: Tentar arranjar alternativa!
    //
    /*
    private <T> T fromJson(Response resp, Type destKlass) {
        return _jsonReader.fromJson( resp.getResponseBody(), destKlass );
    }


    public <T> CompletableFuture< T >   callAndConvert( Type  destKlass, String action, Object...args ) {
        final String uri = String.format( action, args );
        return _httpAsync
                .getDataAsyncWithBasicAuth(
                        API_BASE_URL + uri
                        ,user
                        ,password
                )
                .thenApply(
                        resp -> {
                            int statusCode = resp.getStatusCode();
                            String msg = "Status code = " + statusCode;
                            System.out.println( msg );
                            if( statusCode != 200 ) {
                                // TODO: Tratar dos erros!!
                                throw new RuntimeException( msg );
                            }
                            return  fromJson( resp, destKlass);
                        }
                );
    }
    */

    public static <E> E getResource( String uri, Class< E > klass ) {

        E result = null;
        if (uri != null && !uri.isEmpty()) {
            try {
                result = callAndConvert( klass, uri ).get();
            }
            catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
