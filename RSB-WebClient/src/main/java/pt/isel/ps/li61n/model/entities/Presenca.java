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
    private Periodo periodo;

    // Fk Elemento -> Obter IdInterno
    //private long elementoId;
    private Elemento elemento;

    // FK TipoPresenca
    private String tipoPresencaId;

    // FK pessoal -> obter IdInterno;
    private Elemento elementoReforcoReforcado;

    // FK pessoal -> obter IdInterno;
    //private String elementoReforcadoId;

    // se é reforco não é reforcado. Se for null, não tem .
    private Boolean reforcoNaoReforcado;

    // Fk instalacao onde foi efectuado a presença
    private Long instalacaoId;
    private Instalacao instalacao;

    // Fk PostoFuncional -> obter designacao
    private PostoFuncional postoFuncional;

    // FK Turno -> obter designacao
    private Long turnoId;
    private Turno turno;


    ////////////////////////////////////////////
    //
    // getters & setters
    //
    /////////////////////////////////////


    public Turno getTurno() {
        return turno;
    }

    public void setTurno( Turno turno ) {
        this.turno = turno;
    }

    public Instalacao getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
    }

    public void setElementoReforcoReforcado(Elemento elementoReforcoReforcado ) {
        this.elementoReforcoReforcado = elementoReforcoReforcado;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Elemento getElementoReforcoReforcado() {
        return elementoReforcoReforcado;
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

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
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
    public Elemento getResourceWithCache() {
        return elemento;
    }

    public void setElemento( Elemento elemento ) {
        this.elemento = elemento;
    }
    */

    @Override
    public String toString(){
        return this.getData().toString() +  " - "
                + this.horaInicio.toString() + " - "
                + this.getTurno().toString();
    }

    public Elemento getElementoReforcoReforcadoId() {
        return elementoReforcoReforcado;
    }
}