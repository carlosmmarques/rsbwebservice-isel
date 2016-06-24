package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.ResponsabilidadeOperacional;

import javax.servlet.http.HttpServletRequest;

/**
 * ResponsabilidadeOperacionalDTO - Description
 * Created on 16/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ResponsabilidadeOperacionalDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipoPresenca;

    /**
     * Construtor
     *  @param responsabilidadeOperacional Responsabilidade Operacional a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da entidade
     */
    public ResponsabilidadeOperacionalDTO(ResponsabilidadeOperacional responsabilidadeOperacional
            , HttpServletRequest request
            , Class modeloDeRepresentacao) {
        this.popularAtributosGerais(responsabilidadeOperacional, request, modeloDeRepresentacao);
        this.uri_tipoPresenca = String.format("%s/presenca/tipo/%s",
                this.baseUrl,
                responsabilidadeOperacional.getTipoPresenca().getId());
    }
}
