package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import javax.servlet.http.HttpServletRequest;

/**
 * UnidadeEstruturalDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadeEstruturalDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_unidadeEstrutural;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipoUnidadeEstrutural;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_unidadeEstruturalMae;

    /**
     * Construtor
     * @param unidadeEstrutural  Unidade Estrutural a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public UnidadeEstruturalDTO(UnidadeEstrutural unidadeEstrutural, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(unidadeEstrutural, request, modeloDeRepresentacao);
        this.uri_unidadeEstrutural = String.format("%s/unidadeestrutural/%s",
                this.baseUrl,
                this.id);
        this.uri_tipoUnidadeEstrutural = String.format("%s/unidadeestrutural/tipo/%s",
                this.baseUrl,
                unidadeEstrutural.getTipoUnidadeEstrutural().getId());
        UnidadeEstrutural unidadeEstruturalMae = unidadeEstrutural.getUnidadeEstruturalMae();
        this.uri_unidadeEstruturalMae =
                unidadeEstruturalMae == null ? "" :
                String.format("%s/unidadeestrutural/%s",
                this.baseUrl,
                unidadeEstruturalMae.getId());
    }

}
