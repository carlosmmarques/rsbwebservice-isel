package pt.isel.ps.li61n.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Presenca - Description
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Presenca extends RsbAbstractEntity{

    @Column(nullable = false)
    private Date data;
    @Column(nullable = false)
    private Date horaInicio;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Float getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(Float numHoras) {
        this.numHoras = numHoras;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Pessoal getPessoal() {
        return pessoal;
    }

    public void setPessoal(Pessoal pessoal) {
        this.pessoal = pessoal;
    }

    public PostoFuncional getPostoFuncionalEfectivo() {
        return postoFuncionalEfectivo;
    }

    public void setPostoFuncionalEfectivo(PostoFuncional postoFuncionalEfectivo) {
        this.postoFuncionalEfectivo = postoFuncionalEfectivo;
    }

    public Instalacao getInstalacaoEfectiva() {
        return instalacaoEfectiva;
    }

    public void setInstalacaoEfectiva(Instalacao instalacaoEfectiva) {
        this.instalacaoEfectiva = instalacaoEfectiva;
    }

    public Turno getTurnoEfectivo() {
        return turnoEfectivo;
    }

    public void setTurnoEfectivo(Turno turnoEfectivo) {
        this.turnoEfectivo = turnoEfectivo;
    }

    public TipoPresenca getTipoPresencaEfectiva() {
        return tipoPresencaEfectiva;
    }

    public void setTipoPresencaEfectiva(TipoPresenca tipoPresencaEfectiva) {
        this.tipoPresencaEfectiva = tipoPresencaEfectiva;
    }

    public Pessoal getElementoReforco() {
        return elementoReforco;
    }

    public void setElementoReforco(Pessoal elementoReforco) {
        this.elementoReforco = elementoReforco;
    }

    public Pessoal getElementoReforcado() {
        return elementoReforcado;
    }

    public void setElementoReforcado(Pessoal elementoReforcado) {
        this.elementoReforcado = elementoReforcado;
    }
}
