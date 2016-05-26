package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

/**
 * PessoalPossuiFormacao - Entidade Relação entre os elementos do pessoa, Formação e a caracterização relação
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class PessoalPossuiFormacao extends RsbAbstractEntity{

    @ManyToOne
    @JoinColumn(name = "pessoal_id")
    private Pessoal pessoal;
    @ManyToOne
    @JoinColumn(name = "formacao_id")
    @JsonView(View.Summary.class)
    private Formacao formacao;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView(View.Summary.class)
    private Date dataAquisicaoFormacao;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView(View.Summary.class)
    private Date dataCaducidadeFormacao;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public PessoalPossuiFormacao() {
    }

    /**
     * @return Elemento do Pessoal
     */
    public Pessoal getPessoal() {
        return pessoal;
    }

    /**
     * @param pessoal Elemento do Pessoal
     */
    public void setPessoal(Pessoal pessoal) {
        this.pessoal = pessoal;
    }

    /**
     * @return Formação do elemento do pessoal
     */
    public Formacao getFormacao() {
        return formacao;
    }

    /**
     * @param formacao Formação do elemento do pessoal
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
}
