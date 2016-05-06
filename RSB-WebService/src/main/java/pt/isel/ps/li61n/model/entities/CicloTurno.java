package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * CicloTurno - Representa o Ciclo de Turno. Esta classe não extende de RsbAbstractEntity, uma vez que a chave é
 * composta de um Id do Ciclo e um Id do periodo do ciclo (Um Ciclo é composto por vários periodos)

 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
@IdClass(CicloTurno.CicloTurnoId.class) //A chave é composta e representada pela classe CicloTurnoId
public class CicloTurno{

    /**
     * O identificador do Ciclo
     */
    @Id
    private Long id;
    /**
     * O identificador do sub-periodo que compõe o ciclo (reinicia para cada novo ciclo)
     */
    @Id
    private Integer numPeriodoCiclo;
    /**
     * Identifica o sub-periodo como sendo de descanso (caso contrário será um periodo de trabalho)
     */
    private Boolean periodoDescanso;
    /**
     * O número (fraccionário) de horas de que é composto o sub-periodo
     */
    private Float numHoras;
    /**
     * O Algoritmo de Calculo de Turno a que diz respeito este Ciclo
     */
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
     * Equals - Necessário implementar equals, uma vez que do lado do Algoritmo de Calculo do Turno temos um método que
     * devolve um Set<CicloTurno>, não suportando valores repetidos, pelo que é importante oferecer uma implementação de
     * equals
     * @param o - Objecto a comparar
     * @return true se é "equal" a esta instancia.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CicloTurno that = (CicloTurno) o;

        if (!id.equals(that.id)) return false;
        if (!numPeriodoCiclo.equals(that.numPeriodoCiclo)) return false;
        if (periodoDescanso != null ? !periodoDescanso.equals(that.periodoDescanso) : that.periodoDescanso != null)
            return false;
        if (!numHoras.equals(that.numHoras)) return false;
        return algoritmoCalculoTurno.equals(that.algoritmoCalculoTurno);

    }

    /**
     * hashCode - Igualmente e relação a equals
     * @return hashCode da instancia
     */
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numPeriodoCiclo.hashCode();
        result = 31 * result + (periodoDescanso != null ? periodoDescanso.hashCode() : 0);
        result = 31 * result + numHoras.hashCode();
        result = 31 * result + algoritmoCalculoTurno.hashCode();
        return result;
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
