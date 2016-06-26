package pt.isel.ps.li61n.model.services;

import org.springframework.stereotype.Service;
import pt.isel.ps.li61n.model.entities.Guarnicao;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;

import java.util.Collection;
import java.util.Optional;

/**
 * IPresencasService - Serviço de Fachada para acesso a dados da secção nuclear de gestão de Turno
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public interface IUnidadeOperacionalService {


    /**
     * @return Colecção de Unidades Operacionais
     * @throws Exception
     */
    Collection<UnidadeOperacional> obterUnidadesOperacionais(
    ) throws Exception;


    /**
     * @param id Identificador da Unidade Operacional
     * @return Unidade Operacional
     */
    UnidadeOperacional obterUnidadeOperacional(
            Long id
    ) throws Exception;


    /**
     * @param designacao Designação do Unidade Operacional
     * @param tipoUnidadeOperacional Tipo da Unidade Operacional
     * @param operacional Indica se a Unidade Operacional está em condições de operacionalidade
     * @param instalacao Instalação a que a unidade está atribuida
     * @return Unidade Operacional inserida
     * @throws Exception
     */
    UnidadeOperacional inserirUnidadeOperacional(
            String designacao,
            TipoUnidadeOperacional tipoUnidadeOperacional,
            Boolean operacional,
            Instalacao instalacao
    ) throws Exception;


    /**
     *
     * @param id Identificador da Unidade Esturtural a actualizar
     * @param designacao Designação do Unidade Operacional
     * @param tipoUnidadeOperacional Tipo da Unidade Operacional
     * @param operacional Indica se a Unidade Operacional está em condições de operacionalidade
     * @param instalacao Instalação a que a unidade está atribuida
     * @throws Exception
     * @return Unidade Operacional actualizada
     */
    UnidadeOperacional actualizarUnidadeOperacional(
            Long id,
            Optional<String> designacao,
            Optional<TipoUnidadeOperacional> tipoUnidadeOperacional,
            Optional<Boolean> operacional,
            Optional<Instalacao> instalacao
    ) throws Exception;


    /**
     *
     * @param id Identificador da Unidade Operacional a eliminar
     * @return Unidade Operacional Eliminada
     * @throws Exception
     */
    UnidadeOperacional eliminarUnidadeOperacional(
            Long id
    ) throws Exception;


    /**
     * @return Colecção de Tipos de Unidade Operacional
     * @throws Exception
     */
    Collection<TipoUnidadeOperacional> obterTiposUnidadeOperacional() throws Exception;


    /**
     * @param id Identificador do Tipo de Unidade Operacional
     * @return Tipo de Unidade Operacional
     * @throws Exception
     */
    TipoUnidadeOperacional obterTipoUnidadeOperacional(Long id) throws Exception;


    /**
     * @param designacao Designação do Tipo de Unidade Operacional
     * @param descricao Descrição do Tipo de Unidade Operacional
     * @return Tipo de Unidade Operacional inserido
     * @throws Exception
     */
    TipoUnidadeOperacional inserirTipoUnidadeOperacional(
            String designacao,
            String descricao
    ) throws Exception;


    /**
     *
     * @param id Identificador do Tipo de Unidade Operacional
     * @param designacao Designação do Tipo de Unidade Operacional
     * @param descricao Descrição do Tipo de Unidade Operacional
     * @return Tipo de Unidade Operacional actualizado
     * @throws Exception
     */
    TipoUnidadeOperacional actualizarTipoUnidadeOperacional(
            Long id,
            Optional<String> designacao,
            Optional<String> descricao
    ) throws Exception;


    /**
     * @param id Identificador do Tipo de Unidade Operacional
     * @return Tipo de Unidade Operacional eliminado
     * @throws Exception
     */
    TipoUnidadeOperacional eliminarTipoUnidadeOperacional(Long id) throws Exception;


    /**
     * @param unidadeOperacional_id Identificador da Unidade Operacional a que pertencem as instalações
     * @return Colecção de Guarnições
     * @throws Exception
     */
    Collection<Instalacao> obterGuarnicoesDeUnidadeOperacional(
            Long unidadeOperacional_id
    ) throws Exception;


    /**
     * @param unidadeOperacional_id Identificador da Unidade Operacional
     * @param guarnicao_id Identificador da Guarnicao
     * @return Guarnição
     * @throws Exception
     */
    Guarnicao obterGuarnicaoDeUnidadeOperacional(
            Long unidadeOperacional_id,
            Long guarnicao_id
    ) throws Exception;


    /**
     * @param unidadeOperacional_id Identificador da Unidade Operacional
     * @param responsabilidadeOperacional_id Identificador da ResponsabilidadeOperacional
     * @param qtdminima Quantidade Mínima de meios
     * @param qtdmaxima Quantidade Máxima de meios
     * @return Guarnição inserida
     * @throws Exception
     */
    Guarnicao inserirGuarnicao(
            Long unidadeOperacional_id,
            Long responsabilidadeOperacional_id,
            Integer qtdminima,
            Integer qtdmaxima
    ) throws Exception;


    /**
     * @param guarnicao_id Identificador da Guarni;#ao
     * @param unidadeOperacional_id Identificador da Unidade Operacional
     * @param responsabilidadeOperacional_id Identificador da ResponsabilidadeOperacional
     * @param qtdminima Quantidade Mínima de meios
     * @param qtdmaxima Quantidade Máxima de meios
     * @return Guarnição inserida
     * @throws Exception
     */
    Guarnicao actualizarGuarnicao(
            Long guarnicao_id,
            Optional<Long> unidadeOperacional_id,
            Optional<Long> responsabilidadeOperacional_id,
            Optional<Integer> qtdminima,
            Optional<Integer> qtdmaxima
    ) throws Exception;


    /**
     * @param guarnicao_id Identificador da Guarnicao
     * @return Guarnição eliminada
     * @throws Exception
     */
    Guarnicao eliminarGuarnicao(
            Long guarnicao_id
    ) throws Exception;
}