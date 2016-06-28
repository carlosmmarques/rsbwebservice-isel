package pt.isel.ps.li61n.model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Presenca extends Identity< Long > {

    // Data do registo
    private LocalDate data;

    // hora do registo
    private LocalTime horaInicio;

    // número de horas que o registo representa
    private float numHoras; // TODO: Ser Float??

    // FK periodo
    private Long periodoId;

    // Fk Pessoal -> Obter IdInterno
    //private long elementoId;
    private Pessoal elemento;

    // FK TipoPresenca
    private String tipoPresencaId;

    // FK pessoal -> obter IdInterno;
    private Pessoal elementoReforcoReforcado;

    // FK pessoal -> obter IdInterno;
    //private String elementoReforcadoId;

    // se é reforco não é reforcado. Se for null, não tem .
    private Boolean reforcoNaoReforcado;

    // Fk instalacao onde foi efectuado a presença
    private Long instalacaoId;

    // Fk PostoFuncional -> obter designacao
    private PostoFuncional postoFuncional;

    // FK Turno -> obter designacao
    private Long turnoId;


    ////////////////////////////////////////////
    //
    // getters & setters
    //
    /////////////////////////////////////

    public void setElementoReforcoReforcado( Pessoal elementoReforcoReforcado ) {
        this.elementoReforcoReforcado = elementoReforcoReforcado;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Long periodoId) {
        this.periodoId = periodoId;
    }

    public String getTipoPresencaId() {
        return tipoPresencaId;
    }

    public void setTipoPresencaId( String tipoPresencaId ){
        this.tipoPresencaId = tipoPresencaId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public float getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(float numHoras) {
        this.numHoras = numHoras;
    }

    public Long getInstalacaoId() {
        return instalacaoId;
    }

    public void setInstalacaoId(Long instalacaoId) {
        this.instalacaoId = instalacaoId;
    }

    public PostoFuncional getPostoFuncional() {
        return this.postoFuncional;
    }

    public void setPostoFuncional( PostoFuncional postoFuncional ) {
        this.postoFuncional = postoFuncional;
    }

    public Long getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }

    public Pessoal getElemento() {
        return elemento;
    }

    public void setElemento(Pessoal elemento) {
        this.elemento = elemento;
    }

    public boolean isReforcoNaoReforcado() {
        return reforcoNaoReforcado;
    }

    public void setReforcoNaoReforcado(boolean reforcoNaoReforcado) {
        this.reforcoNaoReforcado = reforcoNaoReforcado;
    }

    public Boolean getReforcoNaoReforcado() {
        return reforcoNaoReforcado;
    }

    public void setReforcoNaoReforcado (Boolean reforcoNaoReforcado) {
        this.reforcoNaoReforcado = reforcoNaoReforcado;
    }

    /*
    public Pessoal getElemento() {
        return elemento;
    }

    public void setElemento( Pessoal elemento ) {
        this.elemento = elemento;
    }
    */

    public Pessoal getElementoReforcoReforcadoId() {
        return elementoReforcoReforcado;
    }
}