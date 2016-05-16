package pt.isel.ps.li61n.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

        public Long getId() { return id; }

        public void setId(Long id) { this.id = id; }

        @JsonAnyGetter
        public Map<String, String> getPessoa_map() {
            return pessoa_map;
        }

        public void setPessoa_map(Map<String, String> pessoa_map) {
            this.pessoa_map = pessoa_map;
        }

        public String getUri_pessoa() {
            return uri_pessoa;
        }

        public void setUri_pessoa(String uri_pessoa) {
            this.uri_pessoa = uri_pessoa;
        }

        public String getUri_instalacaoPorOmissao() {
            return uri_instalacaoPorOmissao;
        }

        public void setUri_instalacaoPorOmissao(String uri_instalacaoPorOmissao) {
            this.uri_instalacaoPorOmissao = uri_instalacaoPorOmissao;
        }

        public String getUri_postoFuncionalPorOmissao() {
            return uri_postoFuncionalPorOmissao;
        }

        public void setUri_postoFuncionalPorOmissao(String uri_postoFuncionalPorOmissao) {
            this.uri_postoFuncionalPorOmissao = uri_postoFuncionalPorOmissao;
        }

        public String getUri_tipoPresencaPorOmissao() {
            return uri_tipoPresencaPorOmissao;
        }

        public void setUri_tipoPresencaPorOmissao(String uri_tipoPresencaPorOmissao) {
            this.uri_tipoPresencaPorOmissao = uri_tipoPresencaPorOmissao;
        }

        public String getUri_turnoPorOmissao() {
            return uri_turnoPorOmissao;
        }

        public void setUri_turnoPorOmissao(String uri_turnoPorOmissao) {
            this.uri_turnoPorOmissao = uri_turnoPorOmissao;
        }
    }

    @Autowired
    Pessoal_IRepository repo;

    /**
     * @return Lista de pessoal
     */
    @JsonView(View.Summary.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<PessoalDTO> listaPessoal(){

        List<PessoalDTO> pessoalDTOs = new LinkedList<>();
        repo.findAll().stream().forEach(
                pessoal -> pessoalDTOs.add(new PessoalDTO(pessoal)));
        return pessoalDTOs;
    }

}
