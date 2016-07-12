package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.isel.ps.li61n.model.dal.ICategoriasRepository;
import pt.isel.ps.li61n.model.dal.IPessoalRepository;

import pt.isel.ps.li61n.model.dal.IPostosFuncionaisRepository;
import pt.isel.ps.li61n.model.dal.exceptions.ElementoNotFoundException;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Categoria;
import pt.isel.ps.li61n.model.entities.Elemento;
import pt.isel.ps.li61n.model.entities.PostoFuncional;

import java.util.Collection;

@Component
public class PessoalLogic implements IPessoalLogic {

    private IPessoalRepository _pessoal;
    private ICategoriasRepository _categorias;
    private IPostosFuncionaisRepository _postos;

    @Autowired
    public PessoalLogic(
            IPessoalRepository pessoal
            ,ICategoriasRepository categorias
            ,IPostosFuncionaisRepository postos
    ) {
        this._pessoal = pessoal;
        System.out.println(
                this.getClass().getSimpleName() +
                " is using " + pessoal.getClass().getSimpleName()
        );

        this._categorias = categorias;
        System.out.println(
                this.getClass().getSimpleName() +
                " is using " + categorias.getClass().getSimpleName()
        );

        this._postos = postos;
        System.out.println(
                this.getClass().getSimpleName() +
                        " is using " + postos.getClass().getSimpleName()
        );
    }

    @Override
    public Collection<Elemento> getAll() {
        try {
            return _pessoal.selectAll();
        }
        catch( RepositoryException e ) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public Elemento getOne(Long id ) {

        Elemento elemento = null;
        try {
            elemento = _pessoal.selectOne( id );
        }
        catch( RepositoryException e ) {
            throw new RuntimeException( e );
        }

        /*
        Elemento pessoal = new Elemento(
                elemento.  int numMecanografico
        ,String idInterno
        ,String postoFuncionalId
        ,long id
        );
        */
        //return _pessoal.selectOne( id );
        return elemento;
    }

    @Override
    public Long create( Elemento element ) throws PropertyEntityException {

        // numMecanografico unique??
        boolean notUnique = true;
        try{
            _pessoal.getOneByNumMecanografico( element.getNumMecanografico() );
        }
        catch( ElementoNotFoundException e ) {
            notUnique = false;
        }
        if( notUnique ){
            throw new PropertyEntityException( "numMecanografico", "O número mecanográfico inserido já existe!" );
        }


        try {
            return _pessoal.insert( element );
        }
        catch( RepositoryException e ) {
            throw new RuntimeException( e );
        }

    }

    @Override
    public Collection< Categoria > getAllCategorias() {

        try {
            return _categorias.selectAll();
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public Collection<PostoFuncional> getAllPostosFuncionais() {

        try {
            return _postos.selectAll();
        }
        catch (RepositoryException e) {
            throw new RuntimeException( e );
        }
    }
}