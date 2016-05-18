package pt.isel.ps.li61n.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pt.isel.ps.li61n.model.entities.Pessoal;
import pt.isel.ps.li61n.model.entities.View;
import pt.isel.ps.li61n.model.repository.Pessoal_IRepository;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PessoalController - Description
 * Created on 13/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Controller
@RequestMapping(value = "/pessoal")
public class PessoalController extends RsbBaseController<Pessoal>{

    /**
     * Classe de representação dos elementos do pessoal.
     */
    public class PessoalDTO{
        private Pessoal pessoa;
        Map<String, String> pessoa_map = new ConcurrentHashMap<>();
        @JsonView(View.Summary.class)
        private Long id;
        @JsonView(View.Summary.class)
        private String uri_pessoa;
        @JsonView(View.Summary.class)
        private String uri_instalacaoPorOmissao;
        @JsonView(View.Summary.class)
        private String uri_postoFuncionalPorOmissao;
        @JsonView(View.Summary.class)
        private String uri_tipoPresencaPorOmissao;
        @JsonView(View.Summary.class)
        private String uri_turnoPorOmissao;

        /**
         * Construtor
         * @param pessoa pessoa a representar
         */
        public PessoalDTO(Pessoal pessoa){
            this.pessoa = pessoa;
            this.id = pessoa.getId();
            this.uri_pessoa = String.format("/pessoal/%d", this.id);
            this.uri_instalacaoPorOmissao = String.format("/unidadeestrutural/%s/instalacao/%s",
                    pessoa.getInstalacao().getUnidadeEstrutural().getId(), pessoa.getInstalacao().getId());
            this.uri_postoFuncionalPorOmissao = String.format("/pessoal/postofuncional/%s", pessoa.getPostoFuncional().getId());
            this.uri_tipoPresencaPorOmissao = String.format("/presenca/tipo/%s", pessoa.getTipoPresenca().getId());
            this.uri_turnoPorOmissao = String.format("/turno/%s", pessoa.getTurno().getId());
            for (Field field : pessoa.getClass().getDeclaredFields()){
                if (field.isAnnotationPresent(JsonView.class))
                    if (View.Summary.class.equals(field.getAnnotation(JsonView.class).value()[0])) {
                        try {
                            String value;
                            field.setAccessible(true);
                            value = field.get(pessoa) == null ? "" : field.get(pessoa).toString();
                            pessoa_map.put(field.getName(), value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
            }
        }

        /**
         * @return id do elemento do pessoal
         */
        public Long getId() { return id; }

        /**
         * @param id id do elemento do pessoal
         */
        public void setId(Long id) { this.id = id; }

        /**
         * @return mapa chave valor com as propriedades anotadas como sumárias
         */
        @JsonAnyGetter
        public Map<String, String> getPessoa_map() {
            return pessoa_map;
        }

        /**
         * @param pessoa_map mapa chave valor com as propriedades anotadas como sumárias
         */
        public void setPessoa_map(Map<String, String> pessoa_map) {
            this.pessoa_map = pessoa_map;
        }

        /**
         * @return Uri do recurso pessoa especifica
         */
        public String getUri_pessoa() {
            return uri_pessoa;
        }

        /**
         * @param uri_pessoa Uri do recurso pessoa especifica
         */
        public void setUri_pessoa(String uri_pessoa) {
            this.uri_pessoa = uri_pessoa;
        }

        /**
         * @return Uri da Instalação por omissão do elemento
         */
        public String getUri_instalacaoPorOmissao() {
            return uri_instalacaoPorOmissao;
        }

        /**
         * @param uri_instalacaoPorOmissao Uri da Instalação por omissão do elemento
         */
        public void setUri_instalacaoPorOmissao(String uri_instalacaoPorOmissao) {
            this.uri_instalacaoPorOmissao = uri_instalacaoPorOmissao;
        }

        /**
         * @return Uri do Posto Funcional por omissão do elemento
         */
        public String getUri_postoFuncionalPorOmissao() {
            return uri_postoFuncionalPorOmissao;
        }

        /**
         * @param uri_postoFuncionalPorOmissao Uri do Posto Funcional por omissão do elemento
         */
        public void setUri_postoFuncionalPorOmissao(String uri_postoFuncionalPorOmissao) {
            this.uri_postoFuncionalPorOmissao = uri_postoFuncionalPorOmissao;
        }

        /**
         * @return Uri do tipo de presença por omissão do elemento
         */
        public String getUri_tipoPresencaPorOmissao() {
            return uri_tipoPresencaPorOmissao;
        }

        /**
         * @param uri_tipoPresencaPorOmissao Uri do tipo de presença por omissão do elemento
         */
        public void setUri_tipoPresencaPorOmissao(String uri_tipoPresencaPorOmissao) {
            this.uri_tipoPresencaPorOmissao = uri_tipoPresencaPorOmissao;
        }

        /**
         * @return Uri do Turno por omissão do elemento
         */
        public String getUri_turnoPorOmissao() {
            return uri_turnoPorOmissao;
        }

        /**
         * @param uri_turnoPorOmissao Uri do Turno por omissão do elemento
         */
        public void setUri_turnoPorOmissao(String uri_turnoPorOmissao) {
            this.uri_turnoPorOmissao = uri_turnoPorOmissao;
        }
    }

    /**
     * Classe para tratamento de excepções via HTTP
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Este elemento do pessoal não existe")
    public class PessoalNotFoundException extends RuntimeException{
        private static final long serialVersionUID = 1L;
        public PessoalNotFoundException(String id){
            super(String.format("Elemento com id %s não existe no repositório", id));
        }
    }

    @Autowired
    Pessoal_IRepository repo;

    /**
     * @return Lista de pessoal
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public List<PessoalDTO> listaPessoal(){

        List<PessoalDTO> pessoalDTOs = new LinkedList<>();
        repo.findAll().stream().forEach(
                pessoal -> pessoalDTOs.add(new PessoalDTO(pessoal)));
        return pessoalDTOs;
    }

    /**
     * @return Um elemento do pessoal
     */
    @JsonView(View.Summary.class) // A representação incluirá apenas campos com esta anotação
    @RequestMapping(value = "/{id}", method = RequestMethod.GET) // Este Método atende ao verbo HTTP GET
    @ResponseBody //Responsebody em JSON
    public PessoalDTO pessoal(@PathVariable String id){
        final Pessoal pessoal = repo.findOne(Long.parseLong(id));
        if (pessoal != null)
            return new PessoalDTO(pessoal);
        throw new PessoalNotFoundException(id);
    }


}
