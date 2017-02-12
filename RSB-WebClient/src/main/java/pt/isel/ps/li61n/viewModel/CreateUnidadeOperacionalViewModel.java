package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.model.entities.Instalacao;
import pt.isel.ps.li61n.model.entities.TipoUnidadeOperacional;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created on 12/02/2017.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class CreateUnidadeOperacionalViewModel  extends BasicInsertViewModel {

    private Long tipoId;

    private Long instalacaoId;

    private boolean operacional;

    public final Collection< TipoUnidadeOperacional > tiposUnidadesOperacionais;

    public final Collection< Instalacao > instalacoes;

    public CreateUnidadeOperacionalViewModel() {

        this.tiposUnidadesOperacionais = new LinkedList<>();
        this.instalacoes = new LinkedList<>();
    }

    public CreateUnidadeOperacionalViewModel(
            Collection< TipoUnidadeOperacional > tiposUnidadesOperacionais
            ,Collection<Instalacao> instalacaos
    ) {
        this.tiposUnidadesOperacionais = tiposUnidadesOperacionais;
        this.instalacoes = instalacaos;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public Long getInstalacaoId() {
        return instalacaoId;
    }

    public void setInstalacaoId(Long instalacaoId) {
        this.instalacaoId = instalacaoId;
    }

    public boolean isOperacional() {
        return operacional;
    }

    public void setOperacional(boolean operacional) {
        this.operacional = operacional;
    }
}

/*



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


 */
