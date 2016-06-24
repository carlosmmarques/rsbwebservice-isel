package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import pt.isel.ps.li61n.controller.ModeloDeRepresentacao;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * ElementoDoPessoal - Elementos do ElementoDoPessoal do Corpo de Bombeiros
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
@Table(name = "pessoal")
public class ElementoDoPessoal extends RsbEntidadeAbstracta {

    @Column(nullable = true)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String idInterno;
    @Column(nullable = false)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String Matricula;
    @Column(unique = true, nullable = false)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String numMecanografico;
    @Column(nullable = false)
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String abreviatura;
    @Column(nullable = false)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String nome;
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Date dataNascimento;
    @Column(nullable = false)
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String telefone1;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String telefone2;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String eMail;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String nif;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({ModeloDeRepresentacao.Sumario.class, ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Date dataIngresso;
    @Enumerated(EnumType.STRING)
    private TipoDocIdentificacao tipoDocIdentificacao;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private String numDocIdentificacao;
    @JsonView({ModeloDeRepresentacao.Normal.class, ModeloDeRepresentacao.Verboso.class})
    private Float factorElegibilidade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postoFuncional_id")
    private PostoFuncional postoFuncional;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoPresenca_id")
    private TipoPresenca tipoPresenca;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turno_id")
    private Turno turno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instalacao_id")
    private Instalacao instalacao;
    @OneToMany(mappedBy = "elementoDoPessoal")
    private List<AtribuicaoCategoria> atribuicõesDeCategoria;
    @OneToMany(mappedBy = "elementoDoPessoal")
    private List<RegistoFormacao> registosFormacao;
    // TODO - Mapear os contactos à tabela Contactos.
    @OneToMany(mappedBy = "elementoDoPessoal")
    private List<Contacto> contactos;

    /**
     * Constutor sem parametros com nível de acessibilidade "public" ou "protected". Requerimento da Framework JPA 2.0+.
     */
    public ElementoDoPessoal() {
    }

    /**
     * @return Identificador interno do elemento do pessoal
     */
    public String getIdInterno() {
        return idInterno;
    }

    /**
     * @param idInterno Identificador interno do elemento do pessoal
     */
    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }

    /**
     * @return Numero de Matricula do elemento do pessoal
     */
    public String getMatricula() {
        return Matricula;
    }

    /**
     * @param matricula Numero de Matricula do elemento do pessoal
     */
    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    /**
     * @return Número mecanográfico. Identificador unico global do elemento
     */
    public String getNumMecanografico() {
        return numMecanografico;
    }

    /**
     * @param numMecanografico Número mecanográfico. Identificador unico global do elemento
     */
    public void setNumMecanografico(String numMecanografico) {
        this.numMecanografico = numMecanografico;
    }

    /**
     * @return Abreviatura - Um nome curto para o elemento
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura Abreviatura - Um nome curto para o elemento
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @return Nome Completo do elemento do pessoal
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Nome Completo do elemento do pessoal
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Data de Nascimento do elemento do pessoal
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento Data de Nascimento do elemento do pessoal
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return número de telefone (obrigatório)
     */
    public String getTelefone1() {
        return telefone1;
    }

    /**
     * @param telefone1 número de telefone (obrigatório)
     */
    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    /**
     * @return numero de telefone facultativo
     */
    public String getTelefone2() {
        return telefone2;
    }

    /**
     * @param telefone2 numero de telefone facultativo
     */
    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    /**
     * @return Endereço de email do elemento
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @param eMail Endereço de email do elemento
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return Numero de Identificação Fiscal do Elemento
     */
    public String getNif() {
        return nif;
    }

    /**
     * @param nif Numero de Identificação Fiscal do Elemento
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * @return Data de Admissão do elemento no corpo de bombeiros
     */
    public Date getDataIngresso() {
        return dataIngresso;
    }

    /**
     * @param dataIngresso Data de Admissão do elemento no corpo de bombeiros
     */
    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    /**
     * @return Tipo de documento de Identificação - Bilhete de Identidade ou Cartão de Cidadão
     */
    public TipoDocIdentificacao getTipoDocIdentificacao() {
        return tipoDocIdentificacao;
    }

    /**
     * @param tipoDocIdentificacao Tipo de documento de Identificação - Bilhete de Identidade ou Cartão de Cidadão
     */
    public void setTipoDocIdentificacao(TipoDocIdentificacao tipoDocIdentificacao) {
        this.tipoDocIdentificacao = tipoDocIdentificacao;
    }

    /**
     * @return Numero do documento de Identificação
     */
    public String getNumDocIdentificacao() {
        return numDocIdentificacao;
    }

    /**
     * @param numDocIdentificacao Numero do documento de Identificação
     */
    public void setNumDocIdentificacao(String numDocIdentificacao) {
        this.numDocIdentificacao = numDocIdentificacao;
    }

    /**
     * @return Factor calculado para a atribuição de responsabilidades operacionais
     */
    public Float getFactorElegibilidade() {
        return factorElegibilidade;
    }

    /**
     * @param factorElegibilidade Factor calculado para a atribuição de responsabilidades operacionais
     */
    public void setFactorElegibilidade(Float factorElegibilidade) {
        this.factorElegibilidade = factorElegibilidade;
    }

    /**
     * @return Posto Funcional ocupado por omissão pelo elemento
     */
    public PostoFuncional getPostoFuncional() {
        return postoFuncional;
    }

    /**
     * @param postoFuncional Posto Funcional ocupado por omissão pelo elemento
     */
    public void setPostoFuncional(PostoFuncional postoFuncional) {
        this.postoFuncional = postoFuncional;
    }

    /**
     * @return tipo de ausencia, ou a função desempenhada em caso de presença
     */
    public TipoPresenca getTipoPresenca() {
        return tipoPresenca;
    }

    /**
     * @param tipoPresenca tipo de ausencia, ou a função desempenhada em caso de presença
     */
    public void setTipoPresenca(TipoPresenca tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }

    /**
     * @return Turno a que o elemento está atribuido por omissão
     */
    public Turno getTurno() {
        return turno;
    }

    /**
     * @param turno Turno a que o elemento está atribuido por omissão
     */
    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    /**
     * @return Instalação em que o elemento está instalado por omissão
     */
    public Instalacao getInstalacao() {
        return instalacao;
    }

    /**
     * @param instalacao Instalação em que o elemento está instalado por omissão
     */
    public void setInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
    }

