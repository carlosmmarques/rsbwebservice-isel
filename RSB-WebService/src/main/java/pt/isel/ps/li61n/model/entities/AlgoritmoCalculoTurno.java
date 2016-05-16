package pt.isel.ps.li61n.model.entities;

import javax.persistence.Entity;

/**
 * AlgoritmoCalculoTurno - Algoritmo de Cálculo do Turno. O Algoritmo é algo que define a sequencia de periodos
 * de descanso ou trabalho e respectivas durações que compõem o que se designa por Ciclo.
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class AlgoritmoCalculoTurno extends RsbAbstractEntity{

    private String designacao;
    private String descricao;
    private Boolean servicoPermanente;
//    @OneToMany(mappedBy = "algoritmoCalculoTurno")
//    private Set<CicloTurno> ciclosTurno;

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

//    /**
//     * @return periodos de ciclos de turno que definem este algoritmo de turno.
//     */
//    public Set<CicloTurno> getCiclos() {
//        return ciclosTurno;
//    }
//
//    /**
//     * @param ciclosTurno periodos de ciclos de turno que definem este algoritmo de turno.
//     */
//    public void setCiclos(Set<CicloTurno> ciclosTurno) {
//        this.ciclosTurno = ciclosTurno;
//    }
}
