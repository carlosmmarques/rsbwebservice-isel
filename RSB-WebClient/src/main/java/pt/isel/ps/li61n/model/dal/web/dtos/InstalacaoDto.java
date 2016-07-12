package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class InstalacaoDto {

    public final Long id;
    public final String uri_instalacao;
    public final String localizacao;
    public final String designacao;
    public final String descricao;

    public InstalacaoDto(
            Long id
            ,String uri_instalacao
            ,String localizacao
            ,String designacao
            ,String descricao
    ) {
        this.id = id;
        this.uri_instalacao = uri_instalacao;
        this.localizacao = localizacao;
        this.designacao = designacao;
        this.descricao = descricao;
    }
}
