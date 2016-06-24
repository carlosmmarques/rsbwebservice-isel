package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.PostoFuncional;

import javax.servlet.http.HttpServletRequest;

/**
 * PostoFuncionalDTO - Description
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PostoFuncionalDTO extends AbstractDTO{

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_postofuncional;

    /**
     * Construtor
     * @param postoFuncional Categoria a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public PostoFuncionalDTO(PostoFuncional postoFuncional, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(postoFuncional, request, modeloDeRepresentacao);
        this.uri_postofuncional = String.format("%s/pessoal/postofuncional/%s",
                this.baseUrl,
                this.id);
    }

}
