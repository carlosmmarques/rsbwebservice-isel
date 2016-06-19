package pt.isel.ps.li61n.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
import pt.isel.ps.li61n.model.entities.RegistoFormacao;

import java.util.List;

/**
 * RegistoFormacao_IRepository - Description
 * Created on 01/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Repository
public interface IRegistoFormacaoRepositorio extends JpaRepository<RegistoFormacao, Long>{

    List<RegistoFormacao> findByElementoDoPessoal (ElementoDoPessoal elementoDoPessoal);

}
