package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;

/**
 * RsbEntidadeAbstracta - Atributos base das entidades da aplicação.
 * Created on 05/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

@MappedSuperclass
public abstract class RsbEntidadeAbstracta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Long id;
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
