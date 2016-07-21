package pt.isel.ps.li61n.controller;

/**
 * ModeloDeRepresentacao - Esta classe contem as definições de interfaces que serão utilizadas para anotar com @JsonView as
 * propriedades que serão serializadas pelo Jackson quando chamado a obter uma representação em JSON para colocar
 * na resposta.
 * Created on 16/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ModeloDeRepresentacao {
    /**
     * Propriedades anotadas com @JSonView(ModeloDeRepresentacao.*.class) serão respeitadas pelos controladores cujos metodos se
     * encontrem anotados da mesma forma.
     * <p>
     * Isto permite disponibilizar representações com vários níves de detalhe das entidades
     */
    public interface Sumario {
    }

    public interface Normal {
    }

    public interface Verboso {
    }

}
