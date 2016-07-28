package pt.isel.ps.li61n.viewModel;

/**
 * Created on 27/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class EscalaFormViewModel {

    // FK
    private Long instalacaoId;

    // FK
    private Long turnoId;

    private String data;

    //
    // Getters & Setters
    //

    public Long getInstalacaoId() {
        return instalacaoId;
    }

    public void setInstalacaoId(Long instalacaoId) {
        this.instalacaoId = instalacaoId;
    }

    public Long getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
