package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TipoUnidadeEstruturalDto {

    public final Long id;
    public final String uri_tipoUnidadeEstrutural;
    public final String designacao;

    // Detalhes
    public final Integer nivelHierarquicoMaximoMae;
    public final String descricao;

    public TipoUnidadeEstruturalDto(
                Long id
                ,String uri_tipoUnidadeEstrutural
                ,String designacao
    ) {
        this( id, uri_tipoUnidadeEstrutural, designacao, null, null );
    }

    public TipoUnidadeEstruturalDto(
            Long id
            ,String uri_tipoUnidadeEstrutural
            ,String designacao
            ,Integer nivelHierarquicoMaximoMae
            ,String descricao
    ) {
        this.id = id;
        this.uri_tipoUnidadeEstrutural = uri_tipoUnidadeEstrutural;
        this.designacao = designacao;
        this.nivelHierarquicoMaximoMae = nivelHierarquicoMaximoMae;
        this.descricao = descricao;
    }
}
