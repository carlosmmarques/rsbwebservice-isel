package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.IMapaForcaLogic;
import pt.isel.ps.li61n.model.IPeriodosLogic;
import pt.isel.ps.li61n.model.IPessoalLogic;
import pt.isel.ps.li61n.model.IPresencasLogic;
import pt.isel.ps.li61n.model.entities.Periodo;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.viewModel.MapaForcaViewModel;
import pt.isel.ps.li61n.viewModel.PeriodoUI;


import java.util.ArrayList;
import java.util.Collection;

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
        //VIEW_NAME_UE_INSERT = MAPA_FORCA_URL + "/insert"
    ;

    private IMapaForcaLogic _logic;

    @Autowired
    public MapaForcaController( IMapaForcaLogic mapaForcaLogic ){
        _logic = mapaForcaLogic;
    }

    @RequestMapping( method = RequestMethod.GET)
    public String index( Model model ){

        Collection< Periodo > periodos =  _logic.getAllPeriodos();

        int size = periodos.size();

        Collection< PeriodoUI > periodosUI = null;

        if( size > 0 ){
            periodosUI = new ArrayList<>( size );

            for( Periodo p : periodos ){

                String url = UrlGenerator.detalhesMapaForcaPeriodo( p.getId() );
                String dataInicio =  p.getDataInicio().toString();
                String dataFim = p.getDataFim().toString();

                PeriodoUI ui = new PeriodoUI(
                        url
                        ,dataInicio
                        ,dataFim
                );

                periodosUI.add( ui );
            }
        }
        model.addAttribute( "periodosUI", periodosUI );
        return VIEW_NAME_INDEX;
    }

    @RequestMapping(
            method = RequestMethod.GET
            ,value = "/{id}"
    )
    public String details( Model model, @PathVariable( "id" ) Long id ){


        Collection< Presenca > presencas  = _logic.getPresencasByPeriodo( id );


        MapaForcaViewModel viewModel = new MapaForcaViewModel(
                "3ª Companhia"
                ,presencas
        );

        model.addAttribute( "viewModel", viewModel );

        return VIEW_NAME_DETAILS;
    }


}
