package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;
import pt.isel.ps.li61n.model.entities.AlgoritmoCalculoTurno;

import javax.servlet.http.HttpServletRequest;

/**
 * AlgoritmoCalculoTurnoDTO - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class AlgoritmoCalculoTurnoDTO extends AbstractDTO{

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String uri_algoritmocalculoturno;

    /**
     * Construtor
     * @param algoritmoCalculoTurno  Algoritmo de Calculo de Turno a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     * @param modeloDeRepresentacao Modelo de representação da categoria
     */
    public AlgoritmoCalculoTurnoDTO(
            AlgoritmoCalculoTurno algoritmoCalculoTurno,
            HttpServletRequest request,
            Class modeloDeRepresentacao
    ) {
        this.popularAtributosGerais(algoritmoCalculoTurno, request, modeloDeRepresentacao);
        this.uri_algoritmocalculoturno = String.format("%s/turno/algoritmocalculoturno/%s",
                this.baseUrl,
                this.id);
    }
}
