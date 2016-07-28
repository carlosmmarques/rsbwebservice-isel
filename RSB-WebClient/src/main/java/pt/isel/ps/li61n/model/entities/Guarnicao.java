package pt.isel.ps.li61n.model.entities;

/**
 * Created on 28/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Guarnicao extends Identity< Long > {

    private ResponsabilidadeOperacional responsabilidadeOperacional;

    private int minimo;

    private int maximo;

    private Long unidadeOperacionalId;

    //
    // Getters & Setters
    //

    public ResponsabilidadeOperacional getResponsabilidadeOperacional() {
        return responsabilidadeOperacional;
    }


    public void setResponsabilidadeOperacional(ResponsabilidadeOperacional responsabilidadeOperacional) {
        this.responsabilidadeOperacional = responsabilidadeOperacional;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public Long getUnidadeOperacionalId() {
        return unidadeOperacionalId;
    }

    public void setUnidadeOperacionalId(Long unidadeOperacionalId) {
        this.unidadeOperacionalId = unidadeOperacionalId;
    }
}
