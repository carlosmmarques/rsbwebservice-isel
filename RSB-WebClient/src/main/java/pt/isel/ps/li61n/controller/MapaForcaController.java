package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.IMapaForcaLogic;
import pt.isel.ps.li61n.model.IPessoalLogic;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.viewModel.MapaForcaViewModel;


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
        //VIEW_NAME_UE_DETAILS = MAPA_FORCA_URL + "/details",
        //VIEW_NAME_UE_INSERT = MAPA_FORCA_URL + "/insert"
    ;

    private IMapaForcaLogic _presencasLogic;
    private IPessoalLogic _pessoalLogic;

    @Autowired
    public MapaForcaController(
            IMapaForcaLogic presencasLogic
            ,IPessoalLogic pessoalLogic
    ) {
        this._presencasLogic = presencasLogic;
        this._pessoalLogic = pessoalLogic;
    }

    @RequestMapping( method = RequestMethod.GET )
    public String index( Model model ){

        Collection< Presenca > presencas  = _presencasLogic.getAllPresencas();


        MapaForcaViewModel viewModel = new MapaForcaViewModel(
            "3ª Companhia"
            ,presencas
            ,_pessoalLogic
        );

        model.addAttribute( "viewModel", viewModel );

        return VIEW_NAME_INDEX;
    }
}
