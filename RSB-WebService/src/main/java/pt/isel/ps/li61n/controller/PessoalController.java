package pt.isel.ps.li61n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pt.isel.ps.li61n.model.entities.Pessoal;
import pt.isel.ps.li61n.model.repository.Pessoal_IRepository;

import java.util.List;

/**
 * PessoalController - Description
 * Created on 13/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
public class PessoalController extends RsbBaseController<Pessoal>{

    @Autowired
    Pessoal_IRepository repo;
    /**
     * @return Lista de pessoal
     */
    @RequestMapping(value = "/pessoal", method = RequestMethod.GET)
    @ResponseBody
    public List<Pessoal> listaPessoal(){
        return repo.findAll();
    }

}
