package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Turno - Turno de trabalho a que os elementos podem ser destacados
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Turno extends EntidadeAbstractaComIdentificador {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String designacao;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Date dtInicioCiclo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Time hrInicioCiclo;
    @ManyToOne
    @JoinColumn(name = "algoritmoCalculoTurno_id")
    private AlgoritmoCalculoTurno algoritmoCalculoTurno;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public Turno() {
    }

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
    public Time getHrInicioCiclo() {
        return hrInicioCiclo;
    }

    /**
     * @param hrInicioCiclo hora de inicio do ciclo.
     */
    public void setHrInicioCiclo(Time hrInicioCiclo) {
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
