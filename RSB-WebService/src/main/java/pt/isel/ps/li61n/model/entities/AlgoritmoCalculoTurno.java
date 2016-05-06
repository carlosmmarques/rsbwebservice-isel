package pt.isel.ps.li61n.model.entities;

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
public class AlgoritmoCalculoTurno extends RsbAbstractEntity{
    /**
     * A designação do Algoritmo de Calculo de Turno (12horas; 8horas; Horário Normal; etc.)
     */
    private String designacao;
    /**
     * Descrição do Algoritmo de Calculo de Turno.
     */
    private String descricao;
    /**
     * Identifica se se trata de um algoritmo de cálculo para turnos de garantia de serviço permanente
     */
    private Boolean servicoPermanente;
    /**
     * Os Ciclos de Turno que dizem respeito a este turno.
     */
    @OneToMany(mappedBy = "algoritmoCalculoTurno")
    private Set<CicloTurno> ciclosTurno;

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getServicoPermanente() {
        return servicoPermanente;
    }

    public void setServicoPermanente(Boolean servicoPermanente) {
        this.servicoPermanente = servicoPermanente;
    }

    public Set<CicloTurno> getCiclos() {
        return ciclosTurno;
    }

    public void setCiclos(Set<CicloTurno> ciclosTurno) {
        this.ciclosTurno = ciclosTurno;
    }
}
