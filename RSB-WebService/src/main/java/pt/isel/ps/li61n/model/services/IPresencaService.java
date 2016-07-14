package pt.isel.ps.li61n.model.services;

import org.springframework.stereotype.Service;
import pt.isel.ps.li61n.model.entities.Periodo;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.model.entities.TipoPresenca;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Optional;

/**
 * IPresencasService - Serviço de Fachada para acesso a dados da secção nuclear de gestão de Presenças
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public interface IPresencaService {


    /**
     * @param datainicio           Data de Inicio
     * @param datafim              Data de Fim
     * @param periodo_id           Identificador do Periodo
     * @param turno_id             Identificadro do Turno (Opcional)
     * @param instalacao_id        Identificador da Instalação (Opcional)
     * @param postofuncional_id    Identificador do Posto Funcional (Opcional)
     * @param tipopresenca_id      Identificador do Tipo de Presença
     * @param elementodopessoal_id Identificador do Elemento do pessoal a que diz respeito a presença
     * @param elementoreforco_id   Identificador do Elemento que reforça a presença por motivos de ausencia
     * @param elementoreforcado_id Identificador do Elemento que foi reforçado por motivos de ausencia
     * @return Colecção de Presenças
     * @throws Exception
     */
    Collection<Presenca> obterPresencas(
            Optional<Date> datainicio,
            Optional<Date> datafim,
            Optional<Date> periodo_id,
            Optional<Long> turno_id,
            Optional<Long> instalacao_id,
            Optional<Long> postofuncional_id,
            Optional<String> tipopresenca_id,
            Optional<Long> elementodopessoal_id,
            Optional<Long> elementoreforco_id,
            Optional<Long> elementoreforcado_id
    ) throws Exception;


    /**
     * @param id Identificador da Presenca
     * @return Elemento do Pessoal
     */
    Presenca obterPresenca(
            Long id
    ) throws Exception;


    /**
     * @param data                 Data da presença
     * @param numhoras             Numero de horas em presença
     * @param periodo_id           Identificador do Periodo
     * @param turno_id             Identificador do Turno (Opcional)
     * @param instalacao_id        Identificador da Instalação (Opcional)
     * @param postofuncional_id    Identificador do Posto Funcional (Opcional)
     * @param tipopresenca_id      Identificador do Tipo de Presença
     * @param elementodopessoal_id Identificador do Elemento do pessoal a que diz respeito a presença
     * @param elementoreforco_id   (Opcional) Identificador do Elemento que reforça a presença por motivos de ausencia
     * @param elementoreforcado_id (Opcional) Identificador do Elemento que foi reforçado por motivos de ausencia
     * @return Presença inserida
     * @throws Exception
     */
    Presenca inserirPresenca(
            Date data,
            Time horainicio,
            Float numhoras,
            Long periodo_id,
            Long turno_id,
            Long instalacao_id,
            Long postofuncional_id,
            String tipopresenca_id,
            Long elementodopessoal_id,
            Optional<Long> elementoreforco_id,
            Optional<Long> elementoreforcado_id
    ) throws Exception;


    /**
     * @param id                   Indentificador do registo de Presença
     * @param data                 Data da presença
     * @param numhoras             Numero de horas em presença
     * @param periodo_id           Identificador do Periodo
     * @param turno_id             Identificador do Turno (Opcional)
     * @param instalacao_id        Identificador da Instalação (Opcional)
     * @param postofuncional_id    Identificador do Posto Funcional (Opcional)
     * @param tipopresenca_id      Identificador do Tipo de Presença
     * @param elementodopessoal_id Identificador do Elemento do pessoal a que diz respeito a presença
     * @param elementoreforco_id   (Opcional) Identificador do Elemento que reforça a presença por motivos de ausencia
     * @param elementoreforcado_id (Opcional) Identificador do Elemento que foi reforçado por motivos de ausencia
     * @return Presença actualizada
     * @throws Exception
     */
    Presenca actualizarPresenca(
            Long id,
            Optional<Date> data,
            Optional<Float> numhoras,
            Optional<Long> periodo_id,
            Optional<Long> turno_id,
            Optional<Long> instalacao_id,
            Optional<Long> postofuncional_id,
            Optional<String> tipopresenca_id,
            Optional<Long> elementodopessoal_id,
            Optional<Long> elementoreforco_id,
            Optional<Long> elementoreforcado_id
    ) throws Exception;


    /**
     * @param id Identificador da Presença
     * @return Presença eliminada
     * @throws Exception
     */
    Presenca eliminarPresenca(
            Long id
    ) throws Exception;


    /**
     * @return Colecção de Tipos de Presença
     * @throws Exception
     */
    Collection<TipoPresenca> obterTiposPresenca() throws Exception;


    /**
     * @param id Identificador do tipo de Presença
     * @return Tipo de Presença
     * @throws Exception
     */
    TipoPresenca obterTipoPresenca(String id) throws Exception;


    /**
     * @param ausencia    Indica se o Tipo de Presença é uma ausencia
     * @param reforco     Indica se o Tipo de Presença é de Reforço
     * @param abreviatura Abreviatura do Tipo de Pres
     * @param descricao
     * @return Tipo de Presença inserido
     * @throws Exception
     */
    TipoPresenca inserirTipoPresenca(
            Boolean ausencia,
            Boolean reforco,
            String abreviatura,
            String descricao
    ) throws Exception;


    /**
     * @param id          Identificador do Tipo de Presença
     * @param ausencia    Indica se o Tipo de Presença é uma ausencia
     * @param reforco     Indica se o Tipo de Presença é de Reforço
     * @param abreviatura Abreviatura do Tipo de Pres
     * @param descricao
     * @return Tipo de presença actualizado
     * @throws Exception
     */
    TipoPresenca actualizarTipoPresenca(
            String id,
            Optional<Boolean> ausencia,
            Optional<Boolean> reforco,
            Optional<String> abreviatura,
            Optional<String> descricao
    ) throws Exception;


    /**
     * @param id Identificador do Tipo de Presença
     * @return Tipo de Presença eliminado
     * @throws Exception
     */
    TipoPresenca eliminarTipoPresenca(String id) throws Exception;


    /**
     * @param datainicio (Opcional) Data de inicio
     * @param datafim    (Opcional) Data de fim
     * @return Colecção de Periodos
     * @throws Exception
     */
    Collection<Periodo> obterPeriodos(
            Optional<Date> datainicio,
            Optional<Date> datafim
    ) throws Exception;


    /**
     * @param id Identificador do Periodo
     * @return Periodo
     * @throws Exception
     */
    Periodo obterPeriodo(Long id) throws Exception;


    /**
     * @param datainicio Data de inicio
     * @param datafim    Data de fim
     * @return Periodo inserido
     * @throws Exception
     */
    Periodo inserirPeriodo(
            Date datainicio,
            Date datafim
    ) throws Exception;


    /**
     * @param id         Identificador do Periodo
     * @param datainicio Data de inicio
     * @param datafim    Data de fim
     * @return Periodo actualizado
     * @throws Exception
     */
    Periodo actualizarPeriodo(
            Long id,
            Optional<Date> datainicio,
            Optional<Date> datafim
    ) throws Exception;


    /**
     * @param id Identificador do Periodo
     * @return Periodo eliminado
     * @throws Exception
     */
    Periodo eliminarPeriodo(Long id) throws Exception;

    /**
     * @param periodo_id           O periodo em relação ao qual pretendemos popular as presenças do elemento
     * @param elementoDoPessoal_id O elemento em relação pretendemos popular as presenças no periodo
     * @throws Exception
     */
    Collection<Presenca> popularPresenças(Long periodo_id, Long elementoDoPessoal_id) throws Exception;


    }