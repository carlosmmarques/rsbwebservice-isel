package pt.isel.ps.li61n.controller.error;

import javax.servlet.http.HttpServletRequest;

/**
 * ErrorInfo - Entidade que representa as excepções.
 * Created on 30/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ErrorInfo {
    private String url;
    private String message;

    private ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }

    /**
     * Metodo de fabricação de objecto descritor da informação do erro - ErrorInfo
     *
     * @param exc     Excepção a caracterizar
     * @param request HttpServletRequest
     * @return Objecto ErrorInfo
     */
    public static ErrorInfo getErrorInfo(RuntimeException exc, HttpServletRequest request) {
        String completeURL = request.getRequestURL().toString();
        if (request.getQueryString() != null)
            completeURL += "?" + request.getQueryString();
        return new ErrorInfo(completeURL, exc.getMessage());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
