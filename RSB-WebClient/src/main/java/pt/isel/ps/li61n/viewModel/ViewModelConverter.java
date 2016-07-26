package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.model.entities.Periodo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 18/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ViewModelConverter {

    public static Collection< PeriodoUI> convertPeriodos( Collection< Periodo > periodos ){
        Collection< PeriodoUI > periodosUI = new ArrayList<>( periodos.size() );

        for( Periodo p : periodos ){

            String dataInicio =  p.getDataInicio().toString();
            String dataFim = p.getDataFim().toString();

            PeriodoUI ui = new PeriodoUI(
                    p.getId()
                    ,dataInicio
                    ,dataFim
            );

            periodosUI.add( ui );
        }
        return periodosUI;
    }
}
