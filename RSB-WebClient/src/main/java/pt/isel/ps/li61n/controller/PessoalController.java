package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.isel.ps.li61n.model.IPessoalLogic;
import pt.isel.ps.li61n.model.PessoalUI;

import java.util.Collection;
import java.util.List;

/**
 * Created on 20/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */

@Controller
@RequestMapping("/pessoal")
public class PessoalController {

    private IPessoalLogic< String, PessoalUI > _elementosLogic;

    @Autowired
    public PessoalController(IPessoalLogic logic ){
        this._elementosLogic = logic;
    }

    @RequestMapping( method = RequestMethod.GET )
    public String index( Model model ){
        Collection<PessoalUI> pessoal = _elementosLogic.getAll();
        model.addAttribute( "pessoal", pessoal );
        return "/pessoal/all";
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public String details( @PathVariable( "id" ) String id, Model model ){

        //obter o elemento com 'id'
       PessoalUI pessoa = _elementosLogic.getOne( id );

        if(  pessoa == null ){
            return "redirect:/PageError";
        }

        model.addAttribute( "pessoa", pessoa );
        return "/pessoal/details";
    }
}
