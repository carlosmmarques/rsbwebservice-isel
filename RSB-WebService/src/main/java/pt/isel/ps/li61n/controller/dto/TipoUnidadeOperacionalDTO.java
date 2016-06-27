package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;

import javax.servlet.http.HttpServletRequest;

/**
 * UnidadeEstruturalDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TipoUnidadeOperacionalDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipoUnidadeOperacional;

    /**
     * Construtor
     * @param tipoUnidadeOperacional  Tipo de Unidade Operacional a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public TipoUnidadeOperacionalDTO(TipoUnidadeOperacional tipoUnidadeOperacional, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(tipoUnidadeOperacional, request, modeloDeRepresentacao);
        this.uri_tipoUnidadeOperacional = String.format("%s/unidadeoperacional/tipo/%s",
                this.baseUrl,
                tipoUnidadeOperacional.getId());
    }
}
