package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.IUnidadesEstruturaisLogic;
import pt.isel.ps.li61n.model.IUnidadesOperacionaisLogic;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;
import pt.isel.ps.li61n.viewModel.CreateUnidadeOperacionalViewModel;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

import static pt.isel.ps.li61n.RsbWebClientApplication.UNIDADES_OPERACIONAIS_URL;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping( UNIDADES_OPERACIONAIS_URL )
public class UnidadeOperacionalController {

    /**
     * Definição globais para facilitar a utilização nos testes.
     */
    public static final String
        VIEW_BASE = "unidadesOperacionais"
        ,VIEW_NAME_INDEX = VIEW_BASE + "/index"
        //,VIEW_NAME_ESCALA = VIEW_BASE + "/escala"
        ,MODEL_UO_LIST = "uos"
        ,MODEL_UO_ELEMENT = "uo"
        ,VIEW_NAME_UO_INSERT =  VIEW_BASE + "/insert"
        ,VIEW_NAME_UO_DETAILS = VIEW_BASE + "/details"
    ;

    private IUnidadesOperacionaisLogic _logic;
    private IUnidadesEstruturaisLogic _instalacoesLogic;

    @Autowired
    public UnidadeOperacionalController(
            IUnidadesOperacionaisLogic unidadesOperacionaisLogic
            ,IUnidadesEstruturaisLogic unidadesEstruturaisLogic
    ){
        this._logic = unidadesOperacionaisLogic;
        this._instalacoesLogic = unidadesEstruturaisLogic;
    }

    @RequestMapping( method = RequestMethod.GET )
    public String index( Model model ){

        Collection< UnidadeOperacional> uos = _logic.getAll();

        model.addAttribute( MODEL_UO_LIST, uos );

        return VIEW_NAME_INDEX;
    }

    @RequestMapping( value = "/criar" , method = RequestMethod.GET )
    public String insertView( Model model ){

        // criar viewModel para conter:
        // - Tipos de unidade Operacional
        //      > obter os tipos

        // - instalacoes
        CreateUnidadeOperacionalViewModel viewModel = new CreateUnidadeOperacionalViewModel();
        populateViewModel( viewModel );

        model.addAttribute( viewModel );

        return VIEW_NAME_UO_INSERT;
    }

    private void populateViewModel( CreateUnidadeOperacionalViewModel viewModel ) {
        viewModel.tiposUnidadesOperacionais.addAll( _logic.getAllTipos() );
        viewModel.instalacoes.addAll( _instalacoesLogic.getAllInstalacoes() );
    }

    @RequestMapping( method = RequestMethod.POST )
    public String insert(
            @Valid CreateUnidadeOperacionalViewModel form
            ,Errors errors
            ,HttpServletResponse response
            ,Model model
    )  {

        if( errors.hasErrors() ) {
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
            populateViewModel( form );
            return VIEW_NAME_UO_INSERT;
        }

        UnidadeOperacional uo = new UnidadeOperacional();
        uo.setDesignacao( form.getDesignacao() );
        uo.setTipoUnidadeOperacionalId( form.getTipoId() );
        uo.setOperacional( true );
        uo.setInstalacaoId( form.getInstalacaoId() );

        Long id = null;
        try {
            id = _logic.create( uo );
        }
        catch( PropertyEntityException e ){
            errors.rejectValue( e.fieldName, "", e.getMessage() );
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
            populateViewModel( form );
            return VIEW_NAME_UO_INSERT;
        }

        return "redirect:/unidadesOperacionais/" + id.toString();
    }

    @RequestMapping(
            value = "/{id}"
            ,method = RequestMethod.GET
    )
    public  String details( @PathVariable( "id" ) Long id, Model model ) {

        //obter o elemento com 'id'
        UnidadeOperacional uo = _logic.getOne( id );

        model.addAttribute( MODEL_UO_ELEMENT, uo );
        return VIEW_NAME_UO_DETAILS;
    }
}
