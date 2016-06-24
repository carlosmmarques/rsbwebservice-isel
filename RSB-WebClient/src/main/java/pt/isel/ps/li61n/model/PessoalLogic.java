package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.isel.ps.li61n.model.dal.IPessoalRepository;
import pt.isel.ps.li61n.model.entities.Pessoal;

import java.util.Collection;

@Component
public class PessoalLogic implements IPessoalLogic {

    private IPessoalRepository _pessoal;

    @Autowired
    public PessoalLogic( IPessoalRepository _pessoal ) {
        this._pessoal = _pessoal;
    }

    @Override
    public Collection< Pessoal > getAll() {
        return _pessoal.selectAll();
    }

    @Override
    public Pessoal getOne( Integer numMecanografico ) {
        return _pessoal.selectOne( numMecanografico );
    }

    @Override
    public Integer create( Pessoal element ) {
        return _pessoal.insert( element );
    }
}