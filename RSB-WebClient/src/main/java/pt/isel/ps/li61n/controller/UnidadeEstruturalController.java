package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.ILogic;
import pt.isel.ps.li61n.model.IUnidadeEstruturalLogic;
import pt.isel.ps.li61n.model.UnidadeEstruturalLogic;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

import static pt.isel.ps.li61n.RsbWebClientApplication.UNIDADES_ESTRUTURAIS_URL;

/**
 * Created on 27/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */

@Controller
@RequestMapping( UNIDADES_ESTRUTURAIS_URL )
public class UnidadeEstruturalController {

     // Definição globais para facilitar a utilização nos testes.
    public static final String
        VIEW_NAME_ALL = UNIDADES_ESTRUTURAIS_URL + "/all",
        VIEW_NAME_DETAILS = UNIDADES_ESTRUTURAIS_URL + "/details",
        VIEW_NAME_INSERT = UNIDADES_ESTRUTURAIS_URL + "/insert"

        ,MODEL_UE_LIST = "ues"
        ,MODEL_UE_ELEMENT = "ue"
    ;

    private IUnidadeEstruturalLogic< UnidadeEstrutural, String > _logic;

    @Autowired
    public UnidadeEstruturalController( IUnidadeEstruturalLogic logic ){
        this._logic = logic;
    }

    /**
     *  Tratamento do pedido para '/unidadesEstruturais".
     *
     * @param model
     * @return
     */
    @RequestMapping( method = RequestMethod.GET )
    public String index( Model model ){
        Collection< UnidadeEstrutural > ues = _logic.getAll();
        model.addAttribute( MODEL_UE_LIST, ues );
        return VIEW_NAME_ALL;
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(
            value = "/{id}"
            ,method = RequestMethod.GET
    )
    public  String details( @PathVariable( "id" ) String id, Model model ) {

        //obter o elemento com 'id'
        UnidadeEstrutural ue = _logic.getOne( id );

        /*if(  ue == null ){
            // 204
            return "/error";
            //, HttpStatus.NO_CONTENT ) ;
        }*/
        model.addAttribute( MODEL_UE_ELEMENT, ue );
        return VIEW_NAME_DETAILS;
    }

    /**
     *
     * @return
     */
    @RequestMapping(
            value = "/criar"
            ,method = RequestMethod.GET
    )
    public String insertView(){
        return VIEW_NAME_INSERT;
    }

    /**
     *
     * @param ue
     * @return
     */
    @RequestMapping( method = RequestMethod.POST )
    public String insert( UnidadeEstrutural ue ){
        _logic.create( ue );
        return "redirect:/unidadesEstruturais/" + ue.getId();
    }
}
