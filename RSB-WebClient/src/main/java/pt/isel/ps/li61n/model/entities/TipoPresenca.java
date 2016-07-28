package pt.isel.ps.li61n.model.entities;

/**
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class TipoPresenca extends Identity< String > {

    private String descricao;

    private String abreviatura;

    private boolean ausencia;

    private boolean reforco;

    // FK TipoPresenca
    private String tipoPresencaReforcoId;

    private TipoPresenca tipoPresencaReforco;


    public TipoPresenca() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public boolean isAusencia() {
        return ausencia;
    }

    public void setAusencia(boolean ausencia) {
        this.ausencia = ausencia;
    }

    public boolean isReforco() {
        return reforco;
    }

    public void setReforco(boolean reforco) {
        this.reforco = reforco;
    }

    public String getTipoPresencaReforcoId() {
        return tipoPresencaReforcoId;
    }

    public void setTipoPresencaReforcoId(String tipoPresencaReforcoId) {
        this.tipoPresencaReforcoId = tipoPresencaReforcoId;
    }

    public TipoPresenca getTipoPresencaReforco() {
        return tipoPresencaReforco;
    }

    public void setTipoPresencaReforco( TipoPresenca tipoPresencaReforco ) {
        this.tipoPresencaReforco = tipoPresencaReforco;
        this.tipoPresencaReforcoId = tipoPresencaReforco.getId();
    }
}
