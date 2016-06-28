package pt.isel.ps.li61n.util.web;

import static pt.isel.ps.li61n.RsbWebClientApplication.PESSOAL;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UrlGenerator {

    public static String detalhesPessoal( Long elementoId ){
        return String.format( "%s/%d", PESSOAL, elementoId );
    }
}
