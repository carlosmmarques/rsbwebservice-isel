package pt.isel.ps.li61n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.isel.ps.li61n.model.repository.UnidadeEstrutural_IRepository;

/**
 * RootController - Description
 * Created on 04/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping(value = "/")
public class RootController {

    private UnidadeEstrutural_IRepository unidadeEstruturalRepository;

//    @Autowired
//    public RootController(UnidadeEstrutural_IRepository unidadeEstruturalRepository) {
//        this.unidadeEstruturalRepository = unidadeEstruturalRepository;
//    }

    @RequestMapping( method = RequestMethod.GET )
    public String home(){
        /**
         * Devolve o nome da vista com que o serviço responde
         */
        return "home";
    }
}
