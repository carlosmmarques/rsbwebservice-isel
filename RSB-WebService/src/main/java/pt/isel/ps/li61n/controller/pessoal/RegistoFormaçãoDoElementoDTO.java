package pt.isel.ps.li61n.controller.pessoal;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.model.entities.Formacao;
import pt.isel.ps.li61n.model.entities.RegistoFormacao;
import pt.isel.ps.li61n.model.entities.View;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe de representação do registo de formação de um elemento do pessoal.
 */
public class RegistoFormaçãoDoElementoDTO {
    Map<String, String> formacao_map = new HashMap<>();
    private String baseUrl;
    @JsonView(View.Summary.class)
    private Long id;
    @JsonView(View.Summary.class)
    private String uri_registoFormacaoDoElemento;
    @JsonView(View.Summary.class)
    private String uri_formacao;
    @JsonView(View.Summary.class)
    private Date dataAquisicaoFormacao;
    @JsonView(View.Summary.class)
    private Date dataCaducidade;

    /**
     * parameterless constructor
     */
    public RegistoFormaçãoDoElementoDTO() {
    }

    /**
     * Construtor
     * @param registoFormação  Registo de formação a representar
     * @param request HttpServletRequest - Util para obtenção dos elementos do contexto da execução do serviço,
     *                nomeadamente do URI.
     */
    public RegistoFormaçãoDoElementoDTO(RegistoFormacao registoFormação, HttpServletRequest request) {
        baseUrl = String.format("%s://%s:%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort());
        this.id = registoFormação.getId();
        this.uri_registoFormacaoDoElemento = String.format("%s/pessoal/%s/formacao/%s",
                this.baseUrl,
                registoFormação.getElementoDoPessoal().getId(),
                registoFormação.getId());
        this.uri_formacao = String.format("%s/formacao/%s",
                this.baseUrl,
                registoFormação.getFormacao().getId());
        this.dataAquisicaoFormacao = registoFormação.getDataAquisicaoFormacao();
        this.dataCaducidade = registoFormação.getDataCaducidadeFormacao();
        Formacao formacao = registoFormação.getFormacao();
        for (Field field : formacao.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonView.class))
                if (field.getAnnotation(JsonView.class).value()[0].equals(View.Summary.class)) {
                    try {
                        String value;
                        field.setAccessible(true);
                        value = field.get(formacao) == null ? "" : field.get(formacao).toString();
                        formacao_map.put(field.getName(), value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        }

    }

    /**
     * @return id do elemento do pessoal
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id id do elemento do pessoal
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return mapa chave valor com as propriedades anotadas como sumárias
     */
    @JsonAnyGetter
    public Map<String, String> getFormacao_map() {
        return formacao_map;
    }

    /**
     * @param formacao_map mapa chave valor com as propriedades anotadas como sumárias
     */
    public void setFormacao_map(Map<String, String> formacao_map) {
        this.formacao_map = formacao_map;
    }

    /**
     * @return Uri do recurso registoFormação especifica
     */
    public String getUri_registoFormacaoDoElemento() {
        return uri_registoFormacaoDoElemento;
    }

    /**
     * @param uri_registoFormacaoDoElemento Uri do recurso registoFormação especifica
     */
    public void setUri_registoFormacaoDoElemento(String uri_registoFormacaoDoElemento) {
        this.uri_registoFormacaoDoElemento = uri_registoFormacaoDoElemento;
    }

    /**
     * @return Uri da Formação
     */
    public String getUri_formacao() {
        return uri_formacao;
    }

    /**
     * @param uri_formacao Uri da Formação
     */
    public void setUri_formacao(String uri_formacao) {
        this.uri_formacao = uri_formacao;
    }

    /**
     * @return Data de aquisição da formação
     */
    public Date getDataAquisicaoFormacao() {
        return dataAquisicaoFormacao;
    }

    /**
     * @param dataAquisicaoFormacao Data de aquisição da formação
     */
    public void setDataAquisicaoFormacao(Date dataAquisicaoFormacao) {
        this.dataAquisicaoFormacao = dataAquisicaoFormacao;
    }

    /**
     * @return Data de caducidade da formação
     */
    public Date getDataCaducidade() {
        return dataCaducidade;
    }

    /**
     * @param dataCaducidade Data de caducidade da formação
     */
    public void setDataCaducidade(Date dataCaducidade) {
        this.dataCaducidade = dataCaducidade;
    }
}
