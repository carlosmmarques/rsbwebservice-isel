package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TipoUnidadeOperacionalDto {

    public final Long id;
    public final String uri_tipoUnidadeOperacional;
    public final String designacao;
    public final String descricao;

    public TipoUnidadeOperacionalDto(
                Long id
                ,String uri_tipoUnidadeOperacional
                ,String designacao
                ,String descricao
    ) {
        this.id = id;
        this.uri_tipoUnidadeOperacional = uri_tipoUnidadeOperacional;
        this.designacao = designacao;
        this.descricao = descricao;
    }
}
