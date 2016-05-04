package pt.isel.ps.li61n.model.entities;

import javax.persistence.ManyToOne;

/**
 * Guarnicao - Entidade que representa a dotação de meios às Unidades Operacionais, em termos das 
 * quantidades mínimas e máximas de representantes com capacidade de Responsabilidade Operacional.
 * 
 * Created on 03/05/2016.
 *
 * @author
 * Carlos Marques - carlosmmarques@gmail.com
 * Tiago Venturinha - tventurinha@gmail.com
 */
public class Guarnicao {
    @ManyToOne(optional = false)
    @JoinColumn(name = "unidadeOperacional_id")
    private UnidadeOperacional unidadeOperacional;
    @ManyToOne(optional = false)
    @JoinColumn(name = "responsabilidadeOperacional_id")
    private ResponsabilidadeOperacional responsabilidadeOperacional;
    private Integer minimo;
    private Integer maximo;
}
