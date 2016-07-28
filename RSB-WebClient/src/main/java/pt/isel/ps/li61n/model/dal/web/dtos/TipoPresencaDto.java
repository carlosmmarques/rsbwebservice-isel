package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 28/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TipoPresencaDto {

    public final String id;
    public final String uri_tipopresenca;
    public final String uri_tipopresencaemreforco;
    public final Boolean reforco;
    public final String abreviatura;
    public final Boolean ausencia;
    public final String descricao;

    public TipoPresencaDto(
            String id
            ,String uri_tipopresenca
            ,String uri_tipopresencaemreforco
            ,Boolean reforco
            ,String abreviatura
            ,Boolean ausencia
            ,String descricao
    ) {
        this.id = id;
        this.uri_tipopresenca = uri_tipopresenca;
        this.uri_tipopresencaemreforco = uri_tipopresencaemreforco;
        this.reforco = reforco;
        this.abreviatura = abreviatura;
        this.ausencia = ausencia;
        this.descricao = descricao;
    }
}
