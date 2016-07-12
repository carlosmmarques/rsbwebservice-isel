package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class ElementoDto {

    public final Long id;

    public final String
                    uri_pessoa
                    ,uri_instalacaoPorOmissao
                    ,uri_postoFuncionalPorOmissao
                    ,uri_tipoPresencaPorOmissao
                    ,uri_categoria
                    ,idInterno
                    ,Matricula //converter para inteiro
                    ,nome
                    ,dataIngresso
                    ,numMecanografico // converter para inteiro
    ;

    public ElementoDto(
            Long id
            ,String uri_pessoa
            ,String uri_instalacaoPorOmissao
            ,String uri_postoFuncionalPorOmissao
            ,String uri_tipoPresencaPorOmissao
            ,String uri_categoria
            ,String idInterno
            ,String matricula
            ,String nome
            ,String dataIngresso
            ,String numMecanografico
    ) {
        this.id = id;
        this.uri_pessoa = uri_pessoa;
        this.uri_instalacaoPorOmissao = uri_instalacaoPorOmissao;
        this.uri_postoFuncionalPorOmissao = uri_postoFuncionalPorOmissao;
        this.uri_tipoPresencaPorOmissao = uri_tipoPresencaPorOmissao;
        this.uri_categoria = uri_categoria;
        this.idInterno = idInterno;
        this.Matricula = matricula;
        this.nome = nome;
        this.dataIngresso = dataIngresso;
        this.numMecanografico = numMecanografico;
    }
}
