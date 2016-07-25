package pt.isel.ps.li61n.model.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.error.exception.*;
import pt.isel.ps.li61n.model.entities.*;
import pt.isel.ps.li61n.model.repository.IPeriodoRepositorio;
import pt.isel.ps.li61n.model.repository.IPresencaRepositorio;
import pt.isel.ps.li61n.model.repository.ITipoPresencaRepositorio;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * PresencasService - Serviço de Fachada para acesso a dados da secção nuclear de gestão de Presenças
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public class PresencaService implements IPresencaService {

    /**
     * Logger
     */
    Logger logger = RsbWebserviceApplication.logger;

    /**
     * Instâncias dos repositórios
     */
    @Autowired
    private IPresencaRepositorio presencaRepo;
    @Autowired
    private IPeriodoRepositorio periodoRepo;
    @Autowired
    private ITipoPresencaRepositorio tipoPresencaRepo;
    @Autowired
    private IEscalaService escalaService;
    @Autowired
    private IPessoalService pessoalService;
    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IUnidadeEstruturalService unidadeEstruturalService;

    /**
     * Método auxiliar para obtenção de elementos do pessoal
     *
     * @param e - Identificador do elemento do Pessoal.
     * @return Elemento do pessoal
     */
    private ElementoDoPessoal getElementoDoPessoal(Long e) {
        ElementoDoPessoal elementoDoPessoal;
        try {
            elementoDoPessoal = pessoalService.obterElementoDoPessoal(e);
        } catch (RecursoEliminadoException | NaoEncontradoException e1) {
            throw e1;
        } catch (Exception e1) {
            throw new ErroNaoDeterminadoException(e1.getMessage());
        }
        return elementoDoPessoal;
    }

    /**
     * Método auxiliar para obtenção de postoFuncional
     *
     * @param e - Identificador do Posto funcional.
     * @return Posto Funcional
     */
    private PostoFuncional getPostoFuncional(Long e) {
        PostoFuncional postoFuncional;
        try {
            postoFuncional = pessoalService.obterPostoFuncional(e);
        } catch (RecursoEliminadoException | NaoEncontradoException e1) {
            throw e1;
        } catch (Exception e1) {
            throw new ErroNaoDeterminadoException(e1.getMessage());
        }
        return postoFuncional;
    }

    /**
     * Método auxiliar para obtenção de Turno
     *
     * @param e - Identificador do Turno.
     * @return turno
     */
    private Turno getTurno(Long e) {
        Turno turno;
        try {
            turno = turnoService.obterTurno(e);
        } catch (RecursoEliminadoException | NaoEncontradoException e1) {
            throw e1;
        } catch (Exception e1) {
            throw new ErroNaoDeterminadoException(e1.getMessage());
        }
        return turno;
    }

    /**
     * Método auxiliar para obtenção de Instalação
     *
     * @param e - Identificador da Instalação.
     * @return Instalação
     */
    private Instalacao getInstalacao(Long e) {
        Instalacao instalacao;
        try {
            instalacao = unidadeEstruturalService.obterInstalacao(e);
        } catch (RecursoEliminadoException | NaoEncontradoException e1) {
            throw e1;
        } catch (Exception e1) {
            throw new ErroNaoDeterminadoException(e1.getMessage());
        }
        return instalacao;
    }

    /**
     * @param datainicio           Data de Inicio
     * @param datafim              Data de Fim
     * @param periodo_id           Identificador do Periodo
     * @param turno_id             Identificador do Turno (Opcional)
     * @param instalacao_id        Identificador da Instalação (Opcional)
     * @param postofuncional_id    Identificador do Posto Funcional (Opcional)
     * @param tipopresenca_id      Identificador do Tipo de Presença
     * @param elementodopessoal_id Identificador do Elemento do pessoal a que diz respeito a presença
     * @param elementoreforco_id   Identificador do Elemento que reforça a presença por motivos de ausencia
     * @param elementoreforcado_id Identificador do Elemento que foi reforçado por motivos de ausencia
     * @return Colecção de Presenças
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Presenca> obterPresencas(
            Optional<Date> datainicio,
            Optional<Date> datafim,
            Optional<Long> periodo_id,
            Optional<Long> turno_id,
            Optional<Long> instalacao_id,
            Optional<Long> postofuncional_id,
            Optional<String> tipopresenca_id,
            Optional<Long> elementodopessoal_id,
            Optional<Long> elementoreforco_id,
            Optional<Long> elementoreforcado_id
    ) throws Exception {
        List<Presenca> presencas = presencaRepo.findAll().stream()
                .filter(presenca -> datainicio.map(date -> presenca.getData().compareTo(date) <= 0).orElse(true))
                .filter(presenca -> datafim.map(date -> presenca.getData().compareTo(date) >= 0).orElse(true))
                .filter(presenca -> periodo_id.map(p -> p.equals(presenca.getPeriodo().getId())).orElse(true))
                .filter(presenca -> turno_id.map(t -> t.equals(presenca.getTurnoEfectivo().getId())).orElse(true))
                .filter(presenca -> instalacao_id.map(i -> i.equals(presenca.getInstalacaoEfectiva().getId())).orElse(true))
                .filter(presenca -> postofuncional_id.map(p -> p.equals(presenca.getPostoFuncionalEfectivo().getId())).orElse(true))
                .filter(presenca -> tipopresenca_id.map(t -> t.equals(presenca.getTipoPresencaEfectiva().getId())).orElse(true))
                .filter(presenca -> elementodopessoal_id.map(e -> e.equals(presenca.getElementoDoPessoal().getId())).orElse(true))
                .filter(presenca -> elementoreforco_id.map(e -> e.equals(presenca.getElementoReforco().getId())).orElse(true))
                .filter(presenca -> elementoreforcado_id.map(e -> e.equals(presenca.getElementoReforcado().getId())).orElse(true))
                .filter(presenca -> presenca.getEliminado() != null && !presenca.getEliminado())
                .collect(Collectors.toList());
        return presencas;
    }

    /**
     * @param id Identificador da Presenca
     * @return Elemento do Pessoal
     */
    @Override
    @Transactional(readOnly = true)
    public Presenca obterPresenca(Long id) throws Exception {
        Presenca presenca = presencaRepo.findOne(id);
        if (presenca == null)
            throw new NaoEncontradoException(String.format("Não é possível obter o registo de presença com o id: %s", id));
        if (presenca.getEliminado())
            throw new RecursoEliminadoException(String.format("O registo de presença solicitado foi eliminado: %s", id));
        return presenca;
    }

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
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Presenca inserirPresenca(
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
    ) throws Exception {
        Presenca presenca;
        try {
            presenca = presencaRepo.findByDataAndElementoDoPessoal(data, pessoalService.obterElementoDoPessoal(elementodopessoal_id)).get();
//            if (presenca.getEliminado())
//                throw new RecursoEliminadoException(String.format("O registo de presença solicitado foi eliminado: %s", presenca.getId()));
//        } catch (NoSuchElementException | RecursoEliminadoException ex) { // O teste a recurso eliminado está ser feito no próprio repositório
        } catch (NoSuchElementException ex) {
            // Presença não existe na BD, vamos criar:
            logger.debug(String.format("Não existe presença ou foi eliminada (Exception: %s). Vamos criar uma presença nova.", ex.getLocalizedMessage()), ex.getCause());
            presenca = new Presenca();
            presenca.setData(data);
            presenca.setHoraInicio(horainicio);
            presenca.setNumHoras(numhoras);
            presenca.setPeriodo(periodoRepo.findOne(periodo_id));
            presenca.setTurnoEfectivo(turnoService.obterTurno(turno_id));
            presenca.setInstalacaoEfectiva(unidadeEstruturalService.obterInstalacao(instalacao_id));
            presenca.setPostoFuncionalEfectivo(pessoalService.obterPostoFuncional(postofuncional_id));
            presenca.setTipoPresencaEfectiva(tipoPresencaRepo.findOne(tipopresenca_id));
            presenca.setElementoDoPessoal(getElementoDoPessoal(elementodopessoal_id));
            final Presenca[] presencaImutavel = {presenca};
            elementoreforco_id.ifPresent(e -> {
                ElementoDoPessoal elementoDoPessoal = getElementoDoPessoal(e);
                presencaImutavel[0].setElementoReforco(elementoDoPessoal);
            });
            elementoreforcado_id.ifPresent(e -> {
                ElementoDoPessoal elementoDoPessoal = getElementoDoPessoal(e);
                presencaImutavel[0].setElementoReforcado(elementoDoPessoal);
            });
            presenca = presencaRepo.save(presenca);
            logger.debug(String.format("Presença inserida com código: %s", presenca.getId()));
            return presenca;
        }
        //presença existe na BD, vamos lançar a respectiva excepção:
        throw new ConflictoException(String.format("Já existe uma presença com id: %s, para o elemento %s na data %s",
                presenca.getId(), elementodopessoal_id, data));
    }

    /**
     * @param id                   Indentificador do registo de Presença
     * @param data                 Data da presença (Opcional)
     * @param numhoras             Numero de horas em presença (Opcional)
     * @param periodo_id           Identificador do Periodo (Opcional)
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
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Presenca actualizarPresenca(
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
    ) throws Exception {
        Presenca presenca = obterPresenca(id);
        data.ifPresent(presenca::setData);
        numhoras.ifPresent(presenca::setNumHoras);
        periodo_id.ifPresent(aLong -> presenca.setPeriodo(periodoRepo.findOne(aLong)));
        turno_id.ifPresent(aLong -> getTurno(aLong));
        instalacao_id.ifPresent(aLong -> presenca.setInstalacaoEfectiva(getInstalacao(aLong)));
        postofuncional_id.ifPresent(aLong -> presenca.setPostoFuncionalEfectivo(getPostoFuncional(aLong)));
        tipopresenca_id.ifPresent(aString -> presenca.setTipoPresencaEfectiva(tipoPresencaRepo.findOne(aString)));
        elementodopessoal_id.ifPresent(aLong -> presenca.setElementoDoPessoal(getElementoDoPessoal(aLong)));
        elementoreforco_id.ifPresent(aLong -> presenca.setElementoReforco(getElementoDoPessoal(aLong)));
        elementoreforcado_id.ifPresent(aLong -> presenca.setElementoReforcado(getElementoDoPessoal(aLong)));
        return presenca;
    }

    /**
     * @param id Identificador da Presença
     * @return Presença eliminada
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Presenca eliminarPresenca(Long id) throws Exception {
        Presenca presenca = obterPresenca(id);
        presenca.setEliminado(true);
        return presencaRepo.save(presenca);
    }

    /**
     * @return Colecção de Tipos de Presença
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<TipoPresenca> obterTiposPresenca() throws Exception {
        return tipoPresencaRepo.findAll().stream()
                .filter(tipoPresenca -> tipoPresenca.getEliminado() != null && !tipoPresenca.getEliminado())
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador do tipo de Presença
     * @return Tipo de Presença
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public TipoPresenca obterTipoPresenca(String id) throws Exception {
        TipoPresenca tipoPresenca = tipoPresencaRepo.findOne(id);
        if (tipoPresenca == null)
            throw new NaoEncontradoException(String.format("Não é possível obter o registo de tipo de presença com o id: %s", id));
        if (tipoPresenca.getEliminado())
            throw new RecursoEliminadoException(String.format("O registo de tipo de presença solicitado foi eliminado: %s", id));
        return tipoPresenca;
    }

    /**
     * @param ausencia    Indica se o Tipo de Presença é uma ausencia
     * @param reforco     Indica se o Tipo de Presença é de Reforço
     * @param abreviatura Abreviatura do Tipo de Pres
     * @param descricao
     * @return Tipo de Presença inserido
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoPresenca inserirTipoPresenca(Boolean ausencia, Boolean reforco, String abreviatura, String descricao) throws Exception {

        TipoPresenca tipoPresenca = new TipoPresenca();

        tipoPresenca.setAusencia(ausencia);
        tipoPresenca.setReforco(reforco);
        tipoPresenca.setAbreviatura(abreviatura);
        tipoPresenca.setDescricao(descricao);

        return tipoPresencaRepo.save(tipoPresenca);
    }

    /**
     * @param id          Identificador do Tipo de Presença
     * @param ausencia    Indica se o Tipo de Presença é uma ausencia
     * @param reforco     Indica se o Tipo de Presença é de Reforço
     * @param abreviatura Abreviatura do Tipo de Pres
     * @param descricao
     * @return Tipo de presença actualizado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoPresenca actualizarTipoPresenca(
            String id,
            Optional<Boolean> ausencia,
            Optional<Boolean> reforco,
            Optional<String> abreviatura,
            Optional<String> descricao
    ) throws Exception {
        TipoPresenca tipoPresenca = obterTipoPresenca(id);
        ausencia.ifPresent(tipoPresenca::setAusencia);
        reforco.ifPresent(tipoPresenca::setReforco);
        abreviatura.ifPresent(tipoPresenca::setAbreviatura);
        descricao.ifPresent(tipoPresenca::setDescricao);
        return tipoPresencaRepo.save(tipoPresenca);
    }

    /**
     * @param id Identificador do Tipo de Presença
     * @return Tipo de Presença eliminado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public TipoPresenca eliminarTipoPresenca(String id) throws Exception {
        TipoPresenca tipoPresenca = obterTipoPresenca(id);
        tipoPresenca.setEliminado(true);
        return tipoPresencaRepo.save(tipoPresenca);
    }

    /**
     * @param datainicio (Opcional) Data de inicio
     * @param datafim    (Opcional) Data de fim
     * @return Colecção de Periodos
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Periodo> obterPeriodos(Optional<Date> datainicio, Optional<Date> datafim) throws Exception {
        return periodoRepo.findAll().stream()
                .filter(periodo -> periodo.getEliminado() != null && !periodo.getEliminado())
                .filter(periodo -> datainicio.map(date -> date.compareTo(periodo.getDtInicio()) == 0).orElse(true))
                .filter(periodo -> datafim.map(date -> date.compareTo(periodo.getDtFim()) == 0).orElse(true))
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador do Periodo
     * @return Periodo
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Periodo obterPeriodo(Long id) throws Exception {
        Periodo periodo = periodoRepo.findOne(id);
        if (periodo == null)
            throw new NaoEncontradoException(String.format("Não é possível obter o registo de periodo com o id: %s", id));
        if (periodo.getEliminado())
            throw new RecursoEliminadoException(String.format("O registo de periodo solicitado foi eliminado: %s", id));
        return periodo;
    }

    /**
     * @param datainicio Data de inicio
     * @param datafim    Data de fim
     * @return Periodo inserido
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Periodo inserirPeriodo(Date datainicio, Date datafim) throws Exception {

        if (datainicio.compareTo(datafim) >= 0)
            throw new DataInvalidaException("A data de fim do periodo não pode ser posterior à data de inicio.");
        Periodo periodo;

        try {
            periodo = periodoRepo.findAll().stream().filter(p -> (datainicio.compareTo(p.getDtInicio()) >= 0) &&
                    (datafim.compareTo(p.getDtFim()) <= 0))
                    .findFirst().get();
            if (periodo.getEliminado())
                throw new RecursoEliminadoException(String.format("O registo de periodo solicitado foi eliminado: %s", periodo.getId()));
        } catch (NoSuchElementException | RecursoEliminadoException ex) {
            // Presença não existe na BD, vamos criar e popular as respectivas presenças:
            logger.debug("Não existe periodo ou foi eliminado. Vamos criar um periodo novo.", ex.getCause());
            periodo = new Periodo();
            periodo.setDtInicio(datainicio);
            periodo.setDtFim(datafim);
            periodo = periodoRepo.save(periodo);
            escalaService.popularPresencas(periodo);
            return periodo;
        }
        //presença existe na BD, vamos lançar a respectiva excepção:
        throw new ConflictoException(String.format("Já existe um periodo compreendido entre as datas %s e %s", datainicio, datafim));
    }

    /**
     * @param id         Identificador do Periodo
     * @param datainicio Data de inicio
     * @param datafim    Data de fim
     * @return Periodo actualizado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Periodo actualizarPeriodo(Long id, Optional<Date> datainicio, Optional<Date> datafim) throws Exception {
        Periodo periodo = obterPeriodo(id);
        datainicio.ifPresent(periodo::setDtInicio);
        datafim.ifPresent(periodo::setDtFim);
        return periodoRepo.save(periodo);
    }

    /**
     * @param id Identificador do Periodo
     * @return Periodo eliminado
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Periodo eliminarPeriodo(Long id) throws Exception {
        Periodo periodo = obterPeriodo(id);
        periodo.setEliminado(true);
        return periodoRepo.save(periodo);
    }

    /**
     * @param periodo_id           O periodo em relação ao qual pretendemos popular as presenças do elemento
     * @param elementodopessoal_id O elemento em relação pretendemos popular as presenças no periodo
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Collection<Presenca> popularPresencas(Long periodo_id, Long elementodopessoal_id) throws Exception {
        Periodo periodo = obterPeriodo(periodo_id);
        ElementoDoPessoal elementoDoPessoal = pessoalService.obterElementoDoPessoal(elementodopessoal_id);
        return escalaService.popularPresencas(periodo, elementoDoPessoal);
    }

    /**
     * Obter elementos para cedencia de troca, ordenados por ordem de favorabilidade de solução
     *
     * @param presenca_id Identificador da Presença, em relação à qual queremos obter elementos para troca
     * @return Lista de elementos disponíveis para troca
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<ElementoDoPessoal> obterElementosDoPessoalParaReforco(Long presenca_id) throws Exception {
        return escalaService.obterElementosDoPessoalParaReforco(presenca_id);
    }

    /**
     * Realizar reforço de uma presenca de um elemento
     *
     * @param presenca_id          Identificador da Presença, em relação à qual queremos obter elementos para troca
     * @param elementodereforco_id Identificador do elemento de reforço
     * @return a Presença com o elemento de reforço
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public Presenca realizarReforco(Long presenca_id, Long elementodereforco_id) throws Exception {
        Presenca presenca = presencaRepo.findOne(presenca_id);
        return escalaService.realizarReforco(presenca_id, elementodereforco_id);
    }
}
