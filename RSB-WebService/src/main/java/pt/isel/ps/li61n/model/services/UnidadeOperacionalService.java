package pt.isel.ps.li61n.model.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.error.NaoEncontradoException;
import pt.isel.ps.li61n.controller.error.RecursoEliminadoException;
import pt.isel.ps.li61n.model.entities.Guarnicao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;
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
public class UnidadeOperacionalService implements IUnidadeOperacionalService {


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
    @Autowired
    private IUnidadeOperacionalRepositorio unidadeOperacionalRepo;
    @Autowired
    private ITipoUnidadeOperacionalRepositorio tipoUnidadeOperacionalRepo;
    @Autowired
    private IGuarnicaoRepositorio guarnicaoRepo;
    @Autowired
    private  IResponsabilidadeOperacionalRepositorio responsabilidadeOperacionalRepo;

    /**
     * @return Colecção de Unidades Operacionais
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<UnidadeOperacional> obterUnidadesOperacionais() throws Exception {
        return unidadeOperacionalRepo.findAll().stream()
                .filter(UO -> UO.getEliminado() != null && !UO.getEliminado())
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador da Unidade Operacional
     * @return Unidade Operacional
     */
    @Override
    @Transactional(readOnly = true)
    public UnidadeOperacional obterUnidadeOperacional(Long id) throws Exception {
        UnidadeOperacional unidadeOperacional = unidadeOperacionalRepo.findOne(id);
        if (unidadeOperacional == null)
            throw new NaoEncontradoException(String.format("Não é possível obter a unidade operacional com id: %s", id));
        if (unidadeOperacional.getEliminado())
            throw new RecursoEliminadoException(String.format("A unidade operacional foi eliminado: %s", id));
        return unidadeOperacional;
    }

