package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;

/**
 * Guarnicao - Entidade que representa a dotação de meios às Unidades Operacionais, em termos das 
 * quantidades mínimas e máximas de representantes com capacidade de Responsabilidade Operacional.
 * 
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Guarnicao extends RsbAbstractEntity{

    @ManyToOne(optional = false)
    @JoinColumn (name= "unidadeOperacional_id")
    private UnidadeOperacional unidadeOperacional;
    @ManyToOne(optional = false)
    @JoinColumn(name = "responsabilidadeOperacional_id")
    private ResponsabilidadeOperacional responsabilidadeOperacional;
    private Integer minimo;
    private Integer maximo;

    public UnidadeOperacional getUnidadeOperacional() {
        return unidadeOperacional;
    }

    public void setUnidadeOperacional(UnidadeOperacional unidadeOperacional) {
        this.unidadeOperacional = unidadeOperacional;
    }

    public ResponsabilidadeOperacional getResponsabilidadeOperacional() {
        return responsabilidadeOperacional;
    }

    public void setResponsabilidadeOperacional(ResponsabilidadeOperacional responsabilidadeOperacional) {
        this.responsabilidadeOperacional = responsabilidadeOperacional;
    }

    public Integer getMinimo() {
        return minimo;
    }

    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }

}
