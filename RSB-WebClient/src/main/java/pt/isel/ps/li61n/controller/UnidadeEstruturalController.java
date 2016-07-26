package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.IUnidadesEstruturaisLogic;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;
import pt.isel.ps.li61n.viewModel.CreateUnidadeEstruturalViewModel;
import pt.isel.ps.li61n.viewModel.InsertInstalacaoViewModel;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

import static pt.isel.ps.li61n.RsbWebClientApplication.INSTALACOES_BASE_URL;
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

    /**
     * Definição globais para facilitar a utilização nos testes.
     */
    public static final String
        VIEW_BASE = "unidadesEstruturais"
        ,VIEW_NAME_UE_ALL = VIEW_BASE + "/all"
        ,VIEW_NAME_UE_DETAILS = VIEW_BASE + "/details"
        ,VIEW_NAME_UE_INSERT =  VIEW_BASE + "/insert"
        ,VIEW_NAME_INSTALACAO_INSERT = VIEW_BASE + INSTALACOES_BASE_URL + "/insert"
        ,VIEW_NAME_INSTALACAO_DETAILS = VIEW_BASE + INSTALACOES_BASE_URL + "/details"
        ,MODEL_UE_LIST = "ues"
        ,MODEL_UE_ELEMENT = "ue"
    ;

    private IUnidadesEstruturaisLogic _logic;

    @Autowired
    public UnidadeEstruturalController( IUnidadesEstruturaisLogic logic ){
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
        return VIEW_NAME_UE_ALL;
    }

    @RequestMapping(
            value = "/{id}"
            ,method = RequestMethod.GET
    )
    public  String details( @PathVariable( "id" ) Long id, Model model ) {

        //obter o elemento com 'id'
        UnidadeEstrutural ue = _logic.getOne( id );

        Collection< UnidadeEstrutural > subUnidades = _logic.getSubunidadesEstruturais( id );
        ue.setSubunidades( subUnidades );

        Collection< Instalacao > instalacaos = _logic.getAllInstalacoes( id );
        ue.setInstalacoes( instalacaos );

        model.addAttribute( MODEL_UE_ELEMENT, ue );
        return VIEW_NAME_UE_DETAILS;
    }

    /**
     * Método responsável por apresentar a página "Inserir Unidade Estrutural".
     * @return
     */
    @RequestMapping(
            value = "/criar"
            ,method = RequestMethod.GET
    )
    public String insertView( Model model ){

        CreateUnidadeEstruturalViewModel viewModel = new CreateUnidadeEstruturalViewModel(
            _logic.getAllTipos()
            ,_logic.getAll()
        );

        model.addAttribute( "viewModel", viewModel );
        return VIEW_NAME_UE_INSERT;
    }

    /**
     *
     * @param ue
     * @return
     */
    @RequestMapping( method = RequestMethod.POST )
    public String insert( UnidadeEstrutural ue ) throws PropertyEntityException {

        // Representação de "Nenhuma"
        if( ue.getUnidadeEstruturalMae_id() == -1L )
            ue.setUnidadeEstruturalMae_id( null );

        Long id = _logic.create( ue );

        return "redirect:/unidadesEstruturais/" + id.toString();
    }

    @RequestMapping(
            value = "/{id}/instalacoes/criar"
            ,method = RequestMethod.GET
    )
    public String insertInstalacaoView(
            @PathVariable( "id" ) Long id
            ,Model model
    ){
        model.addAttribute( "action", UrlGenerator.operacoesUnidadadeEstrutural( id, "instalacoes" ) );
        model.addAttribute( new InsertInstalacaoViewModel() );

        return VIEW_NAME_INSTALACAO_INSERT;
    }

    @RequestMapping(
            value = "/{id}/instalacoes"
            ,method = RequestMethod.POST
    )
    public String insertInstalacao(
                    @Valid InsertInstalacaoViewModel newInstalacao
                    ,Errors errors
                    ,Model model
                    ,@PathVariable( "id" ) Long id
                    ,HttpServletResponse response
    ){
        if( errors.hasErrors() ){
            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
            return VIEW_NAME_INSTALACAO_INSERT;
        }
        Instalacao instalacao = new Instalacao();
        instalacao.setLocalizacao( newInstalacao.getLocalizacao() );
        instalacao.setDescricao( newInstalacao.getDescricao() );
        instalacao.setDesignacao( newInstalacao.getDesignacao() );
        instalacao.setUnidadeEstruturalId( id );

        Long idInstalacao = _logic.createInstalacao( instalacao );
        return UrlGenerator.redirectDetalhesInstalacao( id, idInstalacao );
    }

    @RequestMapping(
            value = "/{ue_id}/instalacoes/{id}"
            ,method = RequestMethod.GET
    )
    public  String details(
            @PathVariable( "ue_id" ) Long ueId
            ,@PathVariable( "id" ) Long id
            ,Model model
    ) {
        //obter o elemento com 'id'
        Instalacao instalacao = null;
        try {
            instalacao = _logic.getOneInstalacao( ueId, id );
        }
        catch( PropertyEntityException e ) {
            throw new RuntimeException( e );
        }

        model.addAttribute( instalacao );
        return VIEW_NAME_INSTALACAO_DETAILS;
    }

}
