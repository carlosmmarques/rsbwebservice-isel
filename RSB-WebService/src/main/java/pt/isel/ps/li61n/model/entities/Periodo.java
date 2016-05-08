package pt.isel.ps.li61n.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Periodo - Representação do Periodo
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Periodo extends RsbAbstractEntity{

    /**
     * Data de inicio do Periodo
     */
    @Column(nullable = false)
    private Date dtInicio;
    /**
     * Data de fim do periodo
     */
    @Column(nullable = false)
    private Date dtFim;

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Periodo periodo = (Periodo) o;

        if (!dtInicio.equals(periodo.dtInicio)) return false;
        return dtFim.equals(periodo.dtFim);

    }

    @Override
    public int hashCode() {
        int result = dtInicio.hashCode();
        result = 31 * result + dtFim.hashCode();
        return result;
    }
}
