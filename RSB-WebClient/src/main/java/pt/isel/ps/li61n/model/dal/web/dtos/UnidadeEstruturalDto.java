package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadeEstruturalDto {

    public final Long id;
    public final String uri_unidadeEstrutural;
    public final String uri_tipoUnidadeEstrutural;
    public final String uri_unidadeEstruturalMae;
    public final String designacao;
    //public final String nivelHierarquico;

    public UnidadeEstruturalDto(
                Long id
                ,String uri_unidadeEstrutural
                ,String uri_tipoUnidadeEstrutural
                ,String uri_unidadeEstruturalMae
                ,String designacao
             //   ,String nivelHierarquico
    ) {
        this.id = id;
        this.uri_unidadeEstrutural = uri_unidadeEstrutural;
        this.uri_tipoUnidadeEstrutural = uri_tipoUnidadeEstrutural;
        this.uri_unidadeEstruturalMae = uri_unidadeEstruturalMae;
        this.designacao = designacao;
        //this.nivelHierarquico = nivelHierarquico;
    }
}
