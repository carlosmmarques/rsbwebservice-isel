package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.EntidadeAbstractaComIdentificador;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractDTO - Description
 * Created on 19/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class AbstractDTO<Entidade extends EntidadeAbstractaComIdentificador> {

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    private Class modeloDeRepresentacao;
    protected String baseUrl;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    protected Long id;
    private final Map<String, String> mapaDeAtributos = new HashMap<>();


    /**
     * @param entidade Entidade do tipo RSBAbstractEntity
     * @param request HttpServletRequest
     * @param modeloDeRepresentacao Modelo de representação (Sumario, Normal, Verboso)
     */
    public void popularAtributosGerais(Entidade entidade, HttpServletRequest request, Class modeloDeRepresentacao){

        baseUrl = String.format("%s://%s:%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        this.id = entidade.getId();
        this.modeloDeRepresentacao = modeloDeRepresentacao;

        UtilidadesParaDTO.gerarMapaDeAtributosParaDTO(logger, mapaDeAtributos, entidade, this.modeloDeRepresentacao);
    }

    @JsonAnyGetter
    public Map<String, String> getCategoriaMap() {
        return mapaDeAtributos;
    }

}
