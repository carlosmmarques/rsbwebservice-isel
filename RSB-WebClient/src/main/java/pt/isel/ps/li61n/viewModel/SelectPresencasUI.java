package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.controller.UrlGenerator;
import pt.isel.ps.li61n.model.entities.Periodo;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 15/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class SelectPresencasUI {

    public final Collection< PeriodoUI > periodosUI;

    public final Collection< UnidadeEstruturalUI > unidadeEstruturaisUIs;

    private Long periodoId;
    private Long unidadeEstruturalId;

    public SelectPresencasUI(
                Collection< Periodo> periodos
                ,Collection<UnidadeEstrutural > unidadeEstruturais
    ){

        periodosUI = new ArrayList<>( periodos.size() );

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

        unidadeEstruturaisUIs = new ArrayList<>( unidadeEstruturais.size() );
        for( UnidadeEstrutural ue : unidadeEstruturais ){
            UnidadeEstruturalUI ui = new UnidadeEstruturalUI(
                                                        ue.getId()
                                                        ,ue.getDesignacao()
            );
            unidadeEstruturaisUIs.add( ui );
        }
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Long periodoId) {
        this.periodoId = periodoId;
    }

    public Long getUnidadeEstruturalId() {
        return unidadeEstruturalId;
    }

    public void setUnidadeEstruturalId(Long unidadeEstruturalId) {
        this.unidadeEstruturalId = unidadeEstruturalId;
    }
}
