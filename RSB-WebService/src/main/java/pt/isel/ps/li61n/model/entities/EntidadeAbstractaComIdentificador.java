package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;

/**
 * EntidadeAbstractaComIdentificador - Atributos base das entidades da aplicação.
 * Created on 05/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

@MappedSuperclass
public abstract class EntidadeAbstractaComIdentificador extends EntidadeAbstracta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
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
