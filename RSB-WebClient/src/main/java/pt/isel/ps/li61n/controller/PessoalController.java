package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.IPessoalLogic;
import pt.isel.ps.li61n.model.entities.Pessoal;
import pt.isel.ps.li61n.viewModel.PessoalUI;
import pt.isel.ps.li61n.util.web.UrlGenerator;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import static pt.isel.ps.li61n.RsbWebClientApplication.PESSOAL;

/**
 * Created on 20/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */

@Controller
@RequestMapping( PESSOAL )
public class PessoalController {

    private IPessoalLogic _elementosLogic;

    @Autowired
    public PessoalController( IPessoalLogic logic ){
        this._elementosLogic = logic;
    }

    @RequestMapping( method = RequestMethod.GET )
    public String index( Model model ){
        Collection< Pessoal > todoPessoal = _elementosLogic.getAll();

        Collection< PessoalUI > pessoal = new LinkedList<>();

        for( Pessoal elemento : todoPessoal ){
            PessoalUI pessoa = new PessoalUI(
                    elemento.getIdInterno()
                    ,elemento.getNome()
                    ,elemento.getCategoria().getAbreviatura()
                    , UrlGenerator.detalhesPessoal( elemento.getId() )
            );
            pessoal.add( pessoa );
        }

        model.addAttribute( "pessoal", pessoal );
        return "/pessoal/all";
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public String details( @PathVariable( "id" ) Long id, Model model ) throws IOException {

        //obter o elemento com 'id'
        Pessoal elemento = _elementosLogic.getOne( id );

        if( elemento == null ){
            return "redirect:/error";
        }

        PessoalUI pessoa = new PessoalUI(
            elemento.getIdInterno()
            ,elemento.getNome()
            ,elemento.getCategoria().getDescrição()
            , UrlGenerator.detalhesPessoal( elemento.getId() )
            , elemento.getNumMecanografico()
        );

        model.addAttribute( "pessoa", pessoa );

        return "/pessoal/details";
    }
}
