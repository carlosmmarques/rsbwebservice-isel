package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * UnidadeOperacional - Description
 * Created on 03/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 */
@Entity
public class UnidadeOperacional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
