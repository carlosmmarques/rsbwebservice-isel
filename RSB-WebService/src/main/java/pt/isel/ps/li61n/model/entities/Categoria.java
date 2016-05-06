package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;

/**
 * Categoria - Categoria profissional de um membro do Pessoal do Corpo de Bombeiros
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Categoria extends RsbAbstractEntity{

    /**
     * O enumerado (interno a esta classe) que identifica o tipo de quadro (Bombeiro, Comando ou Outro)
     */
    @Enumerated(EnumType.STRING)
    private Quadro quadro;
    /**
     * A abreviatura dada a esta categoria
     */
    private String abreviatura;
    /**
     * A descrição, texto mais completo, a dar a esta categoria
     */
    private String descrição;
    /**
     * O nível Hierarquico de uma categoria (inteiro que determina a ordem de preferencia de Comando)
     */
    private Integer nivelHierarquico;

    public Quadro getQuadro() {
        return quadro;
    }

    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public Integer getNivelHierarquico() {
        return nivelHierarquico;
    }

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
