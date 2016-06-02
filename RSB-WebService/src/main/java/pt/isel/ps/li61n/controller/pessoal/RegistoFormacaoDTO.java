package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.Representation;
import pt.isel.ps.li61n.model.entities.RegistoFormacao;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * RegistoFormacaoDTO - Description
 * Created on 02/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class RegistoFormacaoDTO<T> {
    final Map<String, String> pessoaMap = new HashMap<>();
    private Class<T> representationModel;
    private String baseUrl;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private Long id;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String uri_registoFormacaoEspecificaDoElemento;
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String uri_elementoPessoal;

    /**
     * Construtor
     *  @param registoFormacao  registoFormação a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param representationModel Modelo de representação da entidade
     */
    public RegistoFormacaoDTO(RegistoFormacao registoFormacao, HttpServletRequest request, Class<T> representationModel) {
        this.representationModel = representationModel;
        baseUrl = String.format("%s://%s:%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        this.id = registoFormacao.getId();
        this.uri_elementoPessoal =String.format("%s/pessoal/%s",
                this.baseUrl,
                registoFormacao.getElementoDoPessoal().getId());
        this.uri_registoFormacaoEspecificaDoElemento = String.format("%s/formacao/%s",
                this.uri_elementoPessoal,
                registoFormacao.getFormacao().getId());
        for (Field field : registoFormacao.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonView.class))
                if (Arrays.asList(field.getAnnotation(JsonView.class).value()).contains(this.representationModel)) {
                    try {
                        String value;
                        field.setAccessible(true);
                        value = field.get(registoFormacao) == null ? "" : field.get(registoFormacao).toString();
                        pessoaMap.put(field.getName(), value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    /**
     * @return Id do registo de formação
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id Id do registo de formação
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
     * @return uri
     */
    public String getUri_registoFormacaoEspecificaDoElemento() {
        return uri_registoFormacaoEspecificaDoElemento;
    }

    public void setUri_registoFormacaoEspecificaDoElemento(String uri_registoFormacaoEspecificaDoElemento) {
        this.uri_registoFormacaoEspecificaDoElemento = uri_registoFormacaoEspecificaDoElemento;
    }

    public String getUri_elementoPessoal() {
        return uri_elementoPessoal;
    }

    public void setUri_elementoPessoal(String uri_elementoPessoal) {
        this.uri_elementoPessoal = uri_elementoPessoal;
    }
}
