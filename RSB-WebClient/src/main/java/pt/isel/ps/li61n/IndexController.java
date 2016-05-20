package pt.isel.ps.li61n;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 20/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping( method = RequestMethod.GET )
    public String index(){
        return "index";
    }
}
