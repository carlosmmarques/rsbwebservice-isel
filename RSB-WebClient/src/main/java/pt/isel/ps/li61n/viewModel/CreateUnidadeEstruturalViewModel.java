package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.model.entities.TipoUnidadeEstrutural;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class CreateUnidadeEstruturalViewModel  extends BasicInsertViewModel{

    public final Collection<TipoUnidadeEstrutural> tiposUnidadesEstruturais;

    public final Collection<UnidadeEstrutural> unidadesEstruturaisMae;

    public CreateUnidadeEstruturalViewModel(
            Collection<TipoUnidadeEstrutural> tiposUnidadesEstruturais,
            Collection<UnidadeEstrutural> unidadeEstruturaisMae
    ){
        LinkedList< UnidadeEstrutural > ues = new LinkedList<>();

        if( unidadeEstruturaisMae != null ) {
            ues.addAll( unidadeEstruturaisMae );
        }
        ues.add( new UnidadeEstrutural( -1L, "Nenhuma" ) );

        this.unidadesEstruturaisMae = ues;
        this.tiposUnidadesEstruturais = tiposUnidadesEstruturais;


    }
}
