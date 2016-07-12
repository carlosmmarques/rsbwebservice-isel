package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 10/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class CategoriaDto {

    public final Long id;

    public final String descrição;

    public final String abreviatura;

    public final String quadro;

    public final Integer nivelHierarquico;

    public CategoriaDto(
            Long id,
            String descrição,
            String abreviatura,
            String quadro,
            Integer nivelHierarquico
    ) {
        this.id = id;
        this.descrição = descrição;
        this.abreviatura = abreviatura;
        this.quadro = quadro;
        this.nivelHierarquico = nivelHierarquico;
    }
}
