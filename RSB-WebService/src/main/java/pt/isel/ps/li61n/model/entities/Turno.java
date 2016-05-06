package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Turno - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Turno extends RsbAbstractEntity{
    private String designacao;
    private Date dtInicioCiclo;
    private Date hrInicioCiclo;
    @ManyToOne
    @JoinColumn(name = "algoritmoCalculoTurno_id")
    private AlgoritmoCalculoTurno algoritmoCalculoTurno;


    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public Date getDtInicioCiclo() {
        return dtInicioCiclo;
    }

    public void setDtInicioCiclo(Date dtInicioCiclo) {
        this.dtInicioCiclo = dtInicioCiclo;
    }

    public Date getHrInicioCiclo() {
        return hrInicioCiclo;
    }

    public void setHrInicioCiclo(Date hrInicioCiclo) {
        this.hrInicioCiclo = hrInicioCiclo;
    }

    public AlgoritmoCalculoTurno getAlgoritmoCalculoTurno() {
        return algoritmoCalculoTurno;
    }

    public void setAlgoritmoCalculoTurno(AlgoritmoCalculoTurno algoritmoCalculoTurno) {
        this.algoritmoCalculoTurno = algoritmoCalculoTurno;
    }
}
