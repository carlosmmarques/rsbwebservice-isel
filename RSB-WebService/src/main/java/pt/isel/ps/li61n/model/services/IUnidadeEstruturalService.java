package pt.isel.ps.li61n.model.services;

import org.springframework.stereotype.Service;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

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
public interface IUnidadeEstruturalService {


    /**
     * @return Colecção de Unidades Estruturais
     * @throws Exception
     * @param designacao Designação
     * @param tipounidadeestrutural_id Identificador do tipo da Unidade Estrutural
     * @param unidadeestruturalmae_id Identificador do tipo da Unidade Estrutural Mãe
     * @param nivelhierarquico Nível Hierárquico
     */
    Collection<UnidadeEstrutural> obterUnidadesEstruturais(
            Optional<String> designacao,
            Optional<Long> tipounidadeestrutural_id,
            Optional<Long> unidadeestruturalmae_id,
            Optional<Integer> nivelhierarquico
    ) throws Exception;


    /**
     *
     */

    /**
     * @param id Identificador da Unidade Estrutural
     * @return Unidade Estrutural
     */
    UnidadeEstrutural obterUnidadeEstrutural(
            Long id
    ) throws Exception;


    /**
     * @param designacao            Designação do Unidade Estrutural
     * @param tipoUnidadeEstrutural Tipo da Unidade Estutural
     * @param unidadeEstruturalMae  Unidade Estrutural Mãe
     * @param nivelHierarquico      Nível Hierárquico
     * @return Unidade Estrutural inserida
     * @throws Exception
     */
    UnidadeEstrutural inserirUnidadeEstrutural(
            String designacao,
            Long tipoUnidadeEstrutural,
            Long unidadeEstruturalMae,
            Integer nivelHierarquico
    ) throws Exception;


    /**
     * @param id               Identificador da Unidade Esturtural a actualizar
     * @param designacao       Designação do Unidade Estrutural
     * @param unidadeestruturalmae_id
     *@param tipounidadeestrutural_id
     * @param nivelHierarquico Nível Hierárquico  @return Unidade Esturtural actualizada
     * @throws Exception
     */
    UnidadeEstrutural actualizarUnidadeEstrutural(
            Long id,
            Optional<String> designacao,
            Optional<Long> unidadeestruturalmae_id, Optional<Long> tipounidadeestrutural_id,
            Optional<Integer> nivelHierarquico
    ) throws Exception;


    /**
     * @param id Identificador da Unidade Estrutural a eliminar
     * @return Unidade Estrutural Eliminada
     * @throws Exception
     */
    UnidadeEstrutural eliminarUnidadeEstrutural(
            Long id
    ) throws Exception;


    /**
     * @return Colecção de Tipos de Unidade Estrutural
     * @throws Exception
     */
    Collection<TipoUnidadeEstrutural> obterTiposUnidadeEstrutural() throws Exception;


    /**
     * @param id Identificador do Tipo de Unidade Estrutural
     * @return Tipo de Unidade Estrutural
     * @throws Exception
     */
    TipoUnidadeEstrutural obterTipoUnidadeEstutural(Long id) throws Exception;


    /**
     * @param designacao             Designação do Tipo de Unidade Estrutural
     * @param descricao              Descrição do Tipo de Unidade Estrutural
     * @param nivelHierarquicoMaximo Nível Hierárquico Máximo para este Tipo de Unidade Estrutural
     * @return Tipo de Unidade Estrutural inserido
     * @throws Exception
     */
    TipoUnidadeEstrutural inserirTipoUnidadeEstrutural(
            String designacao,
            String descricao,
            Integer nivelHierarquicoMaximo
    ) throws Exception;


    /**
     * @param id                        Identificador do Tipo de Unidade Estrutural
     * @param designacao                Designação do Tipo de Unidade Estrutural
     * @param descricao                 Descrição do Tipo de Unidade Estrutural
     * @param nivelHierarquicoMaximoMae Indica o nível hierarquico máximo para o Tipo de Unidade Estrutural Mãe
     * @return Tipo de Unidade Estrutural actualizado
     * @throws Exception
     */
    TipoUnidadeEstrutural actualizarTipoUnidadeEstrutural(
            Long id,
            Optional<String> designacao,
            Optional<String> descricao,
            Optional<Integer> nivelHierarquicoMaximoMae
    ) throws Exception;


    /**
     * @param id Identificador do Tipo de Unidade Estrutural
     * @return Tipo de Unidade Estrutural eliminado
     * @throws Exception
     */
    TipoUnidadeEstrutural eliminarTipoUnidadeEstrutural(Long id) throws Exception;


    /**
     * @param unidadeestrutural_id Identificador da Unidade Estrutural a que pertencem as instalações
     * @return Colecção de Instalações
     * @throws Exception
     */
    Collection<Instalacao> obterInstalacoes(
            Long unidadeestrutural_id
    ) throws Exception;

    /**
     * @param instalacao_id Identificador da Instalação
     * @return Instalação
     * @throws Exception
     */
    Instalacao obterInstalacao(
            Long instalacao_id
    ) throws Exception;

    /**
     * @param unidadeestrutural_id Identificador da Unidade Estrutural
     * @param instalacao_id        Identificador da Instalação
     * @return Instalação
     * @throws Exception
     */
    Instalacao obterInstalacao(
            Long unidadeestrutural_id,
            Long instalacao_id
    ) throws Exception;


    /**
     * @param unidadeestrutural_id Identificador da Unidade Estrutural
     * @param designacao           Designação da Instalação
     * @param descricao            Descrição da Instalação
     * @param localizacao          Descrição da Localização da Instalação
     * @return Instalação inserida
     * @throws Exception
     */
    Instalacao inserirInstalacao(
            Long unidadeestrutural_id,
            String designacao,
            String descricao,
            String localizacao
    ) throws Exception;


    /**
     * @param instalacao_id                   Identificador da Instalação
     * @param unidadeestrutural_id            Identificador da Unidade Estrutural
     * @param designacao                      Designação da Instalação
     * @param descricao                       Descrição da Instalação
     * @param localizacao                     Descrição da Localização da Instalação
     * @param unidadeestruturalreafectacao_id Identificador da Unidade Estrutural para reafectação desta instalacao
     * @return Instalação actualizada
     * @throws Exception
     */
    Instalacao actualizarInstalacao(
            Long instalacao_id,
            Long unidadeestrutural_id,
            Optional<String> designacao,
            Optional<String> descricao,
            Optional<String> localizacao,
            Optional<Long> unidadeestruturalreafectacao_id
    ) throws Exception;


    /**
     * @param instalacao_id Identificador da Instalação
     * @param unidadeestrutural_id            Identificador da Unidade Estrutural
     * @return Instalação eliminada
     * @throws Exception
     */
    Instalacao eliminarInstalacao(
            Long instalacao_id,
            Long unidadeestrutural_id
    ) throws Exception;
}