    /**
     * @param designacao             Designação do Unidade Operacional
     * @param tipounidadeoperacional_id Tipo da Unidade Operacional
     * @param operacional            Indica se a Unidade Operacional está em condições de operacionalidade
     * @param instalacao_id             Instalação a que a unidade está atribuida
     * @return Unidade Operacional inserida
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public UnidadeOperacional inserirUnidadeOperacional(
            String designacao,
            Long tipounidadeoperacional_id,
            Boolean operacional,
            Long instalacao_id
    ) throws Exception {
        UnidadeOperacional unidadeOperacional = new UnidadeOperacional();
        unidadeOperacional.setDesignacao(designacao);
        unidadeOperacional.setTipoUnidadeOperacional(tipoUnidadeOperacionalRepo.findOne(tipounidadeoperacional_id));
        unidadeOperacional.setOperacional(operacional);
        unidadeOperacional.setInstalacao(instalacaoRepo.findOne(instalacao_id));
        return unidadeOperacionalRepo.save(unidadeOperacional);
    }

    /**
     * @param id                     Identificador da Unidade Esturtural a actualizar
     * @param designacao             Designação do Unidade Operacional
     * @param tipounidadeoperacional_id Tipo da Unidade Operacional
     * @param operacional            Indica se a Unidade Operacional está em condições de operacionalidade
     * @param instalacao_id             Instalação a que a unidade está atribuida
     * @return Unidade Operacional actualizada
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public UnidadeOperacional actualizarUnidadeOperacional(
            Long id,
            Optional<String> designacao,
            Optional<Long> tipounidadeoperacional_id,
            Optional<Boolean> operacional,
            Optional<Long> instalacao_id
    ) throws Exception {
        UnidadeOperacional unidadeOperacional = obterUnidadeOperacional(id);
        designacao.ifPresent(unidadeOperacional::setDesignacao);
        tipounidadeoperacional_id.ifPresent(aLong -> unidadeOperacional.setTipoUnidadeOperacional(tipoUnidadeOperacionalRepo.findOne(aLong)));
        operacional.ifPresent(unidadeOperacional::setOperacional);
        instalacao_id.ifPresent(aLong -> unidadeOperacional.setInstalacao(instalacaoRepo.findOne(aLong)));
        return unidadeOperacionalRepo.save(unidadeOperacional);
    }

    /**
     * @param id Identificador da Unidade Operacional a eliminar
     * @return Unidade Operacional Eliminada
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public UnidadeOperacional eliminarUnidadeOperacional(Long id) throws Exception {
        UnidadeOperacional unidadeOperacional = obterUnidadeOperacional(id);
        unidadeOperacional.setEliminado(true);
        return unidadeOperacionalRepo.save(unidadeOperacional);
    }

    /**
     * @return Colecção de Tipos de Unidade Operacional
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<TipoUnidadeOperacional> obterTiposUnidadeOperacional() throws Exception {
        return tipoUnidadeOperacionalRepo.findAll().stream()
                .filter(tipoUnidadeEstrutural -> tipoUnidadeEstrutural.getEliminado() != null && !tipoUnidadeEstrutural.getEliminado())
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador do Tipo de Unidade Operacional
     * @return Tipo de Unidade Operacional
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public TipoUnidadeOperacional obterTipoUnidadeOperacional(Long id) throws Exception {
        TipoUnidadeOperacional tipoUnidadeOperacional = tipoUnidadeOperacionalRepo.findOne(id);
        if (tipoUnidadeOperacional == null)
            throw new NaoEncontradoException(String.format("Não é possível obter o tipo de unidade operacional com id: %s", id));
        if (tipoUnidadeOperacional.getEliminado())
            throw new RecursoEliminadoException(String.format("O tipo de unidade operacional foi eliminado: %s", id));
        return tipoUnidadeOperacional;
    }

    /**
     * @param designacao Designação do Tipo de Unidade Operacional
     * @param descricao  Descrição do Tipo de Unidade Operacional
     * @return Tipo de Unidade Operacional inserido
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoUnidadeOperacional inserirTipoUnidadeOperacional(String designacao, String descricao) throws Exception {
        TipoUnidadeOperacional tipoUnidadeOperacional = new TipoUnidadeOperacional();
        tipoUnidadeOperacional.setDesignacao(designacao);
        tipoUnidadeOperacional.setDescricao(descricao);
        return tipoUnidadeOperacionalRepo.save(tipoUnidadeOperacional);
    }

    /**
     * @param id         Identificador do Tipo de Unidade Operacional
     * @param designacao Designação do Tipo de Unidade Operacional
     * @param descricao  Descrição do Tipo de Unidade Operacional
     * @return Tipo de Unidade Operacional actualizado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoUnidadeOperacional actualizarTipoUnidadeOperacional(Long id, Optional<String> designacao, Optional<String> descricao) throws Exception {
        TipoUnidadeOperacional tipoUnidadeOperacional = obterTipoUnidadeOperacional(id);
        designacao.ifPresent(tipoUnidadeOperacional::setDesignacao);
        descricao.ifPresent(tipoUnidadeOperacional::setDescricao);
        return tipoUnidadeOperacionalRepo.save(tipoUnidadeOperacional);
    }

    /**
     * @param id Identificador do Tipo de Unidade Operacional
     * @return Tipo de Unidade Operacional eliminado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoUnidadeOperacional eliminarTipoUnidadeOperacional(Long id) throws Exception {
        TipoUnidadeOperacional tipoUnidadeOperacional = obterTipoUnidadeOperacional(id);
        tipoUnidadeOperacional.setEliminado(true);
        return tipoUnidadeOperacionalRepo.save(tipoUnidadeOperacional);
    }

    /**
     * @param unidadeOperacional_id Identificador da Unidade Operacional a que pertencem as instalações
     * @return Colecção de Guarnições
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Guarnicao> obterGuarnicoesDeUnidadeOperacional(Long unidadeOperacional_id) throws Exception {
        return guarnicaoRepo.findAll().stream()
                .filter(guarnicao -> guarnicao.getUnidadeOperacional().getId().equals(unidadeOperacional_id))
                .filter(guarnicao -> guarnicao.getEliminado() != null && !guarnicao.getEliminado())
                .collect(Collectors.toList());
    }


    /**
     * @param guarnicao_id          Identificador da Guarnicao
     * @return Guarnição
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public Guarnicao obterGuarnicaoDeUnidadeOperacional(Long guarnicao_id) throws Exception {
        Guarnicao guarnicao = guarnicaoRepo.findOne(guarnicao_id);
        if (guarnicao == null)
            throw new NaoEncontradoException(String.format("Não é possível obter o registo de Guarnição com id: %s", guarnicao_id));
        if (guarnicao.getEliminado())
            throw new RecursoEliminadoException(String.format("O registo de Guarnição foi eliminado: %s", guarnicao_id));
        return guarnicao;
    }


    /**
     * @param unidadeOperacional_id Identificador da Unidade Operacional
     * @param guarnicao_id          Identificador da Guarnicao
     * @return Guarnição
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Guarnicao obterGuarnicaoDeUnidadeOperacional(Long unidadeOperacional_id, Long guarnicao_id) throws Exception {
        Guarnicao guarnicao = obterGuarnicaoDeUnidadeOperacional(guarnicao_id);
        if (!guarnicao.getUnidadeOperacional().getId().equals(unidadeOperacional_id))
            throw new NaoEncontradoException(String.format("A guarnicao com id %s relativa á unidade operacional com id %s não foi encontrada.",
                    guarnicao_id,
                    unidadeOperacional_id));
        return guarnicao;
    }

    /**
     * @param unidadeOperacional_id          Identificador da Unidade Operacional
     * @param responsabilidadeOperacional_id Identificador da ResponsabilidadeOperacional
     * @param qtdminima                      Quantidade Mínima de meios
     * @param qtdmaxima                      Quantidade Máxima de meios
     * @return Guarnição inserida
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Guarnicao inserirGuarnicao(
            Long unidadeOperacional_id,
            Long responsabilidadeOperacional_id,
            Integer qtdminima,
            Integer qtdmaxima
    ) throws Exception {
        Guarnicao guarnicao = new Guarnicao();
        guarnicao.setUnidadeOperacional(obterUnidadeOperacional(unidadeOperacional_id));
        guarnicao.setResponsabilidadeOperacional(responsabilidadeOperacionalRepo.findOne(responsabilidadeOperacional_id));
        guarnicao.setMinimo(qtdminima);
        guarnicao.setMaximo(qtdmaxima);
        return guarnicaoRepo.save(guarnicao);
    }

    /**
     * @param guarnicao_id                   Identificador da Guarni;#ao
     * @param unidadeOperacional_id          Identificador da Unidade Operacional
     * @param responsabilidadeOperacional_id Identificador da ResponsabilidadeOperacional
     * @param qtdminima                      Quantidade Mínima de meios
     * @param qtdmaxima                      Quantidade Máxima de meios
     * @return Guarnição inserida
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Guarnicao actualizarGuarnicao(
            Long guarnicao_id,
            Long unidadeOperacional_id,
            Optional<Long> responsabilidadeOperacional_id,
            Optional<Integer> qtdminima,
            Optional<Integer> qtdmaxima
    ) throws Exception {
        Guarnicao guarnicao = obterGuarnicaoDeUnidadeOperacional(unidadeOperacional_id, guarnicao_id);
        responsabilidadeOperacional_id.ifPresent(aLong -> guarnicao.setResponsabilidadeOperacional(responsabilidadeOperacionalRepo.findOne(aLong)));
        qtdminima.ifPresent(guarnicao::setMinimo);
        qtdmaxima.ifPresent(guarnicao::setMaximo);
        return guarnicaoRepo.save(guarnicao);
    }

    /**
     * @param guarnicao_id Identificador da Guarnicao
     * @param unidadeoperacional_id
     * @return Guarnição eliminada
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Guarnicao eliminarGuarnicao(Long guarnicao_id, Long unidadeoperacional_id) throws Exception {
        Guarnicao guarnicao = obterGuarnicaoDeUnidadeOperacional(unidadeoperacional_id, guarnicao_id);
        guarnicao.setEliminado(true);
        return guarnicaoRepo.save(guarnicao);
    }
}
