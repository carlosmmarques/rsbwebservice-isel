package pt.isel.ps.li61n.model.services;

import org.springframework.stereotype.Service;
import pt.isel.ps.li61n.model.entities.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;

/**
 * IPessoalService - Serviço de Fachada para acesso a dados da secção nuclear de gestão de Pessoal
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Service
public interface IPessoalService {


    /**
     *
     * @param nummecanografico
     * @param postofuncional_id Identificador do Posto Funcional (Opcional)
     * @param turno_id Identificadro do Turno (Opcional)
     * @param instalacao_id Identificador da Instalação (Opcional)
     * @param categoria_id Identificador da Categoria (Opcional)
     * @param formacao_id Indentificador do tipo de Formação (Opcional)
     * @param responsabilidadeoperacional_id Identificador de Responsabilidade Operacional a que o elemento está apto
     * @return Colecção de elementos do pessoal
     */
    Collection<ElementoDoPessoal> obterElementosDoPessoal(
            Optional<String> nummecanografico, Optional<Long> postofuncional_id,
            Optional<Long> turno_id,
            Optional<Long> instalacao_id,
            Optional<Long> categoria_id,
            Optional<Long> formacao_id,
            Optional<Long> responsabilidadeoperacional_id
    ) throws Exception;


    /**
     * @param id Identificador do Elemento
     * @return Elemento do Pessoal
     */
    ElementoDoPessoal obterElementoDoPessoal(
            Long id
    ) throws Exception;


    /**
     * @param id Identificador do Elemento
     * @return Colecção de registos de formação do elemento
     */
    Collection<RegistoFormacao> obterRegistosDeFormacaoDeUmElemento(
            Long id
    ) throws Exception;


    /**
     * @param elemento_id Identificador do elemento
     * @param registoFormacao_id Identificador do Registo de Formação
     * @return Registo de formação específico de um Elemento
     */
    RegistoFormacao obterRegistoDeFormacaoDeUmElemento(
            Long elemento_id,
            Long registoFormacao_id
    ) throws Exception;


    /**
     * @param id Identificador do elemento
     * @return Colecção de Responsabilidades Operacionais a que o elemento está apto.
     */
    Collection<ResponsabilidadeOperacional> obterResponsabilidadesOperacionaisDeUmElemento(
            Long id
    ) throws Exception;


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
    ElementoDoPessoal inserirElementoDoPessoal(
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
    ) throws Exception;


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
    ElementoDoPessoal actualizarElementoDoPessoal(
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
    ) throws Exception;


    /**
     * @param elemento_id   Identificador do elemento.
     * @param formacao_id   Identificador da Formação
     * @param dataFormacao data de aquisição da Formação
     * @param dataCaducidade   data de caducidade da Formação
     * @return Registo de formação actualizado
     */
    RegistoFormacao actualizarFormacaoDeElementoDoPessoal(
            Long elemento_id,
            Long formacao_id,
            Date dataFormacao,
            Optional<Date> dataCaducidade,
            boolean[] novoRegisto
    ) throws Exception;


    /**
     * @param id   Identificador do elemento.
     * @return Elemento eliminado
     */
    ElementoDoPessoal EliminarElementoDoPessoal(
            Long id
    ) throws Exception;


    /**
     * @return Colecção de Categorias
     */
    Collection<Categoria> obterCategorias();


    /**
     * @param id Identificador do elemento
     * @return Categoria do elemento.
     */
    Categoria obterCategoria(Long id) throws Exception;


    /**
     * @param quadro Quadro de Categoria (COMANDO, BOMBEIRO OU OUTRO)
     * @param abreviatura Abreviatura da categoria
     * @param descricao Descrição da categoria
     * @param nivelHierarquico Nível Hierarquico
     * @return Categoria inserida
     */
    Categoria inserirCategoria(String quadro, String abreviatura, String descricao, Integer nivelHierarquico) throws Exception;


    /**
     * @param  id Identificador da categoria
     * @param quadro Quadro de Categoria (COMANDO, BOMBEIRO OU OUTRO)
     * @param abreviatura Abreviatura da categoria
     * @param descricao Descrição da categoria
     * @param nivelHierarquico Nível Hierarquico
     * @return Categoria inserida
     */
    Categoria actualizarCategoria(Long id, Optional<String> quadro, Optional<String> abreviatura, Optional<String> descricao, Optional<Integer> nivelHierarquico) throws Exception;


    /**
     * @param id               Identificador da Categoria
     * @return Categoria eliminada
     */

    Categoria eliminarCategoria(Long id) throws Exception;


    /**
     * @return Colecção de Postos Funcionais
     */
    Collection<PostoFuncional> obterPostosFuncionais() throws Exception;


    /**
     * @param id Identificador do Posto Funcional
     * @return Posto funcional
     */
    PostoFuncional obterPostoFuncional(Long id) throws Exception;


    /**
     * @param designacao Designação do Posto Funcional
     * @param descricao Descrição do Posto Funcional
     * @return Posto Funcional inserido
     */
    PostoFuncional inserirPostoFuncional(String designacao, Optional<String> descricao) throws Exception;


    /**
     * @param id Identificador do Posto Funcional
     * @param designacao Designação do Posto Funcional
     * @param descricao Descrição do Posto Funcional
     * @return Posto Funcional actualizado
     */
    PostoFuncional actualizarPostoFuncional(Long id, Optional<String> designacao, Optional<String> descricao) throws Exception;


    /**
     * @param id Identificador do Posto Funcional
     * @return Posto Funcional eliminado
     */
    PostoFuncional eliminarPostoFuncional(Long id) throws Exception;


    /**
     * @return Colecção de Responsabilidades Operacionais
     */
    Collection<ResponsabilidadeOperacional> obterResponsabilidadesOperacionais() throws Exception;


    /**
     * @param id Identificador do Responsabilidade Operacional
     * @return Responsabilidade Operacional
     */
    ResponsabilidadeOperacional obterResponsabilidadeOperacional(
            Long id
    ) throws Exception;


    /**
     * @param tipopresenca_id Identificador do Tipo de presença
     * @param dependefactoreligibilidade Indica se o desempenho da Responsabilidade Operacional depende do factor de eligibilidade
     * @param designacao Designação da Responsabilidade Operacional
     * @param sigla Sigla da Responsabilidade Operacional
     * @param tiposervico Texto descritivo do Tipo de Serviço
     * @return Resposnsabilidade Operacional inserida
     */
    ResponsabilidadeOperacional inserirResponsabilidadeOperacional(
            String tipopresenca_id,
            Boolean dependefactoreligibilidade,
            String designacao,
            String sigla,
            ResponsabilidadeOperacional.TipoServico tiposervico
    ) throws Exception;


    /**
     * @param id Identificador do Responsabilidade Operacional
     * @param tipopresenca_id Identificador do Tipo de presença
     * @param dependefactoreligibilidade Indica se o desempenho da Responsabilidade Operacional depende do factor de eligibilidade
     * @param designacao Designação da Responsabilidade Operacional
     * @param sigla Sigla da Responsabilidade Operacional
     * @param tiposervico Texto descritivo do Tipo de Serviço
     * @return Resposnsabilidade Operacional actualizada
     */
    ResponsabilidadeOperacional actualizarResponsabilidadeOperacional(
            Long id,
            Optional<String> tipopresenca_id,
            Optional<Boolean> dependefactoreligibilidade,
            Optional<String> designacao,
            Optional<String> sigla,
            Optional<ResponsabilidadeOperacional.TipoServico> tiposervico
    ) throws Exception;


    /**
     * @param id Identificador do Responsabilidade Operacional
     * @return Resposnsabilidade Operacional eliminada
     */
    ResponsabilidadeOperacional eliminarResponsabilidadeOperacional(
            Long id
    ) throws Exception;


    /**
     * @return Colecção de Formações
     */
    Collection<Formacao> obterFormacoes() throws Exception;


    /**
     * @param id Identificador do Formação
     * @return Formação
     */
    Formacao obterFormacao(Long id) throws Exception;


    /**
     * @param validade Validade (Caducidade) da Formação
     * @param designacao Designação da Formação
     * @param descricao Descrição da Formação
     * @return Formação inserida
     */
    Formacao inserirFormacao(Float validade, String designacao, Optional<String> descricao) throws Exception;


    /**
     * @param id Identificador do Formação
     * @param validade Validade (Caducidade) da Formação
     * @param designacao Designação da Formação
     * @param descricao Descrição da Formação
     * @return Formacao actualizada
     */
    Formacao actualizarFormacao(Long id, Optional<Float> validade, Optional<String> designacao, Optional<String> descricao) throws Exception;


    /**
     * @param id Identificador do Formação
     * @return Formacao eliminada
     */
    Formacao eliminarFormacao(Long id) throws Exception;

    /**
     * @param responsabilidadeoperacional_id Identificador da Responsabilidade Operacional
     * @param formacao_id                    Identificador da Formação
     * @return
     */
    ResponsabilidadeOperacional associarFormacaoAResponsabilidadeOperacional(Long responsabilidadeoperacional_id, Long formacao_id);

    /**
     * @param responsabilidadeoperacional_id Identificador da Responsabilidade Operacional
     * @param formacao_id                    Identificador da Formação
     * @return
     */
    ResponsabilidadeOperacional eliminarAssociacaoDeFormacaoAResponsabilidadeOperacional(Long responsabilidadeoperacional_id, Long formacao_id);
}