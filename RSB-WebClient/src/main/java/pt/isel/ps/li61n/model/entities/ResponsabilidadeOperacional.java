package pt.isel.ps.li61n.model.entities;

/**
 * Created on 28/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ResponsabilidadeOperacional extends Identity<Long> {

    private TipoPresenca tipoPresenca;

    //
    //  Getters & Setters
    //

    public TipoPresenca getTipoPresenca() {
        return tipoPresenca;
    }

    public void setTipoPresenca(TipoPresenca tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }
}
