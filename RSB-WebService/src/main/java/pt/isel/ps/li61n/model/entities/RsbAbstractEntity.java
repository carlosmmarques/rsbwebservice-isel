package pt.isel.ps.li61n.model.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * RsbAbstractEntity - Atributos base das entidades da aplicação.
 * Created on 05/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

@MappedSuperclass
public abstract class RsbAbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * @return Identificador da entidade.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id Identificador da entidade.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
