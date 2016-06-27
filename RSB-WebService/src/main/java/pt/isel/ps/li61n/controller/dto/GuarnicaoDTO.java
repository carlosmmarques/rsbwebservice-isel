package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.Guarnicao;

import javax.servlet.http.HttpServletRequest;

/**
 * UnidadeEstruturalDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class GuarnicaoDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_guarnicao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_responsabilidadeoperacional;


    /**
     * Construtor
     * @param guarnicao  Guarnicao a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public GuarnicaoDTO(Guarnicao guarnicao, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(guarnicao, request, modeloDeRepresentacao);
        this.uri_guarnicao = String.format("%s/unidadeoperacional/%s/guarnicao/%s",
                this.baseUrl,
                guarnicao.getUnidadeOperacional().getId(),
                guarnicao.getId());
        this.uri_responsabilidadeoperacional = String.format("%s/pessoal/responsabilidadeoperacional/%s",
                this.baseUrl,
                guarnicao.getResponsabilidadeOperacional().getId());
    }
}
