package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Contacto - Description
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
@IdClass(value = Contacto.Contacto_PK.class)
public class Contacto {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elemento_id")
    private ElementoDoPessoal elementoDoPessoal;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoContacto_id")
    private TipoContacto tipoContacto;
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String contacto;

    /**
     * @return Elemento do pessoal a que se refere o contacto
     */
    public ElementoDoPessoal getElementoDoPessoal() {
        return elementoDoPessoal;
    }

    /**
     * @param elementoDoPessoal Elemento do pessoal a que se refere o contacto
     */
    public void setElementoDoPessoal(ElementoDoPessoal elementoDoPessoal) {
        this.elementoDoPessoal = elementoDoPessoal;
    }

    /**
     * @return Tipo do contacto
     */
    public TipoContacto getTipoContacto() {
        return tipoContacto;
    }

    /**
     * @param tipoContacto Tipo do contacto
     */
    public void setTipoContacto(TipoContacto tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    /**
     * @return Valor do contacto
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * @param contacto Valor do contacto
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * Chave Composta
     */
    public class Contacto_PK implements Serializable{
        private ElementoDoPessoal elementoDoPessoal;
        private TipoContacto tipoContacto;

        /**
         * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
         */
        public Contacto_PK() {
        }

        public ElementoDoPessoal getElementoDoPessoal() {
            return elementoDoPessoal;
        }

        public void setElementoDoPessoal(ElementoDoPessoal elementoDoPessoal) {
            this.elementoDoPessoal = elementoDoPessoal;
        }

        public TipoContacto getTipoContacto() {
            return tipoContacto;
        }

        public void setTipoContacto(TipoContacto tipoContacto) {
            this.tipoContacto = tipoContacto;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
