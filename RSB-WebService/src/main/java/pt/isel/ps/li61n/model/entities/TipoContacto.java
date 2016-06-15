package pt.isel.ps.li61n.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * TipoContacto - Description
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class TipoContacto {
    @OneToMany(mappedBy = "tipoContacto")
    private List<Contacto> contactos;
    @Id
    @Column(unique = true)
    private String designação;


    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public String getDesignação() {
        return designação;
    }

    public void setDesignação(String designação) {
        this.designação = designação;
    }
}
