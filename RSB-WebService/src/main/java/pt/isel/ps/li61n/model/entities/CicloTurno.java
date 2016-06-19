package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * CicloTurno - Representa o Ciclo de Turno. Esta classe não extende de RsbEntidadeAbstracta, uma vez que a chave é
 * composta de um Id do Ciclo e um Id do periodo do ciclo (Um Ciclo é composto por vários periodos)

 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
@IdClass(value = CicloTurno.CicloTurnoPK.class) //A chave é composta e representada pela classe CicloTurnoPK
public class CicloTurno{

    @Id
    @ManyToOne
    @JoinColumn(name = "algoritmoCalculoTurno_id")
    private AlgoritmoCalculoTurno algoritmoCalculoTurno;
    @Id
    private Integer ordemPeriodoCiclo;
    private Boolean periodoDescanso;
    private Float numHoras;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public CicloTurno() {
    }

    /**
     * @return algoritmo de calculo de turno a que diz respeito este periodo de ciclo.
     */
    public AlgoritmoCalculoTurno getAlgoritmoCalculoTurno() {
        return algoritmoCalculoTurno;
    }

    /**
     * @param algoritmoCalculoTurno algoritmo de calculo de turno a que diz respeito este periodo de ciclo.
     */
    public void setAlgoritmoCalculoTurno(AlgoritmoCalculoTurno algoritmoCalculoTurno) {
        this.algoritmoCalculoTurno = algoritmoCalculoTurno;
    }

    /**
     * @return identificador da ordem do sub-periodo que compõe o ciclo (reinicia para cada novo ciclo).
     */
    public Integer getOrdemPeriodoCiclo() {
        return ordemPeriodoCiclo;
    }

    /**
     * @param ordemPeriodoCiclo identificador da ordem do sub-periodo que compõe o ciclo (reinicia para cada novo ciclo).
     */
    public void setOrdemPeriodoCiclo(Integer ordemPeriodoCiclo) {
        this.ordemPeriodoCiclo = ordemPeriodoCiclo;
    }

    /**
     * @return verdadeiro se o sub-periodo é de de descanso, falso se for um periodo de trabalho.
     */
    public Boolean getPeriodoDescanso() {
        return periodoDescanso;
    }

    /**
     * @param periodoDescanso verdadeiro se o sub-periodo é de de descanso
     *                        falso se for um periodo de trabalho.
     */
    public void setPeriodoDescanso(Boolean periodoDescanso) {
        this.periodoDescanso = periodoDescanso;
    }

    /**
     * @return número (fraccionário) de horas de que é composto o sub-periodo.
     */
    public Float getNumHoras() {
        return numHoras;
    }

    /**
     * @param numHoras número (fraccionário) de horas de que é composto o sub-periodo.
     */
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
        if (!(o instanceof CicloTurno)) return false;

        CicloTurno that = (CicloTurno) o;

        if (!algoritmoCalculoTurno.equals(that.algoritmoCalculoTurno)) return false;
        if (!ordemPeriodoCiclo.equals(that.ordemPeriodoCiclo)) return false;
        if (periodoDescanso != null ? !periodoDescanso.equals(that.periodoDescanso) : that.periodoDescanso != null)
            return false;
        return numHoras.equals(that.numHoras);

    }

    /**
     * hashCode - Igualmente e relação a equals
     * @return hashCode da instancia
     */
    @Override
    public int hashCode() {
        int result = algoritmoCalculoTurno.hashCode();
        result = 31 * result + ordemPeriodoCiclo.hashCode();
        result = 31 * result + (periodoDescanso != null ? periodoDescanso.hashCode() : 0);
        result = 31 * result + numHoras.hashCode();
        return result;
    }

    /**
     * Chave composta
     */
    public static class CicloTurnoPK implements Serializable {

        private Integer ordemPeriodoCiclo;
        private AlgoritmoCalculoTurno algoritmoCalculoTurno;

        /**
         * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
         */
        public CicloTurnoPK() {
        }

        /**
         * @return ordem do periodo do ciclo
         */
        public Integer getOrdemPeriodoCiclo() {
            return ordemPeriodoCiclo;
        }

        /**
         * @param ordemPeriodoCiclo ordem do periodo do ciclo
         */
        public void setOrdemPeriodoCiclo(Integer ordemPeriodoCiclo) {
            this.ordemPeriodoCiclo = ordemPeriodoCiclo;
        }

        /**
         * @return algortimo de Calculo do Turno
         */
        public AlgoritmoCalculoTurno getAlgoritmoCalculoTurno() {
            return algoritmoCalculoTurno;
        }

        /**
         * @param algoritmoCalculoTurno algoritmo de Calculo do Turno
         */
        public void setAlgoritmoCalculoTurno(AlgoritmoCalculoTurno algoritmoCalculoTurno) {
            this.algoritmoCalculoTurno = algoritmoCalculoTurno;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CicloTurnoPK)) return false;

            CicloTurnoPK that = (CicloTurnoPK) o;

            if (!ordemPeriodoCiclo.equals(that.ordemPeriodoCiclo)) return false;
            return algoritmoCalculoTurno.equals(that.algoritmoCalculoTurno);

        }

        @Override
        public int hashCode() {
            int result = ordemPeriodoCiclo.hashCode();
            result = 31 * result + algoritmoCalculoTurno.hashCode();
            return result;
        }
    }
}
