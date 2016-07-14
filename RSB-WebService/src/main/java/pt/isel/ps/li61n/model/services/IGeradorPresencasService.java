package pt.isel.ps.li61n.model.services;

import org.springframework.stereotype.Service;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
import pt.isel.ps.li61n.model.entities.Periodo;
import pt.isel.ps.li61n.model.entities.Presenca;

import java.util.Collection;

/**
 * IGeradorPresençasService - Description
 * Created on 13/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public interface IGeradorPresencasService {

    /**
     * @param periodo O periodo em relação ao qual pretendemos popular as presenças
     * @throws Exception
     */
    void popularPresencas(Periodo periodo) throws Exception;

    /**
     * @param periodo O periodo em relação ao qual pretendemos popular as presenças do elemento
     * @param elementoDoPessoal O elemento em relação pretendemos popular as presenças no periodo
     * @throws Exception
     */
    Collection<Presenca> popularPresenças(Periodo periodo, ElementoDoPessoal elementoDoPessoal) throws Exception;

}
