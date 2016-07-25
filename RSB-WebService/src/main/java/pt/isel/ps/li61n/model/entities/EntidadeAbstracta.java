package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * EntidadeAbstracta - Description
 * Created on 14/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@MappedSuperclass
public abstract class EntidadeAbstracta {

    @Version
    private Long version;

    @Column(name = "eliminado", nullable = false, columnDefinition="BOOLEAN DEFAULT FALSE")
    @JsonView(ModeloDeRepresentacao.Verboso.class)
    private Boolean eliminado = false;

    /**
     * @return Estado da Entidade (Activo / Inactivo)
     */

    public Boolean getEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado Estado da Entidade (Activo / Inactivo)
     */
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

}
