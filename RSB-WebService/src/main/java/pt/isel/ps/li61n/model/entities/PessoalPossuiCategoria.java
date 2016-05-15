package pt.isel.ps.li61n.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

/**
 * PessoalPossuiCategoria - Entidade Relação entre os elementos do pessoa, suas categorias, e a caracterização desta
 * associação.
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataAtribuicaoCategoria;
    private Float classificacaoFormacao;

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
     * @return Categoria associada ao elemento do pessoal
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria Categoria associada ao elemento do pessoal
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return Data de atribuição de categoria
     */
    public Date getDataAtribuicaoCategoria() {
        return dataAtribuicaoCategoria;
    }

    /**
     * @param dataAtribuicaoCategoria Data de atribuição de categoria
     */
    public void setDataAtribuicaoCategoria(Date dataAtribuicaoCategoria) {
        this.dataAtribuicaoCategoria = dataAtribuicaoCategoria;
    }

    /**
     * @return Classificação na Formação que confere a atribuição da categoria
     */
    public Float getClassificacaoFormacao() {
        return classificacaoFormacao;
    }

    /**
     * @param classificacaoFormacao Classificação na Formação que confere a atribuição da categoria
     */
    public void setClassificacaoFormacao(Float classificacaoFormacao) {
        this.classificacaoFormacao = classificacaoFormacao;
    }
}
