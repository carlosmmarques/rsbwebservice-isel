package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.isel.ps.li61n.controller.RsbBaseController;
import pt.isel.ps.li61n.model.entities.Pessoal;
import pt.isel.ps.li61n.model.entities.RegistoFormacao;
import pt.isel.ps.li61n.model.entities.View;
import pt.isel.ps.li61n.model.repository.Pessoal_IRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * PessoalController - Description
 * Created on 13/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping(value = "/pessoal")
public class PessoalController extends RsbBaseController<Pessoal> {

    /**
     * Instância do repositório de elementos do pessoal
     */
    @Autowired
    Pessoal_IRepository pessoal_Repository;

    /**
     * @param postofuncional_id              Opcional - O identificador (Id) do Posto Funcional
     * @param turno_id                       Opcional - O identificador (Id) do turno
     * @param instalacao_id                  Opcional - O identificador (Id) da instalação a que o elemento está atribuido
     * @param categoria_id                   Opcional - O identificador (Id) da categoria do elemento
     * @param formacao_id                    Opcional - O identificador (Id) de uma formação que o elemento tenha adquirido
     * @param responsabilidadeoperacional_id Opcional - O identificador (Id) de uma responsabilidade operacional
     *                                       que o elemento possa desempenhar
     * @return Lista de Pessoal global ou filtrada através dos parametros acima designados.
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(method = RequestMethod.GET) /* Este Método atende ao verbo HTTP GET para "/pessoal" */
    @ResponseBody //Responsebody em JSON
    public List<PessoalDTO> obterVariosElementosDoPessoal(
            @RequestParam(value = "postofuncional_id", required = false) Optional<Long> postofuncional_id,
            @RequestParam(value = "turno_id", required = false) Optional<Long> turno_id,
            @RequestParam(value = "instalacao_id", required = false) Optional<Long> instalacao_id,
            @RequestParam(value = "categoria_id", required = false) Optional<Long> categoria_id,
            @RequestParam(value = "registoFormacao_id", required = false) Optional<Long> formacao_id,
            @RequestParam(value = "responsabilidadeoperacional_id", required = false)
            Optional<Long> responsabilidadeoperacional_id,
            HttpServletRequest request
    ) {

        final List<PessoalDTO> pessoalDTOs = new LinkedList<>();
        pessoal_Repository.findAll().stream()
                .filter(pessoa -> postofuncional_id.map(v -> v.equals(pessoa.getPostoFuncional().getId())).orElse(true))
                .filter(pessoa -> turno_id.map(v -> v.equals(pessoa.getTurno().getId())).orElse(true))
                .filter(pessoa -> instalacao_id.map(v -> v.equals(pessoa.getInstalacao().getId())).orElse(true))
                .filter(pessoa ->
                        categoria_id.map(v ->
                                pessoa.getCategorias().stream().sorted(
                                        (c1, c2) ->
                                                c2.getDataAtribuicaoCategoria()
                                                        .compareTo(c1.getDataAtribuicaoCategoria())
                                ).findFirst().map(c -> c.getCategoria().getId().equals(v))
                        ).orElse(Optional.of(true)).get())
                .filter(pessoa ->
                        formacao_id.map(v ->
                                pessoa.getFormacoes().stream().anyMatch(
                                        f -> f.getFormacao().getId().equals(v))
                        ).orElse(true))
                .filter(pessoa ->
                        responsabilidadeoperacional_id.map(v ->
                                pessoa.getFormacoes().stream().anyMatch(
                                        f -> f.getFormacao().getResponsabilidadesOperacionais().stream().anyMatch(
                                                r -> r.getId().equals(v)))
                        ).orElse(true))
                .forEach(pessoal -> pessoalDTOs.add(new PessoalDTO(pessoal, request)));
        return pessoalDTOs;
    }

    /**
     * @param id Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Representação do elemento na forma de um DTO facilmente serializavel em Json.
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public PessoalDTO obterUmElementoDoPessoal(@PathVariable String id,
                                               HttpServletRequest request
    ) {
        final Pessoal elemento = pessoal_Repository.findOne(Long.parseLong(id));
        if (elemento != null)
            return new PessoalDTO(elemento, request);
        throw new PessoalNotFoundException(id);
    }

    /**
     * @param id Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Lista das formações de um determinado elemento na forma de um DTO facilmente serializável em JSon
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}/formacao", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public List<RegistoFormaçãoDoElementoDTO> obterVariosRegistosDeFormacaoDeUmElemento(
            @PathVariable String id,
            HttpServletRequest request
    ) {
        final List<RegistoFormaçãoDoElementoDTO> registoFormaçãoDoElementoDTOs = new LinkedList<>();
        final Pessoal elemento = pessoal_Repository.findOne(Long.parseLong(id));
        if (elemento != null) {
            elemento.getFormacoes().stream().forEach(
                    registoFormacao ->
                            registoFormaçãoDoElementoDTOs.add(new RegistoFormaçãoDoElementoDTO(registoFormacao, request))
            );
            return registoFormaçãoDoElementoDTOs;
        }
        throw new PessoalNotFoundException(id);
    }


    /**
     * @param id Identificador do elemento do pessoal
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return Lista das formações de um determinado elemento na forma de um DTO facilmente serializável em JSon
     */


    /**
     *
     * @param elemento_id Identificador do elemento do pessoal
     * @param registoFormacao_id Identificador do registo da formação
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     * @return O registo da formação do elemento na forma de um DTO facilmente serializável em JSon
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{elemento_id}/formacao/{registoFormacao_id}", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public RegistoFormaçãoDoElementoDTO obterUmRegistoDeFormacaoDeUmElemento(
            @PathVariable String elemento_id,
            @PathVariable String registoFormacao_id,
            HttpServletRequest request
    ) {
        final Pessoal elemento = pessoal_Repository.findOne(Long.parseLong(elemento_id));
        if (elemento != null) {
            try {
                final RegistoFormacao registoFormacao = elemento.getFormacoes().stream()
                        .filter(regFormacao -> regFormacao.getId().equals(Long.parseLong(registoFormacao_id)))
                        .findAny().get();
                return new RegistoFormaçãoDoElementoDTO(registoFormacao, request);
            } catch (Exception e) { throw new RegistoFormacaoNotFoundException(registoFormacao_id, elemento_id); }
        }
        throw new RegistoFormacaoNotFoundException(registoFormacao_id, elemento_id);
    }

}
