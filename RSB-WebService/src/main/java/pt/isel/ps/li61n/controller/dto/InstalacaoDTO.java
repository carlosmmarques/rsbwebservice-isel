package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.Instalacao;

import javax.servlet.http.HttpServletRequest;

/**
 * UnidadeEstruturalDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class InstalacaoDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_instalacao;

    /**
     * Construtor
     * @param instalacao  Tipo de Unidade Estrutural a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public InstalacaoDTO(Instalacao instalacao, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(instalacao, request, modeloDeRepresentacao);
        this.uri_instalacao = String.format("%s/unidadeestrutural/%s/instalacao/%s",
                this.baseUrl,
                instalacao.getUnidadeEstrutural().getId(),
                this.id);
    }
}
