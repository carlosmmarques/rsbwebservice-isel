package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.Presenca;

import javax.servlet.http.HttpServletRequest;

/**
 * PresencaDTO - Description
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PresencaDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_presenca;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_elementodopessoal;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_elementoreforco;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_elementoreforcado;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_instalacao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_periodo;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_postofuncionalefectivo;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipopresencaefectiva;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_turnoefectivo;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String tipopresencaefectivs;


    /**
     * Construtor
     *
     * @param presenca              Presença a representar
     * @param request               HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da entidade
     */
    public PresencaDTO(Presenca presenca, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(presenca, request, modeloDeRepresentacao);
        this.uri_presenca = String.format("%s/presenca/%s",
                this.baseUrl,
                this.id);
        this.uri_elementodopessoal = String.format("%s/pessoal/%s",
                this.baseUrl,
                presenca.getElementoDoPessoal().getId());
        this.uri_elementoreforco =
                presenca.getElementoReforco() == null ? "" :
                String.format("%s/pessoal/%s",
                        this.baseUrl,
                        presenca.getElementoReforco().getId());
        this.uri_elementoreforcado =
                presenca.getElementoReforcado() == null ? "" :
                String.format("%s/pessoal/%s",
                this.baseUrl,
                presenca.getElementoReforcado().getId());
        this.uri_instalacao = String.format("%s/unidadeestrutural/%s/instalacao/%s",
                this.baseUrl,
                presenca.getInstalacaoEfectiva().getUnidadeEstrutural().getId(),
                presenca.getInstalacaoEfectiva().getId());
        this.uri_periodo = String.format("%s/presenca/periodo/%s",
                this.baseUrl,
                presenca.getPeriodo().getId());
        this.uri_postofuncionalefectivo = String.format("%s/pessoal/postofuncional/%s",
                this.baseUrl,
                presenca.getPostoFuncionalEfectivo().getId());
        this.uri_tipopresencaefectiva = String.format("%s/presenca/tipo/%s",
                this.baseUrl,
                presenca.getTipoPresencaEfectiva().getId());
        this.uri_turnoefectivo = String.format("%s/turno/%s",
                this.baseUrl,
                presenca.getTurnoEfectivo().getId());
        this.tipopresencaefectivs = presenca.getTipoPresencaEfectiva().getId();
    }

}
