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
    private int elementoId;

    // FK TipoPresenca
    private String TipoPresencaId;

    // FK pessoal -> obter IdInterno;
    private Long elementoReforcoReforcadoId;

    // FK pessoal -> obter IdInterno;
    //private String elementoReforcadoId;

    // se é reforco não é reforcado
    private Boolean reforcoNaoReforcado;

    // Fk instalacao onde foi efectuado a presença
    private Long instalacaoId;

    // Fk PostoFuncional -> obter designacao
    private Long postoFuncionalId;

    // FK Turno -> obter designacao
    private Long turnoId;


    ////////////////////////////////////////////
    //
    // getters & setters
    //
    /////////////////////////////////////


    public void setElementoReforcoReforcadoId(Long elementoReforcoReforcadoId) {
        this.elementoReforcoReforcadoId = elementoReforcoReforcadoId;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Long periodoId) {
        this.periodoId = periodoId;
    }

    public String getTipoPresencaId() {
        return TipoPresencaId;
    }

    public void setTipoPresencaId(String tipoPresencaId) {
        TipoPresencaId = tipoPresencaId;
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

    public Long getPostoFuncionalId() {
        return postoFuncionalId;
    }

    public void setPostoFuncionalId(Long postoFuncionalId) {
        this.postoFuncionalId = postoFuncionalId;
    }

    public Long getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }

    public int getElementoId() {
        return elementoId;
    }

    public void setElementoId(int elementoId) {
        this.elementoId = elementoId;
    }

    public Boolean getReforcoNaoReforcado() {
        return reforcoNaoReforcado;
    }

    public void setReforcoNaoReforcado(Boolean reforcoNaoReforcado) {
        this.reforcoNaoReforcado = reforcoNaoReforcado;
    }
}