package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 28/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ResponsabilidadeOperacionalDto {

    public final Long id;
    public final String uri_tipoPresenca;
    public final String sigla;
    public final String tipoServico;
    public final Boolean dependeFactorElegibilidade;
    public final String designacao;

    public ResponsabilidadeOperacionalDto(
                Long id
                ,String uri_tipoPresenca
                ,String sigla
                ,String tipoServico
                ,Boolean dependeFactorElegibilidade
                ,String designacao
    ) {
        this.id = id;
        this.uri_tipoPresenca = uri_tipoPresenca;
        this.sigla = sigla;
        this.tipoServico = tipoServico;
        this.dependeFactorElegibilidade = dependeFactorElegibilidade;
        this.designacao = designacao;
    }
}
