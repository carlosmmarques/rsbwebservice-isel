package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.Calendar;

/**
 * RegistoFormacao - Entidade Relação entre os elementos do pessoa, Formação e a caracterização relação
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class RegistoFormacao extends EntidadeAbstractaComIdentificador {

    @ManyToOne
    @JoinColumn(name = "pessoal_id")
    private ElementoDoPessoal elementoDoPessoal;
    @ManyToOne
    @JoinColumn(name = "formacao_id")
    private Formacao formacao;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Date dataAquisicaoFormacao;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Date dataCaducidadeFormacao;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public RegistoFormacao() {
    }

    /**
     * @return Elemento do ElementoDoPessoal
     */
    public ElementoDoPessoal getElementoDoPessoal() {
        return elementoDoPessoal;
    }

    /**
     * @param elementoDoPessoal Elemento do ElementoDoPessoal
     */
    public void setElementoDoPessoal(ElementoDoPessoal elementoDoPessoal) {
        this.elementoDoPessoal = elementoDoPessoal;
    }

    /**
     * @return Formação do elemento do elementoDoPessoal
     */
    public Formacao getFormacao() {
        return formacao;
    }

    /**
     * @param formacao Formação do elemento do elementoDoPessoal
     */
    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
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
     * @return data de caducidade da formação
     */
    public Date getDataCaducidadeFormacao() {
        return dataCaducidadeFormacao;
    }

    /**
     * @param dataCaducidadeFormacao data de caducidade da formação
     */
    public void setDataCaducidadeFormacao(Date dataCaducidadeFormacao) {
        this.dataCaducidadeFormacao = dataCaducidadeFormacao;
    }

    public void setDataCaducidadeFormacao(Float validade){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataAquisicaoFormacao);
        // TODO: melhorar calculo de caducidade
        cal.add(Calendar.MONTH, Math.round(validade*12));
        this.dataCaducidadeFormacao = validade.equals(-1.0) ? null : new Date(cal.getTime().getTime());
    }

}
