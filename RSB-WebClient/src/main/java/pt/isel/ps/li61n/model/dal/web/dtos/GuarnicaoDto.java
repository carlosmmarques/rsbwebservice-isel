package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 28/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class GuarnicaoDto {

    public final Long id;
    public final String uri_responsabilidadeoperacional;
    public final Integer minimo;
    public final Integer maximo;

    public GuarnicaoDto(
                    Long id
                    ,String uri_responsabilidadeoperacional
                    ,Integer minimo
                    ,Integer maximo
    ) {
        this.id = id;
        this.uri_responsabilidadeoperacional = uri_responsabilidadeoperacional;
        this.minimo = minimo;
        this.maximo = maximo;
    }
}
