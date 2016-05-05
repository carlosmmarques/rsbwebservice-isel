package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Periodo - Description
 * Created on 03/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 */
@Entity
public class Periodo extends RsbAbstractEntity{

    private Date dtInicio;
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
}
