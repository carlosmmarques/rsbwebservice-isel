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
    /**
     * Identificador interno do elemento do pessoal
     */
    @Column(nullable = false)
    private String idInterno;
    /**
     * Numero de Matricula do elemento do pessoal
     */
    @Column(nullable = false)
    private String Matricula;
    /**
     * Número mecanográfico. Identificador unico global do elemento
     */
    @Column(unique = true, nullable = false)
    private String numMecanografico;
    /**
     * Abreviatura - Um nome curto para o elemento
     */
    @Column(nullable = false)
    private String abreviatura;
    /**
     * Nome - Nome Completo do elemento do pessoal
     */
    @Column(nullable = false)
    private String nome;
    /**
     * Data de Nascimento - Data de Nascimento do elemento do pessoal
     */
    @Column(nullable = false)
    private Date dataNascimento;
    /**
     * Telefone1 - Um número de telefone (obrigatório)
     */
    @Column(nullable = false)
    private String telefone1;
    /**
     * Telefone2 - Um numero de telefone facultativo
     */
    private String telefone2;
    /**
     * email - Endereço de email do elemento
     */
    private String eMail;
    /**
     * NIF - Numero de Identificação Fiscal do Elemento
     */
    private String nif;
    /**
     * Data de Ingresso - Data de Admissão do elemento no corpo de bombeiros
     */
    private Date dataIngresso;
    /**
     * Tipo de documento de Identificação - Bilhete de Identidade ou Cartão de Cidadão
     */
    @Enumerated(EnumType.STRING)
    private TipoDocIdentificacao tipoDocIdentificacao;
    /**
     * Numero do documento de Identificação
     */
    private String numDocIdentificação;
    /**
     * Factor de Elegibilidade - Factor calculado para a atribuição de responsabilidades operacionais
     */
    private Float factorElegibilidade;
    /**
     * Posto Funcional - Identificador do Posto Funcional ocupado por omissão pelo elemento
     */
    @ManyToOne
    @JoinColumn(name = "postoFuncional_id")
    private PostoFuncional postoFuncional;
    /**
     * Tipo de Presença - Caracteriza o tipo de ausencia, ou a função desempenhada em caso de presença
     */
    @ManyToOne
    @JoinColumn(name = "tipoPresenca_id")
    private TipoPresenca tipoPresenca;
    /**
     * Turno - Identificador do Turno a que o elemento está atribuido por omissão
     */
    @ManyToOne
    @JoinColumn(name = "turno_id")
    private Turno turno;
    /**
     * Instalação - Identificador da Instalação em que o elemento está instalado por omissão
     */
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

    public TipoDocIdentificacao getTipoDocIdentificacao() {
        return tipoDocIdentificacao;
    }

    public void setTipoDocIdentificacao(TipoDocIdentificacao tipoDocIdentificacao) {
        this.tipoDocIdentificacao = tipoDocIdentificacao;
    }

    public String getNumDocIdentificação() {
        return numDocIdentificação;
    }

    public void setNumDocIdentificação(String numDocIdentificação) {
        this.numDocIdentificação = numDocIdentificação;
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
