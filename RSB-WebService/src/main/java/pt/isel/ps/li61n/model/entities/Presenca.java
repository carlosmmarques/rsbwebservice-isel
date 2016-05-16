package pt.isel.ps.li61n.model.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;

/**
 * Presenca - Caracterização da Presença.
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Presenca extends RsbAbstractEntity{

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date data;
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Time horaInicio;
    @Column(nullable = false)
    private Float numHoras;
    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;
    @ManyToOne
    @JoinColumn(name = "pessoal_id")
    private Pessoal pessoal;
    @ManyToOne
    @JoinColumn(name = "postoFuncionalEfectivo_id")
    private PostoFuncional postoFuncionalEfectivo;
    @ManyToOne
    @JoinColumn(name = "instalacaoEfectiva_id")
    private Instalacao instalacaoEfectiva;
    @ManyToOne
    @JoinColumn(name = "turnoEfectivo_id")
    private Turno turnoEfectivo;
    @ManyToOne
    @JoinColumn(name = "tipoPresencaEfectiva_id")
    private TipoPresenca tipoPresencaEfectiva;
    @ManyToOne
    @JoinColumn(name = "elementoReforco_id")
    private Pessoal elementoReforco;
    @ManyToOne
    @JoinColumn(name = "elementoReforcado_id")
    private Pessoal elementoReforcado;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public Presenca() {
    }

    /**
     * @return data da presença
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data data da presença
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return hora de inicio.
     */
    public Time getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio hora de inicio.
     */
    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return numero de horas da presença.
     */
    public Float getNumHoras() {
        return numHoras;
    }

    /**
     * @param numHoras numero de horas da presença.
     */
    public void setNumHoras(Float numHoras) {
        this.numHoras = numHoras;
    }

    /**
     * @return periodo em que se enquadra esta presença.
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo periodo em que se enquadra esta presença.
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * @return elemento do pessoal a que diz respeito esta presença.
     */
    public Pessoal getPessoal() {
        return pessoal;
    }

    /**
     * @param pessoal elemento do pessoal a que diz respeito esta presença.
     */
    public void setPessoal(Pessoal pessoal) {
        this.pessoal = pessoal;
    }

    /**
     * @return posto funcional efectivo ocupado pelo elemento do pessoal nesta presença
     */
    public PostoFuncional getPostoFuncionalEfectivo() {
        return postoFuncionalEfectivo;
    }

    /**
     * @param postoFuncionalEfectivo posto funcional efectivo ocupado pelo elemento do pessoal nesta presença
     */
    public void setPostoFuncionalEfectivo(PostoFuncional postoFuncionalEfectivo) {
        this.postoFuncionalEfectivo = postoFuncionalEfectivo;
    }

    /**
     * @return instalação em que o elemento do pessoal esteve efectivamente presente.
     */
    public Instalacao getInstalacaoEfectiva() {
        return instalacaoEfectiva;
    }

    /**
     * @param instalacaoEfectiva instalação em que o elemento do pessoal esteve efectivamente presente.
     */
    public void setInstalacaoEfectiva(Instalacao instalacaoEfectiva) {
        this.instalacaoEfectiva = instalacaoEfectiva;
    }

    /**
     * @return turno em que o elemento realizou efectivamente a presença.
     */
    public Turno getTurnoEfectivo() {
        return turnoEfectivo;
    }

    /**
     * @param turnoEfectivo turno em que o elemento realizou efectivamente a presença.
     */
    public void setTurnoEfectivo(Turno turnoEfectivo) {
        this.turnoEfectivo = turnoEfectivo;
    }

    /**
     * @return tipo efectivo da presença
     */
    public TipoPresenca getTipoPresencaEfectiva() {
        return tipoPresencaEfectiva;
    }

    /**
     * @param tipoPresencaEfectiva tipo efectivo da presença
     */
    public void setTipoPresencaEfectiva(TipoPresenca tipoPresencaEfectiva) {
        this.tipoPresencaEfectiva = tipoPresencaEfectiva;
    }

    /**
     * @return elemento do pessoal de reforço (em caso de ausencia).
     */
    public Pessoal getElementoReforco() {
        return elementoReforco;
    }

    /**
     * @param elementoReforco elemento do pessoal de reforço (em caso de ausencia).
     */
    public void setElementoReforco(Pessoal elementoReforco) {
        this.elementoReforco = elementoReforco;
    }

    /**
     * @return elemento que está a ser reforçado (no caso desta presença ser de reforço).
     */
    public Pessoal getElementoReforcado() {
        return elementoReforcado;
    }

    /**
     * @param elementoReforcado elemento que está a ser reforçado (no caso desta presença ser de reforço).
     */
    public void setElementoReforcado(Pessoal elementoReforcado) {
        this.elementoReforcado = elementoReforcado;
    }
}
