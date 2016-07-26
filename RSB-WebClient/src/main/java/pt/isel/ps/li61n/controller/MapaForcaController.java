package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pt.isel.ps.li61n.model.IMapaForcaLogic;
import pt.isel.ps.li61n.model.IUnidadesEstruturaisLogic;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.Periodo;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;
import pt.isel.ps.li61n.viewModel.InsertPeriodoUI;
import pt.isel.ps.li61n.viewModel.MapaForcaViewModel;
import pt.isel.ps.li61n.viewModel.SelectPresencasUI;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import static pt.isel.ps.li61n.RsbWebClientApplication.DATE_FORMATTER;
import static pt.isel.ps.li61n.RsbWebClientApplication.MAPA_FORCA_URL;

/**
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

@Controller
@RequestMapping( MAPA_FORCA_URL )
public class MapaForcaController {

    /**
     * Definição globais para facilitar a utilização nos testes.
     */
    public static final String
        VIEW_NAME_INDEX = MAPA_FORCA_URL + "/index"
        //VIEW_NAME_UE_ALL = MAPA_FORCA_URL + "/all",
        ,VIEW_NAME_DETAILS = MAPA_FORCA_URL + "/details"
        , VIEW_NAME_INSERT = MAPA_FORCA_URL + "/insert"
    ;

    private IMapaForcaLogic _logicMf;
    private IUnidadesEstruturaisLogic _logicUe;

    @Autowired
    public MapaForcaController(
                IMapaForcaLogic mapaForcaLogic
                ,IUnidadesEstruturaisLogic unidadesEstruturaisLogic
    ){

        _logicMf = mapaForcaLogic;
        _logicUe = unidadesEstruturaisLogic;
    }

    @RequestMapping( method = RequestMethod.GET)
    public String index( Model model ){

        Collection< Periodo > periodos =  _logicMf.getAllPeriodos();
        Collection< UnidadeEstrutural > ues = _logicUe.getAll();

        SelectPresencasUI selectPresencas = new SelectPresencasUI(
                                                            periodos
                                                            ,ues
                                            );

        model.addAttribute( "selectPresencasUI", selectPresencas  );
        model.addAttribute( "action", MAPA_FORCA_URL + "/pesquisar" );

        return VIEW_NAME_INDEX;
    }

    @RequestMapping(
            method = RequestMethod.GET
            ,value = "/gerar"
    )
    public String insertView( Model model ){
        model.addAttribute( "action", MAPA_FORCA_URL );
        model.addAttribute( new InsertPeriodoUI() );
        return VIEW_NAME_INSERT;
    }

    @RequestMapping(
            method = RequestMethod.GET
            ,value = "/pesquisar"
    )
    public String details(
            Model model
            ,@RequestParam( "periodoId" ) Long periodoId
            ,@RequestParam( "unidadeEstruturalId" ) Long ueId
    ){

        Periodo periodo = _logicMf.getOnePeriodo( periodoId );

        // seleccionar a unidade estrutural e obter as suas instalacoes
        UnidadeEstrutural ue = _logicUe.getOne( ueId );
        Collection< Instalacao > instalacaos = _logicUe.getAllInstalacoes( ueId );
        ue.setInstalacoes( instalacaos );

        Collection< Presenca > presencas = new LinkedList<>();

        for( Instalacao instalacao : instalacaos ){
            Long instalacaoId = instalacao.getId();
            Collection< Presenca > auxPresencas = _logicMf
                                                    .getPresencasByPeriodoAndInstalacao(
                                                                                periodoId
                                                                                ,instalacaoId
                                                    );
            presencas.addAll( auxPresencas );
        }

        MapaForcaViewModel viewModel = new MapaForcaViewModel(
                                                        periodo
                                                        ,ue
                                                        ,presencas );
        model.addAttribute( "viewModel", viewModel );

        return VIEW_NAME_DETAILS;
    }


    @RequestMapping( method = RequestMethod.POST )
    public String createPeriodo(
                    @Valid InsertPeriodoUI novoPeriodo
                    ,Errors errors
                    ,HttpServletResponse response
    ){
        if( errors.hasErrors() ){
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
            return VIEW_NAME_INSERT;
        }

        Periodo p = new Periodo();
        String dataInicio = novoPeriodo.getDataInicio();
        p.setDataInicio( LocalDate.parse( dataInicio, DATE_FORMATTER ) );

        String dataFim = novoPeriodo.getDataFim();
        p.setDataFim( LocalDate.parse( dataFim, DATE_FORMATTER ) );

        Long id = _logicMf.insertPeriodo( p );

        return "redirect:/mapaForca";
    }
}
