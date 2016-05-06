package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * PessoalPossuiFormacao - Description
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
    private Formacao formacao;
    private Date dataAquisicaoFormacao;
    private Date dataCaducidadeFormacao;

    public Pessoal getPessoal() {
        return pessoal;
    }

    public void setPessoal(Pessoal pessoal) {
        this.pessoal = pessoal;
    }

    public Formacao getFormacao() {
        return formacao;
    }

    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
    }

    public Date getDataAquisicaoFormacao() {
        return dataAquisicaoFormacao;
    }

    public void setDataAquisicaoFormacao(Date dataAquisicaoFormacao) {
        this.dataAquisicaoFormacao = dataAquisicaoFormacao;
    }

    public Date getDataCaducidadeFormacao() {
        return dataCaducidadeFormacao;
    }

    public void setDataCaducidadeFormacao(Date dataCaducidadeFormacao) {
        this.dataCaducidadeFormacao = dataCaducidadeFormacao;
    }


}
