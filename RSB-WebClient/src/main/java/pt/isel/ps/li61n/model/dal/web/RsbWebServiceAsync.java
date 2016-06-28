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
import org.asynchttpclient.Response;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.util.web.HttpAsync;

import java.util.concurrent.CompletableFuture;
import java.lang.reflect.Type;

/**
 * Created on 24/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class RsbWebServiceAsync {

    public static final String API_BASE_URL = "http://localhost:1234";

    //TODO: MELHORAR
    final String user = "user";
    final String password = "password";

    private final Gson _jsonReader;
    private final HttpAsync _httpAsync;

    public RsbWebServiceAsync(Gson _jsonReader, HttpAsync _httpAsync ) {
        this._jsonReader = _jsonReader;
        this._httpAsync = _httpAsync;
    }

    public RsbWebServiceAsync(){
        this( new Gson(), new HttpAsync() );
    }

    // TODO: Reflectir sobre a possibilidade de trabalhar com Type em vez de 'Class'

    public < T > CompletableFuture< T >  callActionAndConvert( Class< T >  destKlass, String action, Object...args ) {
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


    private <T> T fromJson( Response resp, Class<T> destKlass  ) {
        return _jsonReader.fromJson( resp.getResponseBody(), destKlass );
    }


    public <T> CompletableFuture< T >  callAndConvert( Class<T> destKlass, String uri ) {
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
                                // TODO: Tratar dos erros!!
                                throw new RuntimeException( msg );
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
}
