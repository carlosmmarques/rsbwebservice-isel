package pt.isel.ps.li61n.controller;

/**
 * Representation - Esta classe contem as definições de interfaces que serão utilizadas para anotar com @JsonView as
 * propriedades que serão serializadas pelo Jackson quando chamado a obter uma representação em JSON para colocar
 * na resposta.
 * Created on 16/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Representation {
    /**
     * Propriedades anotadas com @JSonView(Representation.*.class) serão respeitadas pelos controladores cujos metodos se
     * encontrem anotados da mesma forma.
     *
     * Isto permite disponibilizar representações com vários níves de detalhe das entidades
     */
    public interface Summary {}

    public interface Normal{}

    public interface Verbose {}

}
