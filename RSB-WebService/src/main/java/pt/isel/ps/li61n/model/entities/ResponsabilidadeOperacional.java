package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ResponsabilidadeOperacional - Description
 * Created on 03/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 */
@Entity
public class ResponsabilidadeOperacional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
