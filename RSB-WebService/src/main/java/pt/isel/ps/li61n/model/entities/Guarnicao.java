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

    /**
     * @return unidade operacional a guarnecer.
     */
    public UnidadeOperacional getUnidadeOperacional() {
        return unidadeOperacional;
    }

    /**
     * @param unidadeOperacional unidade operacional a guarnecer.
     */
    public void setUnidadeOperacional(UnidadeOperacional unidadeOperacional) {
        this.unidadeOperacional = unidadeOperacional;
    }

    /**
     * @return responsabilidade operacional dos elementos.
     */
    public ResponsabilidadeOperacional getResponsabilidadeOperacional() {
        return responsabilidadeOperacional;
    }

    /**
     * @param responsabilidadeOperacional responsabilidade operacional dos elementos.
     */
    public void setResponsabilidadeOperacional(ResponsabilidadeOperacional responsabilidadeOperacional) {
        this.responsabilidadeOperacional = responsabilidadeOperacional;
    }

    /**
     * @return número mínimo de elementos com a responsabilidade operacional determinada.
     */
    public Integer getMinimo() {
        return minimo;
    }

    /**
     * @param minimo número mínimo de elementos com a responsabilidade operacional determinada.
     */
    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }

    /**
     * @return número máximo de elementos com a responsabilidade operacional determinada.
     */
    public Integer getMaximo() {
        return maximo;
    }

    /**
     * @param maximo número máximo de elementos com a responsabilidade operacional determinada.
     */
    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }
}
