package pt.isel.ps.li61n.viewModel;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * Created on 30/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

public class InsertElementoUI {

    @NotEmpty( message = ErrorsMsg.Elemento.Nome.NOT_NULL )
    @Size( max = 250, message = ErrorsMsg.Elemento.Nome.SIZE )
    private String nome;

    @Pattern( regexp = "\\d{2}/\\d{2}/\\d{4}", message = ErrorsMsg.Elemento.DataNascimento.PATTERN )
    //@NotNull( message = ErrorsMsg.Elemento.DataNascimento.NOT_NULL )
    private String dataNascimento;
    //private LocalDate dataNascimento;

    @NotNull( message = ErrorsMsg.Elemento.NumMecanografico.NOT_NULL )
    private Integer numMecanografico;

    // Categoria
    @NotNull
    private Long categoriaId;

    // Not null se for bombeiro
    //private String idInterno;

    @NotNull( message = ErrorsMsg.Elemento.Matricula.NOT_NULL )
    @Range ( min= 1, message = ErrorsMsg.Elemento.Matricula.RANGE )
    private Integer matricula;

    // Not null
    //private String abreviatura; // perceber para q serve!?

    @Pattern( flags = Pattern.Flag.COMMENTS
            ,regexp = "\\d{2}/\\d{2}/\\d{4}"
            ,message = ErrorsMsg.Elemento.DataIngresso.PATTERN
    )
    private String dataIngresso;

    // Not null //TODO: Converter para List<>
    private String contacto; // telefone1

    // private String telefone2;

    // private String email;

    //private String nif;

    //private Boolean tipoDocIdentificacao;

    //private Integer numDocIdentificacao;

    // FK
    private Long instalacaoId;

    // FK
    private Long postoFuncionalId;

    // FK
    private String tipoPresencaId;

    // FK
    private Long turnoId;

    /////////////////////////////////////////////////////////////////////////
    //
    //  Getters & Setters
    //
    ////////////////////////////////////////////////////////////////////////

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento( LocalDate dataNascimento ){
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }
    */

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(String dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public Integer getNumMecanografico() {
        return numMecanografico;
    }

    public void setNumMecanografico(Integer numMecanografico) {
        this.numMecanografico = numMecanografico;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula( Integer matricula ) {
        this.matricula = matricula;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId( Long categoriaId ) {
        this.categoriaId = categoriaId;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Long getPostoFuncionalId() {
        return postoFuncionalId;
    }

    public void setPostoFuncionalId(Long postoFuncionalId) {
        this.postoFuncionalId = postoFuncionalId;
    }

    public Long getInstalacaoId() {
        return instalacaoId;
    }

    public void setInstalacaoId(Long instalacaoId) {
        this.instalacaoId = instalacaoId;
    }

    public String getTipoPresencaId() {
        return tipoPresencaId;
    }

    public void setTipoPresencaId( String tipoPresencaId) {
        this.tipoPresencaId = tipoPresencaId;
    }

    public Long getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }

}
