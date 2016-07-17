package pt.isel.ps.li61n.model.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
import pt.isel.ps.li61n.model.entities.Periodo;
import pt.isel.ps.li61n.model.entities.Presenca;

import java.sql.Date;
import java.util.Collection;

/**
 * IGeradorPresençasService - Description
 * Created on 13/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public interface IEscalaService {

    /**
     * @param periodo O periodo em relação ao qual pretendemos popular as presenças
     * @throws Exception
     */
    @Transactional(propagation = Propagation.MANDATORY)
    void popularPresencas(Periodo periodo) throws Exception;

    /**
     * @param periodo O periodo em relação ao qual pretendemos popular as presenças do elemento
     * @param elementoDoPessoal O elemento em relação pretendemos popular as presenças no periodo
     * @throws Exception
     */
    @Transactional(propagation = Propagation.MANDATORY)
    Collection<Presenca> popularPresencas(Periodo periodo, ElementoDoPessoal elementoDoPessoal) throws Exception;

    /**
     * Obter elementos para cedencia de troca, ordenados por ordem de favorabilidade de solução
     * @param presenca_id Identificador da Presença, em relação à qual queremos obter elementos para troca
     * @return Lista de elementos disponíveis para troca
     * @throws Exception
     */
    @Transactional(propagation = Propagation.MANDATORY)
    Collection<ElementoDoPessoal> obterElementosDoPessoalParaReforco(Long presenca_id) throws Exception;

    /**
     * Realizar reforço de uma presenca de um elemento
     * @param data Data de relização da troca
     * @param elementoAusente_id Identificador do elemento ausente
     * @param elementoReforco_id Identificador do elemento de reforço
     * @return a Presença com o elemento de reforço
     * @throws Exception
     */
    @Transactional(propagation = Propagation.MANDATORY)
    Presenca realizarReforco(Date data, Long elementoAusente_id, Long elementoReforco_id) throws Exception;
}
