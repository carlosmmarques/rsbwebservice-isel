package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.model.entities.Elemento;
import pt.isel.ps.li61n.model.entities.Presenca;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 18/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TrocaViewModel {

    public final Collection< DropDownElement > reforcos;

    private Long reforcoId;
    private Long presencaId;

    public TrocaViewModel(){
        reforcos = null;
    }

    public TrocaViewModel(
            Collection< Elemento > pessoal
    ){

        this.reforcos = new ArrayList<>( pessoal.size() );
        for( Elemento elemento : pessoal ){
            DropDownElement element = new DropDownElement(
                                                elemento.getId().toString()
                                                ,elemento.getIdInterno()
                                            );
            this.reforcos.add( element );
        }

    }

    public Long getReforcoId() {
        return reforcoId;
    }

    public void setReforcoId(Long reforcoId) {
        this.reforcoId = reforcoId;
    }

    public Long getPresencaId() {
        return presencaId;
    }

    public void setPresencaId(Long presencaId) {
        this.presencaId = presencaId;
    }
}
