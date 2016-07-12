package pt.isel.ps.li61n.model.dal.web.dtos;


import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * Created on 07/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public final class ErrorDto {

    public final String url;
    public final String message;

    public ErrorDto(
            String url
            ,String message
    ) {
        this.message = message;
        this.url = url;
    }
}