    /**
     * @return Lista de atribuicõesDeCategoria do elemento
     */
    public List<AtribuicaoCategoria> getAtribuicõesDeCategoria() {
        return atribuicõesDeCategoria;
    }

    /**
     * @param atribuicõesDeCategoria Lista de atribuicõesDeCategoria do elemento
     */
    public void setAtribuicõesDeCategoria(List<AtribuicaoCategoria> atribuicõesDeCategoria) {
        this.atribuicõesDeCategoria = atribuicõesDeCategoria;
    }

    /**
     * @return Lista de formações do elemento
     */
    public List<RegistoFormacao> getRegistosFormacao() {
        return registosFormacao;
    }

    /**
     * @param registosFormacao Lista de formações do elemento
     */
    public void setRegistosFormacao(List<RegistoFormacao> registosFormacao) {
        this.registosFormacao = registosFormacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElementoDoPessoal)) return false;

        ElementoDoPessoal that = (ElementoDoPessoal) o;

        if (idInterno != null ? !idInterno.equals(that.idInterno) : that.idInterno != null) return false;
        if (!Matricula.equals(that.Matricula)) return false;
        if (!numMecanografico.equals(that.numMecanografico)) return false;
        if (!abreviatura.equals(that.abreviatura)) return false;
        if (!nome.equals(that.nome)) return false;
        if (!dataNascimento.equals(that.dataNascimento)) return false;
        if (!telefone1.equals(that.telefone1)) return false;
        if (telefone2 != null ? !telefone2.equals(that.telefone2) : that.telefone2 != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (nif != null ? !nif.equals(that.nif) : that.nif != null) return false;
        if (dataIngresso != null ? !dataIngresso.equals(that.dataIngresso) : that.dataIngresso != null) return false;
        if (tipoDocIdentificacao != that.tipoDocIdentificacao) return false;
        if (numDocIdentificacao != null ? !numDocIdentificacao.equals(that.numDocIdentificacao) : that.numDocIdentificacao != null)
            return false;
        if (factorElegibilidade != null ? !factorElegibilidade.equals(that.factorElegibilidade) : that.factorElegibilidade != null)
            return false;
        if (postoFuncional != null ? !postoFuncional.equals(that.postoFuncional) : that.postoFuncional != null)
            return false;
        if (tipoPresenca != null ? !tipoPresenca.equals(that.tipoPresenca) : that.tipoPresenca != null) return false;
        if (turno != null ? !turno.equals(that.turno) : that.turno != null) return false;
        if (instalacao != null ? !instalacao.equals(that.instalacao) : that.instalacao != null) return false;
        if (atribuicõesDeCategoria != null ? !atribuicõesDeCategoria.equals(that.atribuicõesDeCategoria) : that.atribuicõesDeCategoria != null)
            return false;
        if (registosFormacao != null ? !registosFormacao.equals(that.registosFormacao) : that.registosFormacao != null)
            return false;
        return contactos != null ? contactos.equals(that.contactos) : that.contactos == null;

    }

    @Override
    public int hashCode() {
        int result = idInterno != null ? idInterno.hashCode() : 0;
        result = 31 * result + Matricula.hashCode();
        result = 31 * result + numMecanografico.hashCode();
        result = 31 * result + abreviatura.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + dataNascimento.hashCode();
        result = 31 * result + telefone1.hashCode();
        result = 31 * result + (telefone2 != null ? telefone2.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (dataIngresso != null ? dataIngresso.hashCode() : 0);
        result = 31 * result + (tipoDocIdentificacao != null ? tipoDocIdentificacao.hashCode() : 0);
        result = 31 * result + (numDocIdentificacao != null ? numDocIdentificacao.hashCode() : 0);
        result = 31 * result + (factorElegibilidade != null ? factorElegibilidade.hashCode() : 0);
        result = 31 * result + (postoFuncional != null ? postoFuncional.hashCode() : 0);
        result = 31 * result + (tipoPresenca != null ? tipoPresenca.hashCode() : 0);
        result = 31 * result + (turno != null ? turno.hashCode() : 0);
        result = 31 * result + (instalacao != null ? instalacao.hashCode() : 0);
        result = 31 * result + (atribuicõesDeCategoria != null ? atribuicõesDeCategoria.hashCode() : 0);
        result = 31 * result + (registosFormacao != null ? registosFormacao.hashCode() : 0);
        result = 31 * result + (contactos != null ? contactos.hashCode() : 0);
        return result;
    }

    /**
     * TipoDocIdentificacao - Description
     * Created on 05/05/2016.
     *
     * @author Carlos Marques - carlosmmarques@gmail.com
     *         Tiago Venturinha - tventurinha@gmail.com
     */
    public enum TipoDocIdentificacao {
        BilheteIdentidade,
        CartaoCidadao
    }
}
