package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;

/**
 * Categoria - Categoria profissional de um membro do ElementoDoPessoal do Corpo de Bombeiros
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Categoria extends RsbEntidadeAbstracta {

    @Enumerated(EnumType.STRING)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Quadro quadro;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String abreviatura;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String descrição;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Integer nivelHierarquico;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public Categoria() {
    }

    /**
     * @return enumerado (interno a esta classe) que identifica o tipo de quadro (Bombeiro, Comando ou Outro)
     */
    public Quadro getQuadro() {
        return quadro;
    }

    /**
     * @param quadro enumerado (interno a esta classe) que identifica o tipo de quadro (Bombeiro, Comando ou Outro)
     */
    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }

    /**
     * @return abreviatura dada a esta categoria (texto curto).
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura abreviatura dada a esta categoria (texto curto).
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @return descrição, texto mais completo, a dar a esta categoria.
     */
    public String getDescrição() {
        return descrição;
    }

    /**
     * @param descrição descrição, texto mais completo, a dar a esta categoria.
     */
    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    /**
     * @return nível Hierarquico de uma categoria (inteiro que determina a ordem de preferencia de Comando).
     */
    public Integer getNivelHierarquico() {
        return nivelHierarquico;
    }

    /**
     * @param nivelHierarquico nível Hierarquico de uma categoria (inteiro que determina a ordem de preferencia de
     *                         Comando).
     */
    public void setNivelHierarquico(Integer nivelHierarquico) {
        this.nivelHierarquico = nivelHierarquico;
    }


    /**
     * Quadro - Enumerado com as entradas para o tipo de quadro (COMANDO / BOMBEIRO / OUTRO)
     * Created on 03/05/2016.
     *
     * @author
     * Carlos Marques - carlosmmarques@gmail.com
     * Tiago Venturinha - tventurinha@gmail.com
     */
    public enum Quadro {
        COMANDO, BOMBEIRO, OUTRO
    }
}
