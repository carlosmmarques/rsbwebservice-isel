package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.*;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.entities.*;
import pt.isel.ps.li61n.viewModel.InsertElementoUI;
import pt.isel.ps.li61n.viewModel.PessoalUI;
import pt.isel.ps.li61n.util.web.UrlGenerator;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.LinkedList;

import static pt.isel.ps.li61n.RsbWebClientApplication.DATE_FORMATTER;
import static pt.isel.ps.li61n.RsbWebClientApplication.PESSOAL_URL;

/**
 * Created on 20/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */

@Controller
@RequestMapping( PESSOAL_URL )
public class PessoalController {

    /**
     * Definição globais para facilitar a utilização nos testes.
     */
    public static final String
            VIEW_NAME_ALL = PESSOAL_URL + "/all"
            ,VIEW_NAME_DETAILS = PESSOAL_URL + "/details"
            ,VIEW_NAME_INSERT = PESSOAL_URL + "/insert"
            ,PAGE_PATH_INSERT = "/registar"
    ;

    private IPessoalLogic _elementosLogic;
    private IUnidadesEstruturaisLogic _uesLogic;
    private IPresencasLogic _presencasLogic;
    private ITurnosLogic _turnosLogic;

    @Autowired
    public PessoalController(
            IPessoalLogic pessoalLogic
            ,IUnidadesEstruturaisLogic unidadesEstruturaisLogic
            ,IPresencasLogic presencasLogic
            ,ITurnosLogic turnosLogic
    ){
        this._elementosLogic = pessoalLogic;
        this._uesLogic = unidadesEstruturaisLogic;
        this._presencasLogic = presencasLogic;
        this._turnosLogic = turnosLogic;
    }

    @RequestMapping( method = RequestMethod.GET )
    public String index( Model model ){
        Collection<Elemento> todoPessoal = _elementosLogic.getAll();

        Collection< PessoalUI > pessoal = new LinkedList<>();

        for( Elemento elemento : todoPessoal ){
            PessoalUI pessoa = new PessoalUI(
                    elemento.getIdInterno()
                    ,elemento.getNome()
                    ,elemento.getCategoria().getAbreviatura()
                    , UrlGenerator.detalhesPessoal( elemento.getId() )
            );
            pessoal.add( pessoa );
        }

        model.addAttribute( "pessoal", pessoal );
        return VIEW_NAME_ALL;
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public String details( @PathVariable( "id" ) Long id, Model model ) throws IOException {

        //obter o elemento com 'id'
        Elemento elemento = _elementosLogic.getOne( id );

        if( elemento == null ){
            return "redirect:/error";
        }

        PessoalUI pessoa = new PessoalUI(
            elemento.getIdInterno()
            ,elemento.getNome()
            ,elemento.getCategoria().getDescricao()
            , UrlGenerator.detalhesPessoal( elemento.getId() )
            , elemento.getNumMecanografico()
        );

        model.addAttribute( "pessoa", pessoa );

        return VIEW_NAME_DETAILS;
    }

    /**
     * Apresentar a página com o formulário de inserção de um elemento.
     */
    @RequestMapping( value = PAGE_PATH_INSERT, method = RequestMethod.GET )
    public String insertView( Model model ) {

        // Acção do formulário
        model.addAttribute( "action", PESSOAL_URL );
        model.addAttribute( new InsertElementoUI() );

        populateModelForInsertView( model );

        return VIEW_NAME_INSERT;
    }

    private void populateModelForInsertView( Model model ){
        Collection< Categoria > categorias = _elementosLogic.getAllCategorias();
        model.addAttribute( "categorias", categorias );

        Collection< PostoFuncional > postos = _elementosLogic.getAllPostosFuncionais();
        model.addAttribute( "postos", postos );

        Collection< Instalacao > instalacoes = _uesLogic.getAllInstalacoes();
        model.addAttribute( "instalacoes", instalacoes );

        Collection< TipoPresenca > tiposPresenca = _presencasLogic.getTiposPresencaBy( false, false );
        model.addAttribute( "tiposPresenca", tiposPresenca );

        Collection< Turno > turnos = _turnosLogic.getAll();
        model.addAttribute( "turnos", turnos );
    }

    @RequestMapping( method = RequestMethod.POST )
    public String insert(
            @Valid InsertElementoUI elemento
            ,Errors errors
            ,Model model
            ,HttpServletResponse response
    ) {

        if( errors.hasErrors() ){

            int errorCount = errors.getErrorCount();

            // validar 'dataNascimento'
            String dataNascimentoField = "dataNascimento";
            if( errors.hasFieldErrors( dataNascimentoField ) && validateDate( dataNascimentoField, errors ) ){
                errorCount--;
            }

            if( errorCount> 0  ){
                response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
                populateModelForInsertView( model );
                return VIEW_NAME_INSERT;
            }
        }

        Elemento novoElemento = new Elemento();

        novoElemento.setNome( elemento.getNome() );
        novoElemento.setDataNascimento( LocalDate.parse( elemento.getDataNascimento(), DATE_FORMATTER ) );
        novoElemento.setNumMecanografico( elemento.getNumMecanografico() );
        novoElemento.setMatricula( elemento.getMatricula() );
        novoElemento.setDataIngresso( LocalDate.parse( elemento.getDataNascimento(), DATE_FORMATTER ) );

        novoElemento.setCategoriaId( elemento.getCategoriaId() );
        novoElemento.setPostoFuncionalId( elemento.getPostoFuncionalId() );
        novoElemento.setInstalacaoId( elemento.getInstalacaoId() );
        novoElemento.setTipoPresencaId( elemento.getTipoPresencaId() );
        novoElemento.setTurnoId( elemento.getTurnoId() );

        Long id = null;
        try {
            // Criar o registo do elemento
            id = _elementosLogic.create( novoElemento );
        }
        catch( PropertyEntityException e ) {

            errors.rejectValue( e.fieldName, "", e.getMessage() );

            response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
            return VIEW_NAME_INSERT; //TODO: Redefinir status para 4XX?
        }

        return UrlGenerator.redirectDetalhesPessoal( id );
    }

    //TODO: arranjar outra solução...
    private boolean validateDate( String fieldName, Errors errors ){
        boolean result = true;
        if( errors.hasFieldErrors( fieldName ) ){
            FieldError fieldError =  errors.getFieldError( fieldName );
            String value = (String) fieldError.getRejectedValue();
            try{
                LocalDate.parse( value );
            }
            catch( DateTimeParseException | NullPointerException e ){
                result = false;
            }
        }
        return result;
    }
}
