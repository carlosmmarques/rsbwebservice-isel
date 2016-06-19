package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.RegistoFormacao;

import javax.servlet.http.HttpServletRequest;

/**
 * RegistoFormacaoDTO - Description
 * Created on 02/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class RegistoFormacaoDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_registoFormacaoDoElemento;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_formacao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String designacao;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Float validade;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String descricao;


    /**
     * Construtor
     *  @param registoFormacao  registoFormação a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da registoFormacao
     */
    public RegistoFormacaoDTO(RegistoFormacao registoFormacao, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(registoFormacao, request, modeloDeRepresentacao);
        this.uri_registoFormacaoDoElemento = String.format("%s/pessoal/%s/formacao/%s",
                this.baseUrl,
                registoFormacao.getElementoDoPessoal().getId(),
                registoFormacao.getId());
        this.uri_formacao = String.format("%s/formacao/%s",
                this.baseUrl,
                registoFormacao.getFormacao().getId());
        this.designacao = registoFormacao.getFormacao().getDesignacao();
        this.descricao = registoFormacao.getFormacao().getDescricao();
        this.validade = registoFormacao.getFormacao().getValidade();
    }
}
