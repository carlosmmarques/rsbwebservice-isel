package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

/**
 * AtribuicaoCategoria - Entidade Relação entre os elementos do pessoa, suas categorias, e a caracterização desta
 * associação.
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class AtribuicaoCategoria extends EntidadeAbstractaComIdentificador {

    @ManyToOne
    @JoinColumn(name = "elemento_id")
    private ElementoDoPessoal elementoDoPessoal;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Date dataAtribuicaoCategoria;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Float classificacaoFormacao;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public AtribuicaoCategoria() {
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
     * @return Categoria associada ao elemento do elementoDoPessoal
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria Categoria associada ao elemento do elementoDoPessoal
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
