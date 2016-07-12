package pt.isel.ps.li61n.util.web;

import org.asynchttpclient.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created on 09/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class HttpAsyncTest {

    private HttpAsync _httpAsync;

    @Before
    public void prepare(){
        _httpAsync = new HttpAsync();
    }

    @Test public void postDataAsyncWithBasicAuth() throws ExecutionException, InterruptedException {
        //
        // Arrange
        //
        String uriTest = "http://localhost:1234/pessoal";
        String user = "user";
        String password = "password";

        //
        // Act
        //
        CompletableFuture< Response > getResult = _httpAsync
                                                    .postDataAsyncWithBasicAuth(
                                                            uriTest
                                                        ,user
                                                        ,password
                                                        ,new LinkedList<>()
                                                    );

        Response test = getResult.get();

        String uriResult = test.getUri().toString();

        // Assert
        //
        assertEquals( uriTest, uriResult );
    }
}
