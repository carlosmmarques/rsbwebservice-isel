package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.Periodo;

import javax.servlet.http.HttpServletRequest;

/**
 * PeriodoDTO - Description
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PeriodoDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_periodo;

    /**
     * Construtor
     *
     * @param periodo               Periodo a representar
     * @param request               HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da entidade
     */
    public PeriodoDTO(Periodo periodo, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(periodo, request, modeloDeRepresentacao);
        this.uri_periodo = String.format("%s/presenca/periodo/%s",
                this.baseUrl,
                this.id);
    }
}