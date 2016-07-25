package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * AlgoritmoCalculoTurno - Algoritmo de Cálculo do Turno. O Algoritmo é algo que define a sequencia de periodos
 * de descanso ou trabalho e respectivas durações que compõem o que se designa por Ciclo.
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class AlgoritmoCalculoTurno extends EntidadeAbstractaComIdentificador {

    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    @Column(length = 64)
    private String designacao;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    @Column(length = 255)
    private String descricao;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Boolean servicoPermanente;
    @OneToMany(mappedBy = "algoritmoCalculoTurno")
    private Set<PeriodoCicloTurno> ciclosTurno;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public AlgoritmoCalculoTurno() {
    }

    /**
     * @return designação do algoritmo de calculo de turno (texto curto)
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * @param designacao designação do algoritmo de calculo de turno (texto curto).
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * @return descrição do algoritmo de calculo de turno.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao descrição do algoritmo de calculo de turno.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Identifica se se trata de um algoritmo de cálculo para turnos de garantia de serviço permanente
     * @return true se o algoritmo é de serviço permanente ou false caso contrário
     */
    public Boolean getServicoPermanente() {
        return servicoPermanente;
    }

    /**
     * Define se se trata de um algoritmo de cálculo para turnos de garantia de serviço permanente
     * @param servicoPermanente true se o algoritmo é de serviço permanente
     *                          false caso contrário
     */
    public void setServicoPermanente(Boolean servicoPermanente) {
        this.servicoPermanente = servicoPermanente;
    }

    /**
     * @return periodos de ciclos de turno que definem este algoritmo de turno.
     */
    public Set<PeriodoCicloTurno> getCiclos() {
        return ciclosTurno;
    }

    /**
     * @param ciclosTurno periodos de ciclos de turno que definem este algoritmo de turno.
     */
    public void setCiclos(Set<PeriodoCicloTurno> ciclosTurno) {
        this.ciclosTurno = ciclosTurno;
    }
}
