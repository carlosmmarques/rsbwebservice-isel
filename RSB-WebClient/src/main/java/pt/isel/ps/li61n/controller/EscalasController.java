package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pt.isel.ps.li61n.model.IPresencasLogic;
import pt.isel.ps.li61n.model.ITurnosLogic;
import pt.isel.ps.li61n.model.IUnidadesEstruturaisLogic;
import pt.isel.ps.li61n.model.IUnidadesOperacionaisLogic;
import pt.isel.ps.li61n.model.entities.*;
import pt.isel.ps.li61n.viewModel.EscalaFormViewModel;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import static pt.isel.ps.li61n.RsbWebClientApplication.ESCALAS_URL;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping( ESCALAS_URL )
public class EscalasController {

    /**
     * Definição globais para facilitar a utilização nos testes.
     */
    public static final String
            VIEW_BASE = "escalas"
            ,VIEW_NAME_INDEX = VIEW_BASE + "/index"
            ,VIEW_NAME_GERAR = VIEW_BASE + "/gerar"
            ,VIEW_NAME_DETAILS = VIEW_BASE + "/details"
    ;

    private ITurnosLogic _turnosLogic;
    private IUnidadesEstruturaisLogic _uesLogic;
    private IUnidadesOperacionaisLogic _unOpsLogic;
    private IPresencasLogic _presencasLogic;


    @Autowired
    public EscalasController(
            ITurnosLogic turnosLogic
            ,IUnidadesEstruturaisLogic unidadesEstruturaisLogic
            ,IUnidadesOperacionaisLogic unidadesOperacionaisLogic
            ,IPresencasLogic presencasLogic
    ) {
        _turnosLogic = turnosLogic;
        _uesLogic = unidadesEstruturaisLogic;
        _unOpsLogic = unidadesOperacionaisLogic;
        _presencasLogic = presencasLogic;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return VIEW_NAME_INDEX;
    }

    @RequestMapping(value = "/gerar", method = RequestMethod.GET)
    public String gerarEscalaView(Model model) {

        Collection<Instalacao> instalacoes = _uesLogic.getAllInstalacoes();
        model.addAttribute( "instalacoes", instalacoes );

        Collection<Turno> turnos = _turnosLogic.getAll();
        model.addAttribute( "turnos", turnos );

        String action = ESCALAS_URL + "/details";
        model.addAttribute( "action", action );

        model.addAttribute( new EscalaFormViewModel() );

        return VIEW_NAME_GERAR;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String gerarEscalaView(
            Model model
            ,@RequestParam( "instalacaoId" ) Long instalacaoId
            ,@RequestParam( "turnoId" ) Long turnoId
            ,@RequestParam( "data" ) String data
    ) {
        //obter unidadades operacionais da instalacaoId
        Collection< UnidadeOperacional > unOps = _unOpsLogic.getAllByInstalacao( instalacaoId );

        // Para cada unidadeOperacional, obter guarnicao
        for( UnidadeOperacional unOp : unOps ){
            Long unOpId = unOp.getId();
            Collection< Guarnicao > guarnicao = _unOpsLogic.getGuarnicao( unOpId );
            unOp.setGuarnicao( guarnicao );
        }

        LocalDate dataAux = LocalDate.parse( data );

        //obter presencas da instalacao, turno e dia
        Collection< Presenca > presencas = _presencasLogic.getAll()
                                                            .stream()
                                                                .filter( p -> p.getInstalacao().getId().equals( instalacaoId ) )
                                                                .filter( p -> p.getData().isEqual( dataAux ) )
                                                                .filter( p -> p.getTurno().getId().equals( turnoId ) )
                                                                .collect( Collectors.toList() );

        // Para cada Unidade Operacional com guarnicao, preencher com elementos das preencas.


        return VIEW_NAME_DETAILS;
    }
}