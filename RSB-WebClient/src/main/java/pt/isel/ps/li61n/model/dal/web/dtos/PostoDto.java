package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PostoDto {

    public final Long id;
    public final String designacao;

    public PostoDto( Long id, String designacao ) {
        this.id = id;
        this.designacao = designacao;
    }
}
