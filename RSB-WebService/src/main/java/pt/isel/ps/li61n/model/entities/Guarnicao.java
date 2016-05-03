package pt.isel.ps.li61n.model.entities;

import javax.persistence.ManyToOne;

/**
 * Guarnicao - Description
 * Created on 03/05/2016.
 *
 * @author
 * Carlos Marques - carlosmmarques@gmail.com
 * Tiago Venturinha - tventurinha@gmail.com
 */
public class Guarnicao {
    //TODO: Estudar a questão dos mapeamentos de chaves compostas usando JPA
    @ManyToOne
    private UnidadeOperacional unidadeOperacional;
    @ManyToOne
    private ResponsabilidadeOperacional responsabilidadeOperacional;
    private Integer minimo;
    private Integer maximo;
}
