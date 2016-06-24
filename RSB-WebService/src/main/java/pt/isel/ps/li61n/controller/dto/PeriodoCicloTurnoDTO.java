package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.PeriodoCicloTurno;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * PeriodoCicloTurnoDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PeriodoCicloTurnoDTO {

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    // TipoPresenca não extende RSBAbstractEntity----------------------------------------------------------------------------
    private Class modeloDeRepresentacao;
    protected String baseUrl;
    private final Map<String, String> mapaDeAtributos = new HashMap<>();
    //-----------------------------------------------------------------------------------------------------------------------
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_periodocicloturno;


    /**
     * Construtor
     * @param periodoCicloTurno  Periodo de Ciclo de Turno a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public PeriodoCicloTurnoDTO(
            PeriodoCicloTurno periodoCicloTurno,
            HttpServletRequest request,
            Class modeloDeRepresentacao
    ) {
        this.popularAtributosGerais(periodoCicloTurno, request, modeloDeRepresentacao);
        this.uri_periodocicloturno = String.format("%s/turno/algoritmocalculoturno/%s/ciclo/%s",
                this.baseUrl,
                periodoCicloTurno.getAlgoritmoCalculoTurno().getId(),
                periodoCicloTurno.getOrdemPeriodoCiclo()

        );
    }

    /**
     * @param periodoCicloTurno Entidade do tipo TipoPresenca
     * @param request HttpServletRequest
     * @param modeloDeRepresentacao Modelo de representação (Sumario, Normal, Verboso)
     */
    public void popularAtributosGerais(PeriodoCicloTurno periodoCicloTurno, HttpServletRequest request, Class modeloDeRepresentacao){

        baseUrl = String.format("%s://%s:%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        this.modeloDeRepresentacao = modeloDeRepresentacao;

        for (Field field : periodoCicloTurno.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonView.class))
                if (Arrays.asList(field.getAnnotation(JsonView.class).value()).contains(this.modeloDeRepresentacao)) {
                    try {
                        String value;
                        field.setAccessible(true);
                        value = field.get(periodoCicloTurno) == null ? "" : field.get(periodoCicloTurno).toString();
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
