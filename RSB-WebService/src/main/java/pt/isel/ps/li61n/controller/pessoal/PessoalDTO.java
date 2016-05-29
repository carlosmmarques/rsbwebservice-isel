package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
import pt.isel.ps.li61n.model.entities.View;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe de representação dos elementos do pessoal.
 */
public class PessoalDTO {
    Map<String, String> pessoa_map = new HashMap<>();
    private String baseUrl;
    @JsonView(View.Summary.class)
    private Long id;
    @JsonView(View.Summary.class)
    private String uri_pessoa;
    @JsonView(View.Summary.class)
    private String uri_instalacaoPorOmissao;
    @JsonView(View.Summary.class)
    private String uri_postoFuncionalPorOmissao;
    @JsonView(View.Summary.class)
    private String uri_tipoPresencaPorOmissao;
    @JsonView(View.Summary.class)
    private String uri_turnoPorOmissao;

    /**
     * parameterless constructor
     */
    public PessoalDTO() {
    }

    /**
     * Construtor
     *
     * @param pessoa  registoFormação a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     */
    public PessoalDTO(ElementoDoPessoal pessoa, HttpServletRequest request) {
        baseUrl = String.format("%s://%s:%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        this.id = pessoa.getId();
        this.uri_pessoa = String.format("%s/pessoal/%s",
                this.baseUrl,
                this.id);
        this.uri_instalacaoPorOmissao = String.format("%s/unidadeestrutural/%s/instalacao/%s",
                this.baseUrl,
                pessoa.getInstalacao().getUnidadeEstrutural().getId(),
                pessoa.getInstalacao().getId());
        this.uri_postoFuncionalPorOmissao = String.format("%s/pessoal/postofuncional/%s",
                this.baseUrl,
                pessoa.getPostoFuncional().getId());
        this.uri_tipoPresencaPorOmissao = String.format("%s/presenca/tipo/%s",
                this.baseUrl,
                pessoa.getTipoPresenca().getId());
        this.uri_turnoPorOmissao = String.format("%s/turno/%s",
                this.baseUrl,
                pessoa.getTurno().getId());
        for (Field field : pessoa.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonView.class))
                if (field.getAnnotation(JsonView.class).value()[0].equals(View.Summary.class)) {
                    try {
                        String value;
                        field.setAccessible(true);
                        value = field.get(pessoa) == null ? "" : field.get(pessoa).toString();
                        pessoa_map.put(field.getName(), value);
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
     * @return mapa chave valor com as propriedades anotadas como sumárias
     */
    @JsonAnyGetter
    public Map<String, String> getPessoa_map() {
        return pessoa_map;
    }

    /**
     * @param pessoa_map mapa chave valor com as propriedades anotadas como sumárias
     */
    public void setPessoa_map(Map<String, String> pessoa_map) {
        this.pessoa_map = pessoa_map;
    }

    /**
     * @return Uri do recurso registoFormação especifica
     */
    public String getUri_pessoa() {
        return uri_pessoa;
    }

    /**
     * @param uri_pessoa Uri do recurso registoFormação especifica
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
