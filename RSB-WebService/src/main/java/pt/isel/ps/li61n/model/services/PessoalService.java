package pt.isel.ps.li61n.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ps.li61n.controller.pessoal.ConflictException;
import pt.isel.ps.li61n.controller.pessoal.DeletedResourceException;
import pt.isel.ps.li61n.controller.pessoal.NotFoundException;
import pt.isel.ps.li61n.model.entities.*;
import pt.isel.ps.li61n.model.repository.*;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PessoalService - Serviço de Fachada para acesso a dados da secção nuclear de gestão de Pessoal.
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public class PessoalService implements IPessoalService {

    /**
     * Instâncias dos repositórios
     */
    @Autowired
    IPessoalRepositorio pessoalRepo;
    @Autowired
    IPostoFuncionalRepositorio postoFuncionalRepo;
    @Autowired
    ITipoPresencaRepositorio tipoPresencaRepo;
    @Autowired
    ICategoriaRepositorio categoriaRepo;
    @Autowired
    IInstalacaoRepositorio instalacaoRepo;
    @Autowired
    IAtribuicaoCategoriaRepositorio atribuicaoCategoriaRepo;
    @Autowired
    ITurnoRepositorio turnoRepo;
    @Autowired
    IRegistoFormacaoRepositorio registoFormacaoRepo;
    @Autowired
    IFormacaoRepositorio formacaoRepo;


    /**
     * @param postofuncional_id              Identificador do Posto Funcional (Opcional)
     * @param turno_id                       Identificadro do Turno (Opcional)
     * @param instalacao_id                  Identificador da Instalação (Opcional)
     * @param categoria_id                   Identificador da Categoria (Opcional)
     * @param formacao_id                    Indentificador do tipo de Formação (Opcional)
     * @param responsabilidadeoperacional_id Identificador de Responsabilidade Operacional a que o elemento está apto
     * @return Colecção de elementos do pessoal
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<ElementoDoPessoal> obterElementosDoPessoal(
            Optional<Long> postofuncional_id,
            Optional<Long> turno_id,
            Optional<Long> instalacao_id,
            Optional<Long> categoria_id,
            Optional<Long> formacao_id,
            Optional<Long> responsabilidadeoperacional_id
    ) throws Exception {
        return pessoalRepo.findAll().stream()
                .filter(pessoa -> postofuncional_id.map(v -> v.equals(pessoa.getPostoFuncional().getId())).orElse(true))
                .filter(pessoa -> turno_id.map(v -> v.equals(pessoa.getTurno().getId())).orElse(true))
                .filter(pessoa -> instalacao_id.map(v -> v.equals(pessoa.getInstalacao().getId())).orElse(true))
                .filter(pessoa ->
                        categoria_id.map(v ->
                                pessoa.getAtribuicõesDeCategoria().stream().sorted(
                                        (c1, c2) ->
                                                c2.getDataAtribuicaoCategoria()
                                                        .compareTo(c1.getDataAtribuicaoCategoria())
                                ).findFirst().map(c -> c.getCategoria().getId().equals(v))
                        ).orElse(Optional.of(true)).get())
                .filter(pessoa ->
                        formacao_id.map(v ->
                                pessoa.getRegistosFormacao().stream().anyMatch(
                                        f -> f.getFormacao().getId().equals(v))
                        ).orElse(true))
                .filter(pessoa ->
                        responsabilidadeoperacional_id.map(v ->
                                pessoa.getRegistosFormacao().stream().anyMatch(
                                        f -> f.getFormacao().getResponsabilidadesOperacionais().stream().anyMatch(
                                                r -> r.getId().equals(v)))
                        ).orElse(true))
//                .filter(pessoa -> !pessoa.getEliminado())
                .collect(Collectors.toList());
    }

    /**
     * @param id Identificador do Elemento
     * @return Elemento do Pessoal
     */
    @Override
    @Transactional(readOnly = true)
    public ElementoDoPessoal obterUmElementoDoPessoal(
            Long id
    ) throws Exception {
        final ElementoDoPessoal elemento = pessoalRepo.findOne(id);
        if (elemento == null)
            throw new NotFoundException(String.format("Não é possível obter o Elemento do Pessoal com id: %s", id));
        if (elemento.getEliminado())
            throw new DeletedResourceException(String.format("O elemento solicitado foi eliminado: %s", id));
        return elemento;
    }

    /**
     * @param id Identificador do Elemento
     * @return Colecção de registos de formação do elemento
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<RegistoFormacao> obterRegistosDeFormacaoDeUmElemento(
            Long id
    ) throws Exception {
        final ElementoDoPessoal elemento = pessoalRepo.findOne(id);
        if (elemento == null)
            throw new NotFoundException(String.format("Não é possível obter o Elemento do Pessoal com id: %s", id));
        if (elemento.getEliminado())
            throw new DeletedResourceException(String.format("O elemento solicitado foi eliminado: %s", id));
        return elemento.getRegistosFormacao();
    }

    /**
     * @param elemento_id        Identificador do elemento
     * @param registoFormacao_id Identificador do Registo de Formação
     * @return Registo de formação específico de um Elemento
     */
    @Override
    @Transactional(readOnly = true)
    public RegistoFormacao obterUmRegistoDeFormacaoDeUmElemento(
            Long elemento_id,
            Long registoFormacao_id
    ) throws Exception {
        final ElementoDoPessoal elemento = pessoalRepo.findOne(elemento_id);
        if (elemento == null)
            throw new NotFoundException(String.format("Não é possível obter o Elemento do Pessoal com id: %s", elemento_id));
        if (elemento.getEliminado())
            throw new DeletedResourceException(String.format("O elemento solicitado foi eliminado: %s", elemento_id));
        try {
            final RegistoFormacao registoFormacao = elemento.getRegistosFormacao().stream()
                    .filter(regFormacao -> regFormacao.getId().equals(registoFormacao_id))
                    .filter(regFormacao -> !regFormacao.getEliminado())
                    .findAny().get();
            return registoFormacao;
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não existe nenhum registo de formação com o id %s para o elemento %s", registoFormacao_id, elemento_id));
        }
    }

    /**
     * @param id Identificador do elemento
     * @return Colecção de Responsabilidades Operacionais a que o elemento está apto.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<ResponsabilidadeOperacional> obterResponsabilidadesOperacionaisDeUmElemento(
            Long id
    ) throws Exception {
        final Set<ResponsabilidadeOperacional> responsabilidadesOperacionais = new LinkedHashSet<>();
        final ElementoDoPessoal elemento = pessoalRepo.findOne(id);
        elemento.getRegistosFormacao().stream()
                .filter(registoFormacao -> //Obter apenas registos de formação que não estão caducados
                        registoFormacao.getDataCaducidadeFormacao().getTime() > Calendar.getInstance().getTimeInMillis())
                .forEach(registoFormacao ->
                        registoFormacao.getFormacao().getResponsabilidadesOperacionais().stream()
                                .forEach(responsabilidadesOperacionais::add)
                );
        return responsabilidadesOperacionais;
    }

    /**
     * @param idInterno               Identificador interno da Unidade Estrutural
     * @param matricula               Matricula do elemento
     * @param nummecanografico        Numero mecanográfico (Unico - Destina-se a mapear ao sistema de RH da CML)
     * @param abreviatura             Abreviatura - Nome curto
     * @param nome                    Nome do elemento
     * @param datanascimento          Data de nascimento do elemento
     * @param telefone1               Telefone do elemento
     * @param postofuncional_id       Código (id) do Posto Funcional por omissão
     * @param tipopresenca_id         Codigo (id) do tipo de presença por omissão
     * @param turno_id                código (id) do turno por omissão
     * @param instalacao_id           código (id) da Instalação por omissão
     * @param categoria_id            Código (id) da Categoria por omissão
     * @param dataatribuicaocategoria Data a que a categoria foi atribuida ao elemento
     * @param classificacaoformacao   Classificação com que o elemento adquiriu a categoria
     * @param telefone2               Telefone do elemento
     * @param email                   e-mail do elemento
     * @param nif                     Numero de Identificação Fiscal do Elemento
     * @param dataingresso            Data de Ingresso no Corpo de Bombeiros
     * @param tipodocidentificacao    Tipo de documento de Identificação (Bilhete de Identidade / Cartão de Cidadão)
     * @param numdocidentificacao     Numero do documento de Identificação
     * @param factorelegibilidade     Factor de elegibilidade
     * @return Elemento do pessoal inserido
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public ElementoDoPessoal inserirUmElementoDoPessoal(
            String idInterno,
            String matricula,
            String nummecanografico,
            String abreviatura,
            String nome,
            Date datanascimento,
            String telefone1,
            Long postofuncional_id,
            String tipopresenca_id,
            Long turno_id,
            Long instalacao_id,
            Long categoria_id,
            Date dataatribuicaocategoria,
            Float classificacaoformacao,
            Optional<String> telefone2,
            Optional<String> email,
            Optional<String> nif,
            Optional<Date> dataingresso,
            Optional<ElementoDoPessoal.TipoDocIdentificacao> tipodocidentificacao,
            Optional<String> numdocidentificacao,
            Optional<Float> factorelegibilidade
    ) throws Exception {

        validarExistenciaDeNumeroMecanografico(nummecanografico);

        ElementoDoPessoal elemento = new ElementoDoPessoal();
        AtribuicaoCategoria atribuicaoDeCategoria = new AtribuicaoCategoria();

        elemento.setIdInterno(idInterno);
        elemento.setMatricula(matricula);
        elemento.setNumMecanografico(nummecanografico);
        elemento.setAbreviatura(abreviatura);
        elemento.setNome(nome);
        elemento.setDataNascimento(datanascimento);
        elemento.setTelefone1(telefone1);
        telefone2.ifPresent(elemento::setTelefone2);
        email.ifPresent(elemento::seteMail);
        nif.ifPresent(elemento::setNif);
        dataingresso.ifPresent(elemento::setDataIngresso);
        tipodocidentificacao.ifPresent(elemento::setTipoDocIdentificacao);
        numdocidentificacao.ifPresent(elemento::setNumDocIdentificacao);
        factorelegibilidade.ifPresent(elemento::setFactorElegibilidade);
        try {
            elemento.setPostoFuncional(postoFuncionalRepo.findOne(postofuncional_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter o Posto Funcional com id: %s", postofuncional_id.toString()));
        }
        try {
            elemento.setTipoPresenca(tipoPresencaRepo.findOne(tipopresenca_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter o Tipo de Presença com id: %s", tipopresenca_id));
        }
        try {
            elemento.setTurno(turnoRepo.findOne(turno_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter o Turno com id: %s", turno_id));
        }
        try {
            elemento.setInstalacao(instalacaoRepo.findOne(instalacao_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter a Instalação com id: %s", instalacao_id));
        }
        try {
            atribuicaoDeCategoria.setCategoria(categoriaRepo.findOne(categoria_id));
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter a Categoria com id: %s", categoria_id));
        }
        elemento = pessoalRepo.saveAndFlush(elemento);
        atribuicaoDeCategoria.setElementoDoPessoal(elemento);
        atribuicaoDeCategoria.setDataAtribuicaoCategoria(dataatribuicaocategoria);
        atribuicaoDeCategoria.setClassificacaoFormacao(classificacaoformacao);
        atribuicaoDeCategoria = atribuicaoCategoriaRepo.saveAndFlush(atribuicaoDeCategoria);
        List<AtribuicaoCategoria> atribuicoesCategoria = new ArrayList<AtribuicaoCategoria>();
        atribuicoesCategoria.add(atribuicaoDeCategoria);
        elemento.setAtribuicõesDeCategoria(atribuicoesCategoria);

        return elemento;
    }

    /**
     * @param id                      Identificador do elemento.
     * @param idInterno               Identificador interno da Unidade Estrutural
     * @param matricula               Matricula do elemento
     * @param abreviatura             Abreviatura - Nome curto
     * @param nome                    Nome do elemento
     * @param datanascimento          Data de nascimento do elemento
     * @param telefone1               Telefone do elemento
     * @param postofuncional_id       Código (id) do Posto Funcional por omissão
     * @param tipopresenca_id         Codigo (id) do tipo de presença por omissão
     * @param turno_id                código (id) do turno por omissão
     * @param instalacao_id           código (id) da Instalação por omissão
     * @param categoria_id            Código (id) da Categoria por omissão
     * @param dataatribuicaocategoria Data a que a categoria foi atribuida ao elemento
     * @param classificacaoformacao   Classificação com que o elemento adquiriu a categoria
     * @param telefone2               Telefone do elemento
     * @param email                   e-mail do elemento
     * @param nif                     Numero de Identificação Fiscal do Elemento
     * @param dataingresso            Data de Ingresso no Corpo de Bombeiros
     * @param tipodocidentificacao    Tipo de documento de Identificação (Bilhete de Identidade / Cartão de Cidadão)
     * @param numdocidentificacao     Numero do documento de Identificação
     * @param factorelegibilidade     Factor de elegibilidade
     * @return Elemento do pessoal actualizado
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public ElementoDoPessoal actualizarUmElementoDoPessoal(
            Long id,
            Optional<String> idInterno,
            Optional<String> matricula,
            Optional<String> abreviatura,
            Optional<String> nome,
            Optional<Date> datanascimento,
            Optional<String> telefone1,
            Optional<Long> postofuncional_id,
            Optional<String> tipopresenca_id,
            Optional<Long> turno_id,
            Optional<Long> instalacao_id,
            Optional<Long> categoria_id,
            Optional<Date> dataatribuicaocategoria,
            Optional<Float> classificacaoformacao,
            Optional<String> telefone2,
            Optional<String> email,
            Optional<String> nif,
            Optional<Date> dataingresso,
            Optional<ElementoDoPessoal.TipoDocIdentificacao> tipodocidentificacao,
            Optional<String> numdocidentificacao,
            Optional<Float> factorelegibilidade
    ) throws Exception {

        ElementoDoPessoal elemento = pessoalRepo.findOne(id);

        if (elemento == null)
            throw new NotFoundException(String.format("Não é possível obter o Elemento do Pessoal com id: %s", id));
        if (elemento.getEliminado())
            throw new DeletedResourceException(String.format("O elemento solicitado foi eliminado: %s", id));

        idInterno.ifPresent(elemento::setIdInterno);
        matricula.ifPresent(elemento::setMatricula);
        // nummecanografico.ifPresent(elemento::setNumMecanografico);
        abreviatura.ifPresent(elemento::setAbreviatura);
        nome.ifPresent(elemento::setNome);
        datanascimento.ifPresent(elemento::setDataNascimento);
        telefone1.ifPresent(elemento::setTelefone1);
        telefone2.ifPresent(elemento::setTelefone2);
        email.ifPresent(elemento::seteMail);
        nif.ifPresent(elemento::setNif);
        dataingresso.ifPresent(elemento::setDataIngresso);
        tipodocidentificacao.ifPresent(elemento::setTipoDocIdentificacao);
        numdocidentificacao.ifPresent(elemento::setNumDocIdentificacao);
        factorelegibilidade.ifPresent(elemento::setFactorElegibilidade);
        postofuncional_id.ifPresent(pf -> {
            try {
                elemento.setPostoFuncional(postoFuncionalRepo.findOne(pf));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter o Posto Funcional com id: %s", pf.toString()));
            }
        });
        tipopresenca_id.ifPresent(tp -> {
            try {
                elemento.setTipoPresenca(tipoPresencaRepo.findOne(tp));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter o Tipo de Presença com id: %s", tp.toString()));
            }
        });
        turno_id.ifPresent(t -> {
            try {
                elemento.setTurno(turnoRepo.findOne(t));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter o Turno com id: %s", t.toString()));
            }
        });
        instalacao_id.ifPresent(i -> {
            try {
                elemento.setInstalacao(instalacaoRepo.findOne(i));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter a Instalação com id: %s", i.toString()));
            }
        });
        categoria_id.ifPresent(cat_id -> {
            List<AtribuicaoCategoria> atribuicoesCategoria = atribuicaoCategoriaRepo.findByElementoDoPessoal(elemento);
            Optional<AtribuicaoCategoria> atribuicaoDeCategoriaOpt = atribuicoesCategoria
                    .stream()
                    .filter(c -> c.getCategoria().getId().equals(cat_id))
                    .findFirst();
            AtribuicaoCategoria atribuicaoCategoria =
                    atribuicaoDeCategoriaOpt.isPresent() ?
                            atribuicaoDeCategoriaOpt.get() :
                            new AtribuicaoCategoria();
            if (atribuicaoCategoria.getCategoria() == null) {
                try {
                    atribuicaoCategoria.setCategoria(categoriaRepo.findOne(cat_id));
                    atribuicaoCategoria.setElementoDoPessoal(elemento);
                } catch (Exception e) {
                    throw new NotFoundException(String.format("Não é possível obter a Categoria com id: %s", cat_id.toString()));
                }
            }
            dataatribuicaocategoria.ifPresent(atribuicaoCategoria::setDataAtribuicaoCategoria);
            classificacaoformacao.ifPresent(atribuicaoCategoria::setClassificacaoFormacao);
            atribuicaoCategoriaRepo.save(atribuicaoCategoria);
        });

        return pessoalRepo.save(elemento);

    }

    /**
     * @param elemento_id    Identificador do elemento.
     * @param formacao_id    Identificador da Formação
     * @param dataFormacao   data de aquisição da Formação
     * @param dataCaducidade data de caducidade da Formação
     * @return Registo de formação actualizado
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public RegistoFormacao actualizarFormacaoDeElementoDoPessoal(
            Long elemento_id,
            Long formacao_id,
            Date dataFormacao,
            Optional<Date> dataCaducidade,
            boolean[] novoRegisto
    ) throws Exception {
        ElementoDoPessoal elemento = pessoalRepo.findOne(elemento_id);
        List<RegistoFormacao> registosFormacao = registoFormacaoRepo.findByElementoDoPessoal(elemento);
        Optional<RegistoFormacao> registoFormacaoOpt;
        try {
            registoFormacaoOpt = registosFormacao
                    .stream()
                    .filter(r -> r.getFormacao().getId().equals(formacao_id))
                    .findFirst();
        } catch (Exception e) {
            throw new NotFoundException(String.format("Não é possível obter o registo da formação com id %s para o elemento %s", formacao_id, elemento_id));
        }
        RegistoFormacao registoFormacao =
                registoFormacaoOpt.isPresent() ?
                        registoFormacaoOpt.get() :
                        new RegistoFormacao();
        if (registoFormacao.getElementoDoPessoal() == null) {
            try {
                novoRegisto[0] = true;
                registoFormacao.setElementoDoPessoal(elemento);
                registoFormacao.setFormacao(formacaoRepo.findOne(formacao_id));
            } catch (Exception e) {
                throw new NotFoundException(String.format("Não é possível obter o registo de Formacao com id: %s", formacao_id));
            }
        }
        registoFormacao.setDataAquisicaoFormacao(dataFormacao);
        if (dataCaducidade.isPresent()) registoFormacao.setDataCaducidadeFormacao(dataCaducidade.get());
        else registoFormacao.setDataCaducidadeFormacao(registoFormacao.getFormacao().getValidade());
        return registoFormacaoRepo.save(registoFormacao);
    }

    /**
     * @param id Identificador do elemento.
     * @return Elemento eliminado
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public ElementoDoPessoal EliminarElementoDoPessoal(Long id) throws Exception {
        ElementoDoPessoal elemento = pessoalRepo.findOne(id);
        elemento.setEliminado(true);
        /* TODO Validar a necessidade de lançar excepção quando um elmento é eliminado. Exemplo:
            Um elementoa afecto a um turno. Faz sentido validar esta afectação antes de o sinalizar como "Eliminado"?
         */

        return pessoalRepo.save(elemento);

    }

    /**
     * @return Lista global de Categorias
     */
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> obterCategorias() {
        return categoriaRepo.findAll();
    }

    /**
     * @param numeroMecanografico O numero mecanográfico do elemento a validar
     * @throws ConflictException se o numero mecanográfico já está atribuido
     */
    @Transactional(readOnly = true)
    private void validarExistenciaDeNumeroMecanografico(String numeroMecanografico) throws ConflictException {
        if (pessoalRepo.findAll().stream()
                .filter(p -> p.getNumMecanografico().equals(numeroMecanografico))
                .count() > 0)
            throw new ConflictException(String.format("Já existe um elemento com o numero mecanográfico %s", numeroMecanografico));
    }

}
