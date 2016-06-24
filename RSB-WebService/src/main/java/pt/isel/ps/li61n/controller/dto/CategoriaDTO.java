package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.Categoria;

import javax.servlet.http.HttpServletRequest;

/**
 * CategoriaDTO -  Classe de representação dos elementos do pessoal.
 * Created on 19/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class CategoriaDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_categoria;

    /**
     * Construtor
     * @param categoria  Categoria a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public CategoriaDTO(Categoria categoria, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(categoria, request, modeloDeRepresentacao);
        this.uri_categoria = String.format("%s/pessoal/categoria/%s",
                this.baseUrl,
                this.id);
    }
}
