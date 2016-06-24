package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.AtribuicaoCategoria;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;

import java.util.List;

/**
 * AtribuicaoCategoria_IRepository - Description
 * Created on 29/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface IAtribuicaoCategoriaRepositorio extends JpaRepository<AtribuicaoCategoria, Long> {

    List<AtribuicaoCategoria> findByElementoDoPessoal (ElementoDoPessoal elementoDoPessoal);


}
