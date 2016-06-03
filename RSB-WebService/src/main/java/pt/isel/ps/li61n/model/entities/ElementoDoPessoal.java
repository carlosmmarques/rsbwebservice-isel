package pt.isel.ps.li61n.model.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import pt.isel.ps.li61n.controller.Representation;

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
public class ElementoDoPessoal extends RsbAbstractEntity{

    @Column(nullable = true)
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String idInterno;
    @Column(nullable = false)
    @JsonView({Representation.Normal.class})
    private String Matricula;
    @Column(unique = true, nullable = false)
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String numMecanografico;
    @Column(nullable = false)
    @JsonView({Representation.Normal.class})
    private String abreviatura;
    @Column(nullable = false)
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private String nome;
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({Representation.Normal.class})
    private Date dataNascimento;
    @Column(nullable = false)
    @JsonView({Representation.Normal.class})
    private String telefone1;
    @JsonView({Representation.Normal.class})
    private String telefone2;
    @JsonView({Representation.Normal.class})
    private String eMail;
    @JsonView({Representation.Normal.class})
    private String nif;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView({Representation.Summary.class, Representation.Normal.class})
    private Date dataIngresso;
    @Enumerated(EnumType.STRING)
    @JsonView({Representation.Normal.class})
    private TipoDocIdentificacao tipoDocIdentificacao;
    @JsonView({Representation.Normal.class})
    private String numDocIdentificacao;
    @JsonView({Representation.Normal.class})
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
    private List<RegistoFormacao> formacoes;

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
    public List<RegistoFormacao> getFormacoes() {
        return formacoes;
    }

    /**
     * @param formacoes Lista de formações do elemento
     */
    public void setFormacoes(List<RegistoFormacao> formacoes) {
        this.formacoes = formacoes;
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
