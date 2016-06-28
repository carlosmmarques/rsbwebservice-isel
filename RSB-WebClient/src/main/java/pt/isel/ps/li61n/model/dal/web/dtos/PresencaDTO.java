package pt.isel.ps.li61n.model.dal.web.dtos;

/**
 * Created on 27/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PresencaDto {

    public final Long id;

    public final String
                    uri_presenca
                    ,uri_elementodopessoal
                    ,uri_elementoreforco
                    ,uri_elementoreforcado
                    ,uri_instalacao
                    ,uri_periodo
                    ,uri_postofuncionalefectivo
                    ,uri_tipopresencaefectiva
                    ,uri_turnoefectivo
                    ,tipopresencaefectiva
                    ,elementoDoPessoal
    ;
    public final float numHoras;


    public final String data;
    public final String horaInicio;

    public PresencaDto(
            Long id
            ,String uri_presenca
            ,String uri_elementodopessoal
            ,String uri_elementoreforco
            ,String uri_elementoreforcado
            ,String uri_instalacao
            ,String uri_periodo
            ,String uri_postofuncionalefectivo
            ,String uri_tipopresencaefectiva
            ,String uri_turnoefectivo
            ,String tipopresencaefectiva
            ,String elementoDoPessoal
            ,float numHoras

            ,String data
            ,String horaInicio
    ) {
        this.id = id;
        this.uri_presenca = uri_presenca;
        this.uri_elementodopessoal = uri_elementodopessoal;
        this.uri_elementoreforco = uri_elementoreforco;
        this.uri_elementoreforcado = uri_elementoreforcado;
        this.uri_instalacao = uri_instalacao;
        this.uri_periodo = uri_periodo;
        this.uri_postofuncionalefectivo = uri_postofuncionalefectivo;
        this.uri_tipopresencaefectiva = uri_tipopresencaefectiva;
        this.uri_turnoefectivo = uri_turnoefectivo;
        this.tipopresencaefectiva = tipopresencaefectiva;
        this.elementoDoPessoal = elementoDoPessoal;
        this.numHoras = numHoras;

        this.data = data;
        this.horaInicio = horaInicio;
    }
}
