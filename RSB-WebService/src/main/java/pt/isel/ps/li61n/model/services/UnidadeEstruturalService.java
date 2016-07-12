package pt.isel.ps.li61n.model.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.error.NaoEncontradoException;
import pt.isel.ps.li61n.controller.error.RecursoEliminadoException;
import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;
import pt.isel.ps.li61n.model.repository.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TurnoService - Description
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public class UnidadeEstruturalService implements IUnidadeEstruturalService {


    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     * Instâncias dos repositórios
     */
    @Autowired
    private ITurnoRepositorio turnoRepo;
    @Autowired
    private IAlgoritmoCalculoTurnoRepositorio algoritmoCalculoTurnoRepo;
    @Autowired
    private IPeriodoCicloTurnoRepositorio periodoCicloTurnoRepo;
    @Autowired
    private IUnidadeEstruturalRepositorio unidadeEstruturalRepo;
    @Autowired
    private ITipoUnidadeEstruturalRepositorio tipoUnidadeEstruturalRepo;
    @Autowired
    private IInstalacaoRepositorio instalacaoRepo;

    /**
     * @return Colecção de Unidades Estruturais
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<UnidadeEstrutural> obterUnidadesEstruturais() throws Exception {
        return unidadeEstruturalRepo.findAll().stream()
                .filter(ue -> ue.getEliminado() != null && !ue.getEliminado())
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador da Unidade Estrutura
     * @return Unidade Estrutural
     */
    @Override
    @Transactional(readOnly = true)
    public UnidadeEstrutural obterUnidadeEstrutural(Long id) throws Exception {
        UnidadeEstrutural unidadeEstrutural = unidadeEstruturalRepo.findOne(id);
        if (unidadeEstrutural == null)
            throw new NaoEncontradoException(String.format("Não é possível obter a unidade etrutural com id: %s", id));
        if (unidadeEstrutural.getEliminado())
            throw new RecursoEliminadoException(String.format("A unidade etrutural foi eliminado: %s", id));
        return unidadeEstrutural;
    }

    /**
     * @param tipounidadeestrutural_id Identificador de Tipo de Unidade Estrutural
     * @param unidadeestruturalmae_id  Identificador da Unidade Estutural Mãe
     * @param nivelhierarquico         Nível hierarquico
     * @param designacao               Designação da Unidade Estrutural
     * @return Unidade Estrutural Inserida
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public UnidadeEstrutural inserirUnidadeEstrutural(
            String designacao,
            Long tipounidadeestrutural_id,
            Long unidadeestruturalmae_id,
            Integer nivelhierarquico
    ) throws Exception {

        UnidadeEstrutural unidadeEstrutural = new UnidadeEstrutural();
        TipoUnidadeEstrutural tipoUnidadeEstrutural = obterTipoUnidadeEstutural(tipounidadeestrutural_id);
        if( unidadeestruturalmae_id != null ){
            UnidadeEstrutural unidadeEstruturalMae = obterUnidadeEstrutural(unidadeestruturalmae_id);
            unidadeEstrutural.setUnidadeEstruturalMae(unidadeEstruturalMae);
        }

        unidadeEstrutural.setDesignacao(designacao);
        unidadeEstrutural.setTipoUnidadeEstrutural(tipoUnidadeEstrutural);

        unidadeEstrutural.setNivelHierarquico(nivelhierarquico);

        return unidadeEstruturalRepo.save(unidadeEstrutural);
    }

    /**
     * @param id                       Identificador da Unidade Estrutural a actualizar
     * @param designacao               Designação da Unidade Estrutural
     * @param unidadeestruturalmae_id  Identificador da Unidade Estutural Mãe
     * @param tipounidadeestrutural_id Identificador de Tipo de Unidade Estrutural
     * @param nivelhierarquico         Nível hierarquico da Unidade Estrutural
     * @return Unidade Estrutural actuaizado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public UnidadeEstrutural actualizarUnidadeEstrutural(
            Long id,
            Optional<String> designacao,
            Optional<Long> unidadeestruturalmae_id,
            Optional<Long> tipounidadeestrutural_id,
            Optional<Integer> nivelhierarquico
    ) throws Exception {

        UnidadeEstrutural unidadeEstrutural = obterUnidadeEstrutural(id);

        designacao.ifPresent(unidadeEstrutural::setDesignacao);
        tipounidadeestrutural_id.ifPresent(aLong -> {
            try {
                unidadeEstrutural.setTipoUnidadeEstrutural(obterTipoUnidadeEstutural(aLong));
            } catch (Exception e) {
                throw new NaoEncontradoException(String.format("Não é possível obter o Tipo de Unidade Estrutural com id: %s", aLong));
            }
        });
        return unidadeEstruturalRepo.save(unidadeEstrutural);
    }

    /**
     * @param id Identificador da Unidade Estrutural
     * @return Unidade Estrutural eliminada
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public UnidadeEstrutural eliminarUnidadeEstrutural(Long id) throws Exception {
        UnidadeEstrutural unidadeEstrutural = unidadeEstruturalRepo.findOne(id);
        unidadeEstrutural.setEliminado(true);
        return unidadeEstruturalRepo.save(unidadeEstrutural);
    }

    /**
     * @return Colecção de Tipos de Unidades Estruturais
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<TipoUnidadeEstrutural> obterTiposUnidadeEstrutural() throws Exception {
        return tipoUnidadeEstruturalRepo.findAll().stream()
                .filter(tipoUE -> tipoUE.getEliminado() != null && !tipoUE.getEliminado())
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador do Tipo de Unidade Estrutural
     * @return Tipo de Unidade Estrutural
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public TipoUnidadeEstrutural obterTipoUnidadeEstutural(Long id) throws Exception {
        TipoUnidadeEstrutural tipoUnidadeEstrutural = tipoUnidadeEstruturalRepo.findOne(id);
        if (tipoUnidadeEstrutural == null)
            throw new NaoEncontradoException(String.format("Não é possível obter o Tipo de Unidade Estrutural com id: %s", id));
        if (tipoUnidadeEstrutural.getEliminado())
            throw new RecursoEliminadoException(String.format("O Tipo de Unidade Estrutural solicitado foi eliminado: %s", id));
        return tipoUnidadeEstrutural;
    }

    /**
     * @param designacao                Designação do Tipo de Unidade Estrutural
     * @param descricao                 Descrição do Tipo de Unidade Estrutural
     * @param nivelHierarquicoMaximoMae Indica o nível hierarquico máximo para o Tipo de Unidade Estrutural Mãe
     * @return Tipo de Unidade Estrutural inserido
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoUnidadeEstrutural inserirTipoUnidadeEstrutural(
            String designacao,
            String descricao,
            Integer nivelHierarquicoMaximoMae
    ) throws Exception {

        TipoUnidadeEstrutural tipoUnidadeEstrutural = new TipoUnidadeEstrutural();

        tipoUnidadeEstrutural.setDesignacao(designacao);
        tipoUnidadeEstrutural.setDescricao(descricao);
        tipoUnidadeEstrutural.setNivelHierarquicoMaximoMae(nivelHierarquicoMaximoMae);

        return tipoUnidadeEstruturalRepo.save(tipoUnidadeEstrutural);
    }

    /**
     * @param id                        Identificador do Tipo de Unidade Estrutural
     * @param designacao                Designação do Tipo de Unidade Estrutural
     * @param descricao                 Descrição do Tipo de Unidade Estrutural
     * @param nivelHierarquicoMaximoMae Indica o nível hierarquico máximo para o Tipo de Unidade Estrutural Mãe
     * @return Tipo de Unidade Estrutural actualizado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoUnidadeEstrutural actualizarTipoUnidadeEstrutural(
            Long id,
            Optional<String> designacao,
            Optional<String> descricao,
            Optional<Integer> nivelHierarquicoMaximoMae
    ) throws Exception {
        TipoUnidadeEstrutural tipoUnidadeEstrutural = obterTipoUnidadeEstutural(id);
        designacao.ifPresent(tipoUnidadeEstrutural::setDesignacao);
        descricao.ifPresent(tipoUnidadeEstrutural::setDescricao);
        nivelHierarquicoMaximoMae.ifPresent(tipoUnidadeEstrutural::setNivelHierarquicoMaximoMae);
        return tipoUnidadeEstruturalRepo.save(tipoUnidadeEstrutural);
    }

    /**
     * @param id Identificador do Tipo de Unidade Estrutural
     * @return Tipo de Unidade Estrutural eliminado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoUnidadeEstrutural eliminarTipoUnidadeEstrutural(Long id) throws Exception {
        TipoUnidadeEstrutural tipoUnidadeEstrutural = obterTipoUnidadeEstutural(id);
        tipoUnidadeEstrutural.setEliminado(true);
        return tipoUnidadeEstruturalRepo.save(tipoUnidadeEstrutural);
    }

    /**
     * @param unidadeestrutural_id Identificador da Unidade Estrutural a que pertencem as instalações
     * @return Colecção de Instalações
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Instalacao> obterInstalacoes(
            Long unidadeestrutural_id
    ) throws Exception {
        return instalacaoRepo.findAll().stream()
                .filter(instalacao -> instalacao != null && !instalacao.getEliminado())
                .filter(instalacao -> instalacao.getUnidadeEstrutural().getId().equals(unidadeestrutural_id))
                .collect(Collectors.toList());
    }

    /**
     * @param instalacao_id Identificador da Instalação
     * @return Instalação
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Instalacao obterInstalacao(Long instalacao_id) throws Exception {
        Instalacao instalacao = instalacaoRepo.findOne(instalacao_id);
        if (instalacao == null)
            throw new NaoEncontradoException(String.format(
                    "Não é possível obter a Instalação com id %s.",
                    instalacao_id));
        if (instalacao.getEliminado())
            throw new RecursoEliminadoException(String.format(
                    "A Instalação com id %s foi eliminada.",
                    instalacao_id));
        return instalacao;
    }

    /**
     * @param unidadeestrutural_id Identificador da Unidade Estrutural
     * @param instalacao_id        Identificador da Instalação
     * @return Instalação
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Instalacao obterInstalacao(
            Long unidadeestrutural_id,
            Long instalacao_id
    ) throws Exception {
        UnidadeEstrutural unidadeEstrutural = unidadeEstruturalRepo.findOne(unidadeestrutural_id);
        if (unidadeEstrutural == null)
            throw new NaoEncontradoException(String.format(
                    "Não é possível obter a Unidade Estrutural com id %s.",
                    unidadeestrutural_id));
        if (unidadeEstrutural.getEliminado())
            throw new RecursoEliminadoException(String.format(
                    "A Unidade Estrutural com id %s foi eliminada.",
                    unidadeestrutural_id));
        Instalacao instalacao = obterInstalacao(instalacao_id);
        if (instalacao.getUnidadeEstrutural().getId() != unidadeestrutural_id)
            throw new NaoEncontradoException(String.format("A Instalação com id %s não pertence à unidade estrutural %s",
                    instalacao_id, unidadeestrutural_id));
        return instalacao;
    }

    /**
     * @param unidadeestrutural_id Identificador da Unidade Estrutural
     * @param designacao           Designação da Instalação
     * @param descricao            Descrição da Instalação
     * @param localizacao          Descrição da Localização da Instalação
     * @return Instalação inserida
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Instalacao inserirInstalacao(
            Long unidadeestrutural_id,
            String designacao,
            String descricao,
            String localizacao
    ) throws Exception {
        Instalacao instalacao = new Instalacao();
        UnidadeEstrutural unidadeEstrutural = obterUnidadeEstrutural(unidadeestrutural_id);
        instalacao.setUnidadeEstrutural(unidadeEstrutural);
        instalacao.setDesignacao(designacao);
        instalacao.setDescricao(descricao);
        instalacao.setLocalizacao(localizacao);
        return instalacaoRepo.save(instalacao);
    }

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
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Instalacao actualizarInstalacao(
            Long instalacao_id,
            Long unidadeestrutural_id,
            Optional<String> designacao,
            Optional<String> descricao,
            Optional<String> localizacao,
            Optional<Long> unidadeestruturalreafectacao_id
    ) throws Exception {
        Instalacao instalacao = obterInstalacao(unidadeestrutural_id, instalacao_id);
        designacao.ifPresent(instalacao::setDesignacao);
        descricao.ifPresent(instalacao::setDescricao);
        localizacao.ifPresent(instalacao::setLocalizacao);
        unidadeestruturalreafectacao_id.ifPresent(aLong -> {
            UnidadeEstrutural ue = null;
            try {
                ue = obterUnidadeEstrutural(aLong);
            } catch (Exception e) {
                throw new NaoEncontradoException(String.format("Não é possível obter a Unidade Estrutural com id: %s", aLong));
            }
            instalacao.setUnidadeEstrutural(ue);
        });
        return instalacaoRepo.save(instalacao);
    }

    /**
     * @param instalacao_id        Identificador da Instalação
     * @param unidadeestrutural_id Identificador da Unidade Estrutural
     * @return Instalação eliminada
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Instalacao eliminarInstalacao(
            Long instalacao_id,
            Long unidadeestrutural_id
    ) throws Exception {
        Instalacao instalacao = obterInstalacao(unidadeestrutural_id, instalacao_id);
        instalacao.setEliminado(true);
        return instalacaoRepo.save(instalacao);
    }
}
