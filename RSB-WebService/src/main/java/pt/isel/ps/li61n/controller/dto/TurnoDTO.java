package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.Turno;

import javax.servlet.http.HttpServletRequest;

/**
 * TurnoDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TurnoDTO extends AbstractDTO {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_turno;

    /**
     * Construtor
     * @param turno  Turno a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public TurnoDTO(Turno turno, HttpServletRequest request, Class modeloDeRepresentacao) {
        this.popularAtributosGerais(turno, request, modeloDeRepresentacao);
        this.uri_turno = String.format("%s/turno/%s",
                this.baseUrl,
                this.id);
    }

}
