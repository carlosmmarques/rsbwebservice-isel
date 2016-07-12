package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.IUnidadesEstruturaisLogic;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;
import pt.isel.ps.li61n.viewModel.CreateUnidadeEstruturalViewModel;

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

    /**
     * Definição globais para facilitar a utilização nos testes.
     */
    public static final String
        VIEW_NAME_ALL = UNIDADES_ESTRUTURAIS_URL + "/all",
        VIEW_NAME_DETAILS = UNIDADES_ESTRUTURAIS_URL + "/details",
        VIEW_NAME_INSERT = UNIDADES_ESTRUTURAIS_URL + "/insert"

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
    public  String details( @PathVariable( "id" ) Long id, Model model ) {

        //obter o elemento com 'id'
        UnidadeEstrutural ue = _logic.getOne( id );

        /*
        if( ue != null ){
            // obter o tipo se a ue não tiver
            if( ue.getTipo() == null ){
                ue.setTipo( _logic.getTipo( ue.getTipo_id() ) );
            }

            // obter as subunidaes ou actualizar
            // TODO: Criar género de flag a dizer q precisa de ser actualizado pa não estar smp a fazer o pedido
            ue.setSubunidades( _logic.getSubunidadesEstruturais( ue.getId() ) );
        }
        else{
            return "/error";
        }

        /*if(  ue == null ){
            // 204
            return "/error";
            //, HttpStatus.NO_CONTENT ) ;
        }*/
        model.addAttribute( MODEL_UE_ELEMENT, ue );
        return VIEW_NAME_DETAILS;
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
        return VIEW_NAME_INSERT;
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
    public String insertInstalacaoView(){
        return "/unidadesEstruturais/instalacoes/insert";
    }
}
