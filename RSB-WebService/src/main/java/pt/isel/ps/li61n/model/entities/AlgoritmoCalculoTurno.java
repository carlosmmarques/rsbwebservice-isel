package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * AlgoritmoCalculoTurno - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class AlgoritmoCalculoTurno extends RsbAbstractEntity{
    private String designacao;
    private String descricao;
    private Boolean servicoPermanente;
    @OneToMany(mappedBy = "algoritmoCalculoTurno")
    private Set<CicloTurno> ciclosTurno;

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getServicoPermanente() {
        return servicoPermanente;
    }

    public void setServicoPermanente(Boolean servicoPermanente) {
        this.servicoPermanente = servicoPermanente;
    }

    public Set<CicloTurno> getCiclos() {
        return ciclosTurno;
    }

    public void setCiclos(Set<CicloTurno> ciclosTurno) {
        this.ciclosTurno = ciclosTurno;
    }
}
