package pt.isel.ps.li61n.model;

import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Demo on 24/05/2016.
 */

@Controller
public class PessoalLogic implements IPessoalLogic< PessoalUI, String > {

    private HashMap< String, PessoalUI > _pessoal;

    public PessoalLogic(){
        _pessoal = new HashMap<>();
        for ( int i = 0; i < 10 ; i++ ) {
            String aux = "" + i;
            PessoalUI p = new PessoalUI( aux, "name " + aux, "/pessoal/" + aux );
            _pessoal.put( aux, p );
        }

    }
    // Obter todos
    @Override
    public Collection< PessoalUI > getAll() {
        return _pessoal.values();
    }

    @Override
    public PessoalUI getOne( String id ) {
        return _pessoal.get( id );
    }
}
