package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.Representation;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe de representação dos elementos do pessoal.
 */
public class PessoalDTO<T> {
    final Map<String, String> pessoaMap = new HashMap<>();
    private Class<T> representationModel;
    private String baseUrl;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private Long id;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String uri_pessoa;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String uri_instalacaoPorOmissao;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String uri_postoFuncionalPorOmissao;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String uri_tipoPresencaPorOmissao;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String uri_turnoPorOmissao;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String uri_categoria;

    /**
     * Construtor
     *  @param elemento  registoFormação a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param representationModel Modelo de representação da entidade
     */
    public PessoalDTO(ElementoDoPessoal elemento, HttpServletRequest request, Class<T> representationModel) {
        this.representationModel = representationModel;
        baseUrl = String.format("%s://%s:%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        this.id = elemento.getId();
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
//        this.uri_categoria = String.format("%s/categoria/%s",
//                this.baseUrl,
//                elemento.getAtribuicaoDeCategorias().stream()
//                        .sorted((o1, o2) -> o2.getDataAtribuicaoCategoria().compareTo(o1.getDataAtribuicaoCategoria()))
//                        .findFirst().get()
//                        .getCategoria().getId());
        for (Field field : elemento.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonView.class))
                if (Arrays.asList(field.getAnnotation(JsonView.class).value()).contains(this.representationModel)) {
                    try {
                        String value;
                        field.setAccessible(true);
                        value = field.get(elemento) == null ? "" : field.get(elemento).toString();
                        pessoaMap.put(field.getName(), value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    /**
     * @return id do elemento do pessoal
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id id do elemento do pessoal
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return mapa chave valor com as propriedades anotadas
     */
    @JsonAnyGetter
    public Map<String, String> getPessoaMap() {
        return pessoaMap;
    }

    /**
     * @return Uri do recurso elemento do pessoal especifica
     */
    public String getUri_pessoa() {
        return uri_pessoa;
    }

    /**
     * @param uri_pessoa Uri do recurso elemento do pessoal  especifica
     */
    public void setUri_pessoa(String uri_pessoa) {
        this.uri_pessoa = uri_pessoa;
    }

    /**
     * @return Uri da Instalação por omissão do elemento
     */
    public String getUri_instalacaoPorOmissao() {
        return uri_instalacaoPorOmissao;
    }

    /**
     * @param uri_instalacaoPorOmissao Uri da Instalação por omissão do elemento
     */
    public void setUri_instalacaoPorOmissao(String uri_instalacaoPorOmissao) {
        this.uri_instalacaoPorOmissao = uri_instalacaoPorOmissao;
    }

    /**
     * @return Uri do Posto Funcional por omissão do elemento
     */
    public String getUri_postoFuncionalPorOmissao() {
        return uri_postoFuncionalPorOmissao;
    }

    /**
     * @param uri_postoFuncionalPorOmissao Uri do Posto Funcional por omissão do elemento
     */
    public void setUri_postoFuncionalPorOmissao(String uri_postoFuncionalPorOmissao) {
        this.uri_postoFuncionalPorOmissao = uri_postoFuncionalPorOmissao;
    }

    /**
     * @return Uri do tipo de presença por omissão do elemento
     */
    public String getUri_tipoPresencaPorOmissao() {
        return uri_tipoPresencaPorOmissao;
    }

    /**
     * @param uri_tipoPresencaPorOmissao Uri do tipo de presença por omissão do elemento
     */
    public void setUri_tipoPresencaPorOmissao(String uri_tipoPresencaPorOmissao) {
        this.uri_tipoPresencaPorOmissao = uri_tipoPresencaPorOmissao;
    }

    /**
     * @return Uri do Turno por omissão do elemento
     */
    public String getUri_turnoPorOmissao() {
        return uri_turnoPorOmissao;
    }

    /**
     * @param uri_turnoPorOmissao Uri do Turno por omissão do elemento
     */
    public void setUri_turnoPorOmissao(String uri_turnoPorOmissao) {
        this.uri_turnoPorOmissao = uri_turnoPorOmissao;
    }
}
