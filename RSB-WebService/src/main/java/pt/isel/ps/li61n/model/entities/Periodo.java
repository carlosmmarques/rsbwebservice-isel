package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

/**
 * Periodo - Representação do Periodo
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Periodo extends RsbEntidadeAbstracta {

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Date dtInicio;
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Date dtFim;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public Periodo() {
    }

    /**
     * @return Data de inicio do Periodo.
     */
    public Date getDtInicio() {
        return dtInicio;
    }

    /**
     * @param dtInicio Data de inicio do Periodo.
     */
    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    /**
     * @return Data de fim do periodo.
     */
    public Date getDtFim() {
        return dtFim;
    }

    /**
     * @param dtFim Data de fim do periodo.
     */
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
