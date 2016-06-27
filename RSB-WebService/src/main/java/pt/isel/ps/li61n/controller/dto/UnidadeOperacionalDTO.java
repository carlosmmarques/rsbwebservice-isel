package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;

import javax.servlet.http.HttpServletRequest;

/**
 * UnidadeEstruturalDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadeOperacionalDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_unidadeOperacional;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipoUnidadeOperacional;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_instalacao;

    /**
     * Construtor
     * @param unidadeOperacional  Unidade Operacional a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public UnidadeOperacionalDTO(UnidadeOperacional unidadeOperacional, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(unidadeOperacional, request, modeloDeRepresentacao);
        this.uri_unidadeOperacional = String.format("%s/unidadeoperacional/%s",
                this.baseUrl,
                this.id);
        this.uri_tipoUnidadeOperacional = String.format("%s/unidadeoperacional/tipo/%s",
                this.baseUrl,
                unidadeOperacional.getTipoUnidadeOperacional().getId());
        this.uri_instalacao =String.format("%s/unidadeestrutural/%s/instalacao/%s",
                this.baseUrl,
                unidadeOperacional.getInstalacao().getUnidadeEstrutural().getId(),
                unidadeOperacional.getInstalacao().getId());
    }

}
