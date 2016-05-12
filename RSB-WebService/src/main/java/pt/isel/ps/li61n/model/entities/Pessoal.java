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

    @Column(nullable = true)
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
    private TipoDocIdentificacao tipoDocIdentificacao;
    private String numDocIdentificação;
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
    public String getNumDocIdentificação() {
        return numDocIdentificação;
    }

    /**
     * @param numDocIdentificação Numero do documento de Identificação
     */
    public void setNumDocIdentificação(String numDocIdentificação) {
        this.numDocIdentificação = numDocIdentificação;
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
