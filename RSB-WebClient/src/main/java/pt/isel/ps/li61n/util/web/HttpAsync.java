/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * Distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package pt.isel.ps.li61n.util.web;

import com.google.gson.Gson;
import org.asynchttpclient.*;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.asynchttpclient.Dsl.basicAuthRealm;

/**
 * Created on 25/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class HttpAsync implements AutoCloseable {

    private final AsyncHttpClient _client = new DefaultAsyncHttpClient();

    //TODO: Refactoring com getDataAsync e dps fazer uma versão que possa fazer gets, puts, ...
    public CompletableFuture< Response > getDataAsyncWithBasicAuth(
            String path
            ,String user
            ,String password
    ){
        CompletableFuture< Response > promise = new CompletableFuture<>();
        _client
                .prepareGet( path )

                // Pra ter Basic Authentication
                .setRealm( basicAuthRealm( user, password ).build() )

                .execute(
                        new AsyncCompletionHandler< Object >() {

                            @Override
                            public Object onCompleted( Response response ) throws Exception {
                                promise.complete( response );
                                return response;
                            }
                        }
                );
        return promise;
    }


    private Gson _gson = new Gson();

    public CompletableFuture< Response > getDataAsyncWithBasicAuth(
            String path
            ,String user
            ,String password
            ,List< Param > parameters
    ){
        CompletableFuture< Response > promise = new CompletableFuture<>();
        _client
                .prepareGet( path )
                .addQueryParams( parameters )

                // Pra ter Basic Authentication
                .setRealm( basicAuthRealm( user, password ).build() )

                .execute(
                        new AsyncCompletionHandler< Object >() {

                            @Override
                            public Object onCompleted( Response response ) throws Exception {

                                /*
                                int statusCode = response.getStatusCode();
                                String msg = "Status code = " + statusCode;
                                System.out.println( msg );
                                if( statusCode != 200 ) {
                                    ErrorDto errorDto = _gson.fromJson(
                                            response.getResponseBody()
                                            ,ErrorDto.class
                                    );
                                    throw new WebServiceException( errorDto.url, errorDto.message, statusCode );
                                }
                                */
                                promise.complete( response );
                                return response;
                            }
                        }
                );
        return promise;
    }



    //TODO: Refactoring com getDataAsync e dps fazer uma versão que possa fazer gets, puts, ...
    public CompletableFuture< Response > postDataAsyncWithBasicAuth(
            String path
            ,String user
            ,String password
            ,List<Param> parameters
    ){
        CompletableFuture< Response > promise = new CompletableFuture<>();
        _client
                .preparePost( path )
                .setFormParams( parameters )

                // Pra ter Basic Authentication
                .setRealm( basicAuthRealm( user, password ).build() )

                .execute(
                        new AsyncCompletionHandler< Object >() {

                            @Override
                            public Object onCompleted( Response response ) throws Exception {
                                promise.complete( response );
                                return response;
                            }
                        }
                );
        return promise;
    }

    public CompletableFuture< Response > putDataAsyncWithBasicAuth(
            String path
            ,String user
            ,String password
            ,List<Param> parameters
    ){
        CompletableFuture< Response > promise = new CompletableFuture<>();
        _client
                .preparePut( path )
                .setFormParams( parameters )

                // Pra ter Basic Authentication
                .setRealm( basicAuthRealm( user, password ).build() )

                .execute(
                        new AsyncCompletionHandler< Object >() {

                            @Override
                            public Object onCompleted( Response response ) throws Exception {
                                promise.complete( response );
                                return response;
                            }
                        }
                );
        return promise;
    }

    public CompletableFuture< Response > postDataAsync(
            String path
            ,List<Param> parameters
    ){
        CompletableFuture< Response > promise = new CompletableFuture<>();
        _client
                .preparePost( path )
                .setFormParams( parameters )

                .execute(
                        new AsyncCompletionHandler< Object >() {

                            @Override
                            public Object onCompleted( Response response ) throws Exception {
                                promise.complete( response );
                                return response;
                            }
                        }
                );
        return promise;
    }


    public CompletableFuture< Response > getDataAsync( String path ){

        CompletableFuture< Response > promise = new CompletableFuture<>();

        _client
            .prepareGet( path )
            .execute(
                new AsyncCompletionHandler< Object >() {

                    @Override
                    public Object onCompleted( Response response ) throws Exception {
                        promise.complete( response );
                        return response;
                    }
                }
            );
        return promise;
    }

    @Override
    public void close() throws IOException {
        if( !_client.isClosed() ){
            _client.close();
        }
    }
}
