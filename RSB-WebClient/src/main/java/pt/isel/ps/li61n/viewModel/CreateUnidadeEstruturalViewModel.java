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

    private Long tipoId;

    private Long unidadeEstruturalMaeId;

    public final Collection<TipoUnidadeEstrutural> tiposUnidadesEstruturais;

    public final Collection<UnidadeEstrutural> unidadesEstruturaisMae;

    public CreateUnidadeEstruturalViewModel() {
        this.unidadesEstruturaisMae = new LinkedList<>();
        this.tiposUnidadesEstruturais = new LinkedList<>();
    }

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

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public Long getUnidadeEstruturalMaeId() {
        return unidadeEstruturalMaeId;
    }

    public void setUnidadeEstruturalMaeId(Long unidadeEstruturalMaeId) {
        this.unidadeEstruturalMaeId = unidadeEstruturalMaeId;
    }
}
