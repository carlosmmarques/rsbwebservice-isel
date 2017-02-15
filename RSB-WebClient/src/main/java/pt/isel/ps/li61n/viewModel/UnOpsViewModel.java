package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.model.entities.Guarnicao;
import pt.isel.ps.li61n.model.entities.UnidadeOperacional;

import java.util.Collection;

/**
 * Created on 15/02/2017.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnOpsViewModel {

    public final boolean temGuarnicao;
    public final Collection< Guarnicao > guarnicao;

    // Unidade Operacional
    public final String designacao;
    public final String tipo;
    public final String instalacao;
    public final String operacional;

    //public final UnidadeOperacional uo;

    public UnOpsViewModel( UnidadeOperacional uo, Collection< Guarnicao > guarnicao ) {
        this.designacao = uo.getDesignacao();
        this.tipo = uo.getTipo().getDescricao();
        this.instalacao = uo.getInstalacao().getDesignacao();
        this.operacional = uo.getOperacional() ? "Sim" : "Não";

        this.guarnicao = guarnicao;
        this.temGuarnicao =  !( guarnicao == null || guarnicao.isEmpty() );
    }
}
