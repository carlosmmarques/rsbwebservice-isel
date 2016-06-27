package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;

import javax.servlet.http.HttpServletRequest;

/**
 * UnidadeEstruturalDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TipoUnidadeEstruturalDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipoUnidadeEstrutural;

    /**
     * Construtor
     * @param tipoUnidadeEstrutural  Tipo de Unidade Estrutural a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public TipoUnidadeEstruturalDTO(TipoUnidadeEstrutural tipoUnidadeEstrutural, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(tipoUnidadeEstrutural, request, modeloDeRepresentacao);
        this.uri_tipoUnidadeEstrutural = String.format("%s/unidadeestrutural/tipo/%s",
                this.baseUrl,
                this.id);
    }

}
