package pt.isel.ps.li61n.model.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Pessoal - Elementos do Pessoal do Corpo de Bombeiros
 * Created on 03/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
@Entity
public class Pessoal extends RsbAbstractEntity{
    @Column(nullable = false)
    private String idInterno;
    @Column(nullable = false)
    private String Matricula;
    @Column(unique = true, nullable = false)
    private String numMecanografico;
    @Column(nullable = false)
    private String abreviatura;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Date dataNascimento;
    @Column(nullable = false)
    private String telefone1;
    private String telefone2;
    private String eMail;
    private String nif;
    private Date dataIngresso;
    @Enumerated(EnumType.STRING)
    private TipoDocIdentificacao docIdentificacao;
    private Float factorElegibilidade;
    @ManyToOne
    @JoinColumn(name = "postoFuncional_id")
    private PostoFuncional postoFuncional;
    @ManyToOne
    @JoinColumn(name = "tipoPresenca_id")
    private TipoPresenca tipoPresenca;
    @ManyToOne
    @JoinColumn(name = "turno_id")
    private Turno turno;
    @ManyToOne
    @JoinColumn(name = "instalacao_id")
    private Instalacao instalacao;

    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public String getNumMecanografico() {
        return numMecanografico;
    }

    public void setNumMecanografico(String numMecanografico) {
        this.numMecanografico = numMecanografico;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public TipoDocIdentificacao getDocIdentificacao() {
        return docIdentificacao;
    }

    public void setDocIdentificacao(TipoDocIdentificacao docIdentificacao) {
        this.docIdentificacao = docIdentificacao;
    }

    public Float getFactorElegibilidade() {
        return factorElegibilidade;
    }

    public void setFactorElegibilidade(Float factorElegibilidade) {
        this.factorElegibilidade = factorElegibilidade;
    }

    public PostoFuncional getPostoFuncional() {
        return postoFuncional;
    }

    public void setPostoFuncional(PostoFuncional postoFuncional) {
        this.postoFuncional = postoFuncional;
    }

    public TipoPresenca getTipoPresenca() {
        return tipoPresenca;
    }

    public void setTipoPresenca(TipoPresenca tipoPresenca) {
        this.tipoPresenca = tipoPresenca;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Instalacao getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(Instalacao instalacao) {
        this.instalacao = instalacao;
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
