package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * PessoalPossuiCategoria - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class PessoalPossuiCategoria extends RsbAbstractEntity{
    @ManyToOne
    @JoinColumn(name = "pessoal_id")
    private Pessoal pessoal;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    private Date dataAtribuicaoCategoria;
    private Float classificacaoFormacao;

    public Pessoal getPessoal() {
        return pessoal;
    }

    public void setPessoal(Pessoal pessoal) {
        this.pessoal = pessoal;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getDataAtribuicaoCategoria() {
        return dataAtribuicaoCategoria;
    }

    public void setDataAtribuicaoCategoria(Date dataAtribuicaoCategoria) {
        this.dataAtribuicaoCategoria = dataAtribuicaoCategoria;
    }

    public Float getClassificacaoFormacao() {
        return classificacaoFormacao;
    }

    public void setClassificacaoFormacao(Float classificacaoFormacao) {
        this.classificacaoFormacao = classificacaoFormacao;
    }


}
