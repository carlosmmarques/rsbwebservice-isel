package pt.isel.ps.li61n.model;

import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.HashMap;


@Controller
public class PessoalLogic implements IPessoalLogic<  String, PessoalUI > {

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

    @Override
    public String create(PessoalUI element) {
        return null;
    }
}
