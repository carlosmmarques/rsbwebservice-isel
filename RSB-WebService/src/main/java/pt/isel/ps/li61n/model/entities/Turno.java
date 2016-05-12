package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Turno - Turno de trabalho a que os elementos podem ser destacados
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

    /**
     * @return designação do Turno.
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação do Turno.
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return data de inicio do ciclo
     */
    public Date getDtInicioCiclo() {
        return dtInicioCiclo;
    }

    /**
     * @param dtInicioCiclo data de inicio do ciclo
     */
    public void setDtInicioCiclo(Date dtInicioCiclo) {
        this.dtInicioCiclo = dtInicioCiclo;
    }

    /**
     * @return hora de inicio do ciclo.
     */
    public Date getHrInicioCiclo() {
        return hrInicioCiclo;
    }

    /**
     * @param hrInicioCiclo hora de inicio do ciclo.
     */
    public void setHrInicioCiclo(Date hrInicioCiclo) {
        this.hrInicioCiclo = hrInicioCiclo;
    }

    /**
     * @return algoritmo de calculo do turno
     */
    public AlgoritmoCalculoTurno getAlgoritmoCalculoTurno() {
        return algoritmoCalculoTurno;
    }

    /**
     * @param algoritmoCalculoTurno algoritmo de calculo do turno
     */
    public void setAlgoritmoCalculoTurno(AlgoritmoCalculoTurno algoritmoCalculoTurno) {
        this.algoritmoCalculoTurno = algoritmoCalculoTurno;
    }
}
