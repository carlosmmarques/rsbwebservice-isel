package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.TipoPresenca;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TipoPresencaDTO - Description
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TipoPresencaDTO {

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    // TipoPresenca não extende RSBAbstractEntity----------------------------------------------------------------------------
    private Class modeloDeRepresentacao;
    protected String baseUrl;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    protected String id;
    private final Map<String, String> mapaDeAtributos = new HashMap<>();
    //-----------------------------------------------------------------------------------------------------------------------
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipopresenca;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_tipopresencaemreforco;


    /**
     * Construtor
     *  @param tipoPresenca Presença a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da entidade
     */
    public TipoPresencaDTO(TipoPresenca tipoPresenca, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(tipoPresenca, request, modeloDeRepresentacao);
        this.uri_tipopresenca = String.format("%s/presenca/tipo/%s",
                this.baseUrl,
                this.id);
        this.uri_tipopresencaemreforco =
                tipoPresenca.getTipoPresencaEmReforco() == null ? "" :
                String.format("%s/pessoal/tipo/%s",
                this.baseUrl,
                tipoPresenca.getTipoPresencaEmReforco().getId());
    }

    /**
     * @param tipoPresenca Entidade do tipo TipoPresenca
     * @param request HttpServletRequest
     * @param modeloDeRepresentacao Modelo de representação (Sumario, Normal, Verboso)
     */
    public void popularAtributosGerais(TipoPresenca tipoPresenca, HttpServletRequest request, Class modeloDeRepresentacao){

        baseUrl = String.format("%s://%s:%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        this.id = tipoPresenca.getId();
        this.modeloDeRepresentacao = modeloDeRepresentacao;

        for (Field field : tipoPresenca.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonView.class))
                if (Arrays.asList(field.getAnnotation(JsonView.class).value()).contains(this.modeloDeRepresentacao)) {
                    try {
                        String value;
                        field.setAccessible(true);
                        value = field.get(tipoPresenca) == null ? "" : field.get(tipoPresenca).toString();
                        mapaDeAtributos.put(field.getName(), value);
                    } catch (IllegalAccessException e) {
                        logger.error(e.getLocalizedMessage(), e.getCause());
                        e.printStackTrace();
                    }
                }
        }
    }

    @JsonAnyGetter
    public Map<String, String> getCategoriaMap() {
        return mapaDeAtributos;
    }

}
