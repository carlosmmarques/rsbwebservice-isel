package pt.isel.ps.li61n.controller;

import static pt.isel.ps.li61n.RsbWebClientApplication.*;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UrlGenerator {

    private final static String REDIRECT =  "redirect:%s";

    private static String baseOperation( String basePath, String id ){
        return String.format( "%s/%s", basePath, id );
    }

    public static String detalhesPessoal( Long elementoId ){
        return baseOperation( PESSOAL_URL, elementoId.toString() );
    }

    public static String redirectDetalhesPessoal( Long elementoId ){
        return String.format( REDIRECT, detalhesPessoal( elementoId ) );
    }

    public static String detalhesUnidadadeOperacional( Long elementoId ){
        return operacoesUnidadadeEstrutural( elementoId.toString() );
    }

    public static String operacoesUnidadadeEstrutural( String operation ){
        return baseOperation( UNIDADES_ESTRUTURAIS_URL, operation );
    }

    public static String operacoesUnidadadeEstrutural( Long ueId, String operation ){
        return baseOperation( detalhesUnidadadeOperacional( ueId ), operation );
    }

    public static String detalhesInstalacao( Long unidadeEstruturalId, Long instalacaoId ){
        return operacaoInstalacao( unidadeEstruturalId, instalacaoId.toString() );
    }


    public static String operacaoInstalacao(Long unidadeEstruturalId, String operationName ){
        return String.format(
                "%s/%d%s/%s"
                ,UNIDADES_ESTRUTURAIS_URL
                ,unidadeEstruturalId
                ,INSTALACOES_BASE_URL
                ,operationName
        );
    }

    public static String redirectDetalhesInstalacao( Long unidadeEstruturalId, Long instalacaoId ){
        return String.format(
                REDIRECT
                ,detalhesInstalacao( unidadeEstruturalId, instalacaoId )
        );
    }

    public static String detalhesMapaForcaPeriodo( Long idPeriodo ){
        return baseOperation( MAPA_FORCA_URL, idPeriodo.toString() );
    }

    public static String redirectMapaForcaPeriodo( Long idPeriodo ){
        return String.format(
                        REDIRECT,
                        baseOperation( MAPA_FORCA_URL, idPeriodo.toString() )
                    );
    }
}
