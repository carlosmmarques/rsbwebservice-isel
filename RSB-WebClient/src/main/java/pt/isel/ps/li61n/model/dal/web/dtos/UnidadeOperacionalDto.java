package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadeOperacionalDto {

    public final Long id;

    public final String
                    uri_unidadeOperacional
                    ,uri_tipoUnidadeOperacional
                    ,uri_instalacao
                    ,designacao
    ;

    public final Boolean operacional;

    public UnidadeOperacionalDto(
            Long id
            ,String uri_unidadeOperacional
            ,String uri_tipoUnidadeOperacional
            ,String uri_instalacao
            ,String designacao
            ,Boolean operacional
    ) {
        this.id = id;
        this.uri_unidadeOperacional = uri_unidadeOperacional;
        this.uri_tipoUnidadeOperacional = uri_tipoUnidadeOperacional;
        this.uri_instalacao = uri_instalacao;
        this.designacao = designacao;
        this.operacional = operacional;
    }
}
