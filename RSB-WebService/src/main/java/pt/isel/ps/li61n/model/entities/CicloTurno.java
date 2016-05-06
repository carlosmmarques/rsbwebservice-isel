package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * CicloTurno - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
@IdClass(CicloTurno.ClicloTurnoId.class)
public class CicloTurno{
    @Id
    private Long id;
    @Id
    private Integer numPeriodoCiclo;
    private Boolean periodoDescanso;
    private Float numHoras;
    @ManyToOne
    @JoinColumn(name = "algoritmoCalculoTurno_id")
    private AlgoritmoCalculoTurno algoritmoCalculoTurno;

    public Integer getNumPeriodoCiclo() {
        return numPeriodoCiclo;
    }

    public void setNumPeriodoCiclo(Integer numPeriodoCiclo) {
        this.numPeriodoCiclo = numPeriodoCiclo;
    }

    public Boolean getPeriodoDescanso() {
        return periodoDescanso;
    }

    public void setPeriodoDescanso(Boolean periodoDescanso) {
        this.periodoDescanso = periodoDescanso;
    }

    public Float getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(Float numHoras) {
        this.numHoras = numHoras;
    }

    /**
     * Chave composta
     */
    protected class ClicloTurnoId implements Serializable{
        private Long id;
        private Integer numPeriodoCiclo;

        public ClicloTurnoId(Long id, Integer numPeriodoCiclo) {
            this.id = id;
            this.numPeriodoCiclo = numPeriodoCiclo;
        }
    }
}
