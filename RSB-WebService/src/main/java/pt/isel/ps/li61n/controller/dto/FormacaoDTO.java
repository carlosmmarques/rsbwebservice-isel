package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.Formacao;

import javax.servlet.http.HttpServletRequest;

/**
 * FormacaoDTO - Description
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class FormacaoDTO extends AbstractDTO{

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_formacao;

    /**
     * Construtor
     * @param formacao Formação a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public FormacaoDTO(Formacao formacao, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(formacao, request, modeloDeRepresentacao);
        this.uri_formacao = String.format("%s/pessoal/formacao/%s",
                this.baseUrl,
                this.id);
    }

}
