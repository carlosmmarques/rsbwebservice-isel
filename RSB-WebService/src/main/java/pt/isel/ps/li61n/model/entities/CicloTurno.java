package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * CicloTurno - Ciclo do Turno
 * Esta classe não extende de RsbAbstractEntity, uma vez que a chave é composta de um Id do Ciclo e um Id do
 * periodo do ciclo (Um Ciclo pode compreender vários periodos)

 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
@IdClass(CicloTurno.CicloTurnoId.class)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlgoritmoCalculoTurno getAlgoritmoCalculoTurno() {
        return algoritmoCalculoTurno;
    }

    public void setAlgoritmoCalculoTurno(AlgoritmoCalculoTurno algoritmoCalculoTurno) {
        this.algoritmoCalculoTurno = algoritmoCalculoTurno;
    }

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
    protected class CicloTurnoId implements Serializable{
        private Long id;
        private Integer numPeriodoCiclo;

        public CicloTurnoId(Long id, Integer numPeriodoCiclo) {
            this.id = id;
            this.numPeriodoCiclo = numPeriodoCiclo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CicloTurnoId that = (CicloTurnoId) o;

            return (id.equals(that.id)) && numPeriodoCiclo.equals(that.numPeriodoCiclo);
        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + numPeriodoCiclo.hashCode();
            return result;
        }
    }
}
