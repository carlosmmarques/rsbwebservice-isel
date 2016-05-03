package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;

/**
 * Categoria - Categoria de um membro do Pessoal do Corpo de Bombeiros
 * Created on 03/05/2016.
 *
 * @author
 * Carlos Marques - carlosmmarques@gmail.com
 * Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Categoria {

    /**
     * Quadro - Enumerado com as entradas para o tipo de quadro (COMANDO / BOMBEIRO / OUTRO)
     * Created on 03/05/2016.
     *
     * @author
     * Carlos Marques - carlosmmarques@gmail.com
     * Tiago Venturinha - tventurinha@gmail.com
     */
    public static enum Quadro {
        COMANDO, BOMBEIRO, OUTRO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Enumerated(EnumType.STRING)
    private Quadro quadro;
    private String abreviatura;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

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

    private String descrição;
    private Integer nivelHierarquico;


}
