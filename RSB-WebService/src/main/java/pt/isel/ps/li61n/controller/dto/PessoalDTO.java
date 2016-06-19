package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe de representação dos elementos do pessoal.
 */
public class PessoalDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_pessoa;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_instalacaoPorOmissao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_postoFuncionalPorOmissao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipoPresencaPorOmissao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_turnoPorOmissao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_categoria;

    /**
     * Construtor
     *  @param elemento  registoFormação a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da entidade
     */
    public PessoalDTO(ElementoDoPessoal elemento, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(elemento, request, modeloDeRepresentacao);
        this.uri_pessoa = String.format("%s/pessoal/%s",
                this.baseUrl,
                this.id);
        this.uri_instalacaoPorOmissao = String.format("%s/unidadeestrutural/%s/instalacao/%s",
                this.baseUrl,
                elemento.getInstalacao().getUnidadeEstrutural().getId(),
                elemento.getInstalacao().getId());
        this.uri_postoFuncionalPorOmissao = String.format("%s/pessoal/postofuncional/%s",
                this.baseUrl,
                elemento.getPostoFuncional().getId());
        this.uri_tipoPresencaPorOmissao = String.format("%s/presenca/tipo/%s",
                this.baseUrl,
                elemento.getTipoPresenca().getId());
        this.uri_turnoPorOmissao = String.format("%s/turno/%s",
                this.baseUrl,
                elemento.getTurno().getId());
        this.uri_categoria = String.format("%s/categoria/%s",
                this.baseUrl,
                elemento.getAtribuicõesDeCategoria().stream()
                        .sorted((o1, o2) -> o2.getDataAtribuicaoCategoria().compareTo(o1.getDataAtribuicaoCategoria()))
                        .findFirst().get()
                        .getCategoria().getId());
    }
}
