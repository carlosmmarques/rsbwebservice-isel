package pt.isel.ps.li61n.model.entities;

/**
 * CicloTurnoPK - Description
 * Created on 16/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

import java.io.Serializable;

/**
 * Chave composta
 */
public class CicloTurnoPK implements Serializable {

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