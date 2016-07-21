package pt.isel.ps.li61n.model.entities;

import java.time.LocalDate;

/**
 * Created on 22/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class Elemento extends Identity< Long >{

    private String nome;

    private LocalDate dataNascimento;

    private int numMecanografico;

    private int matricula;

    private LocalDate dataIngresso;

    // FK de Categoria
    private Long categoriaId;
    private Categoria categoria;
    private LocalDate dataAtribuicao;
    private Float classificacaoFormacao;

    private String idInterno;

    // FK de PostoFuncional
    private Long postoFuncionalId;
    private PostoFuncional postoFuncional;

    // FK de TipoPresença
    private String tipoPresencaId;

    // FK de Turno
    private Long turnoId;

    // FK de Instalacao
    private Long instalacaoId;



    public Elemento(
            String nome
            ,LocalDate dataNascimento
            ,int numMecanografico
            ,int matricula
            ,LocalDate dataIngresso
            ,String idInterno
            ,Long postoFuncionalId
    ) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.numMecanografico = numMecanografico;
        this.matricula = matricula;
        this.dataIngresso = dataIngresso;
        this.idInterno = idInterno;
        this.postoFuncionalId = postoFuncionalId;
    }

    public Elemento() {
    }

    public Elemento(
            int numMecanografico
            ,String idInterno
            ,Long postoFuncionalId
            ,Long id
    ) {
        this( null, null, numMecanografico, -1, null, idInterno, postoFuncionalId );
        this.setId( id );
    }

    public Elemento(
            int numMecanografico
            ,String idInterno
            ,Long postoFuncionalId
    ) {
        this( numMecanografico, idInterno, postoFuncionalId, null );
    }



    //
    // getters & setters
    //
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula( int matricula ) {
        this.matricula = matricula;
    }

    public int getNumMecanografico() {
        return numMecanografico;
    }

    public void setNumMecanografico( int numMecanografico ) {
        this.numMecanografico = numMecanografico;
    }

    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno( String idInterno ) {
        this.idInterno = idInterno;
    }

    public Long getPostoFuncionalId() {
        return postoFuncionalId;
    }

    public void setPostoFuncionalId( Long postoFuncionalId ) {
        this.postoFuncionalId = postoFuncionalId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PostoFuncional getPostoFuncional() {
        return postoFuncional;
    }

    public void setPostoFuncional( PostoFuncional postoFuncional ){
        this.postoFuncional = postoFuncional;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getTipoPresencaId() {
        return tipoPresencaId;
    }

    public void setTipoPresencaId(String tipoPresencaId) {
        this.tipoPresencaId = tipoPresencaId;
    }

    public Long getTurnoId() {
        return turnoId;
    }

    public void setTurnoId( Long turnoId ) {
        this.turnoId = turnoId;
    }

    public Long getInstalacaoId() {
        return instalacaoId;
    }

    public void setInstalacaoId( Long instalacaoId ) {
        this.instalacaoId = instalacaoId;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId( Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public LocalDate getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(LocalDate dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }

    public Float getClassificacaoFormacao() {
        return classificacaoFormacao;
    }

    public void setClassificacaoFormacao(Float classificacaoFormacao) {
        this.classificacaoFormacao = classificacaoFormacao;
    }

    @Override
    public String toString(){
        return idInterno;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Elemento elemento = (Elemento) o;

        if( numMecanografico == elemento.numMecanografico ){
            return true;
        }
        else{
            Long id = this.getId();
            if( id != null )
                return this.getId().equals( elemento.getId() );
            return false;
        }
        /*
        if (matricula != elemento.matricula) return false;
        if (nome != null ? !nome.equals(elemento.nome) : elemento.nome != null) return false;
        if (dataNascimento != null ? !dataNascimento.equals(elemento.dataNascimento) : elemento.dataNascimento != null)
            return false;
        if (dataIngresso != null ? !dataIngresso.equals(elemento.dataIngresso) : elemento.dataIngresso != null)
            return false;
        if (categoria != null ? !categoria.equals(elemento.categoria) : elemento.categoria != null) return false;
        if (idInterno != null ? !idInterno.equals(elemento.idInterno) : elemento.idInterno != null) return false;
        if (postoFuncional != null ? !postoFuncional.equals(elemento.postoFuncional) : elemento.postoFuncional != null)
            return false;
        return postoFuncionalId != null ? postoFuncionalId.equals(elemento.postoFuncionalId) : elemento.postoFuncionalId == null;
        */


    }

    /*
    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        result = 31 * result + numMecanografico;
        result = 31 * result + matricula;
        result = 31 * result + (dataIngresso != null ? dataIngresso.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (idInterno != null ? idInterno.hashCode() : 0);
        result = 31 * result + (postoFuncional != null ? postoFuncional.hashCode() : 0);
        result = 31 * result + (postoFuncionalId != null ? postoFuncionalId.hashCode() : 0);
        return result;
    }
    */

}
