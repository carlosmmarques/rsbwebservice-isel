package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.Representation;
import pt.isel.ps.li61n.model.entities.ResponsabilidadeOperacional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ResponsabilidadeOperacionalDTO - Description
 * Created on 16/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ResponsabilidadeOperacionalDTO<T> {
    final Map<String, String> resposnsabilidadeOperacionalMap = new HashMap<>();
    private Class<T> representationModel;
    private String baseUrl;
    @JsonView({Representation.Summary.class, Representation.Normal.class, Representation.Verbose.class})
    private Long id;
    @JsonView({Representation.Summary.class, Representation.Normal.class, Representation.Verbose.class})
    private String uri_tipoPresenca;

    /**
     * Construtor
     *  @param responsabilidadeOperacional Responsabilidade Operacional a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param representationModel Modelo de representação da entidade
     */
    public ResponsabilidadeOperacionalDTO(ResponsabilidadeOperacional responsabilidadeOperacional
            , HttpServletRequest request
            , Class<T> representationModel) {
        this.representationModel = representationModel;
        baseUrl = String.format("%s://%s:%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        this.id = responsabilidadeOperacional.getId();
        this.uri_tipoPresenca = String.format("%s/presenca/tipo/%s",
                this.baseUrl,
                responsabilidadeOperacional.getTipoPresenca().getId());
        for (Field field : responsabilidadeOperacional.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonView.class))
                if (Arrays.asList(field.getAnnotation(JsonView.class).value()).contains(this.representationModel)) {
                    try {
                        String value;
                        field.setAccessible(true);
                        value = field.get(responsabilidadeOperacional) == null ? "" : field.get(responsabilidadeOperacional).toString();
                        resposnsabilidadeOperacionalMap.put(field.getName(), value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @JsonAnyGetter
    public Map<String, String> getResposnsabilidadeOperacionalMap() {
        return resposnsabilidadeOperacionalMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri_tipoPresenca() {
        return uri_tipoPresenca;
    }

    public void setUri_tipoPresenca(String uri_tipoPresenca) {
        this.uri_tipoPresenca = uri_tipoPresenca;
    }
}
