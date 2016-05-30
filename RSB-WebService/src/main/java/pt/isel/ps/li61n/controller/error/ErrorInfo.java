package pt.isel.ps.li61n.controller.error;

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

    public ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }

}
