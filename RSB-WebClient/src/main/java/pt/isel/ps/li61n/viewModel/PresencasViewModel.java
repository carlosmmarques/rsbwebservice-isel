package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.model.entities.Presenca;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 25/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PresencasViewModel {

    public final Collection< DropDownElement > presencas;

    private Long presencaId;

    public PresencasViewModel(
            Collection<Presenca> presencas
    ){
        this.presencas = new ArrayList<>( presencas.size() );
        for( Presenca p : presencas ){
            String msg = String.format(
                    "Dia %s às %s?"
                    ,p.getData().toString()
                    ,p.getHoraInicio()
            );

            DropDownElement element = new DropDownElement(
                    p.getId().toString()
                    ,msg
            );
            this.presencas.add( element );
        }
    }

    public Long getPresencaId() {
        return presencaId;
    }

    public void setPresencaId(Long presencaId) {
        this.presencaId = presencaId;
    }
}
