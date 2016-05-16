package pt.isel.ps.li61n.model.entities;

/**
 * View - Esta classe contem as definições de interfaces que serão utilizadas para anotar com @JsonView as
 * propriedades que serão serializadas pelo Jackson quando chamado a obter uma representação em JSON para colocar
 * na resposta.
 * Created on 16/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class View {
    /**
     * Propriedades anotadas com @JSonView(View.Summary.class) serão respeitadas pelos controladores cujos metodos se
     * encontrem anotados da mesma forma.
     *
     * Isto permite disponibilizar representações sucintas das entidades em listas com grandes quantidades de dados
     */
    public interface Summary{}
}
