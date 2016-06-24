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

import java.util.Collection;

/**
 * Created on 20/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */

@Controller
@RequestMapping("/pessoal")
public class PessoalController {

    private IPessoalLogic _elementosLogic;

    @Autowired
    public PessoalController(IPessoalLogic logic ){
        this._elementosLogic = logic;
    }

    @RequestMapping( method = RequestMethod.GET )
    public String index( Model model ){
        Collection< Pessoal > pessoal = _elementosLogic.getAll();
        model.addAttribute( "pessoal", pessoal );
        return "/pessoal/all";
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public String details( @PathVariable( "id" ) Integer id, Model model ){

        //obter o elemento com 'id'
       Pessoal pessoa = _elementosLogic.getOne( id );

        if(  pessoa == null ){
            return "redirect:/PageError";
        }

        model.addAttribute( "pessoa", pessoa );
        return "/pessoal/details";
    }
}
