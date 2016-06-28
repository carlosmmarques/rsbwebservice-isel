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

import org.asynchttpclient.*;
import org.asynchttpclient.netty.handler.HttpHandler;
import sun.security.util.Password;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
