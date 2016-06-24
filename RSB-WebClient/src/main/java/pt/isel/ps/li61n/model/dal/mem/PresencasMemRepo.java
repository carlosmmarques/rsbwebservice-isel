package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPresencasRepository;
import pt.isel.ps.li61n.model.entities.Presenca;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PresencasMemRepo implements IPresencasRepository {

    private HashMap< Long, Presenca > _repo;
    private long _repoSize;

    public PresencasMemRepo() {
        _repo = new HashMap<>();
        _repoSize = 0;

        populate();
    }

    @Override
    public Long insert( Presenca element ) {
        Long id = _repoSize++;
        element.setId( id );
        _repo.put( id, element );
        return id;
    }

    @Override
    public Presenca selectOne(Long aLong) {
        return null;
    }

    @Override
    public Collection<Presenca> selectAll() {

        return _repo.values();
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Long aLong) {

    }

    private void populate(){

        //
        // Lista de presenças do B.Sap 510 em Dezembro 2015
        //
        long postoFuncionalId_default = 1L; // Simular que PostoFuncional.getDesignacao() = "B.Sap";

        long periodoId = 11L; // Confirmar que é referente ao periodo do "mês de Dezembro"
        String tipoPresencaId_default = "S4";
        LocalTime servicoDia = LocalTime.of( 8, 00 );
        LocalTime servicoNoite = LocalTime.of( 20, 00 );
        float numHoras = 12;
        long instalacaoId_default = 3; // Simular que é do "Quartel de Alvalade"
        long turnoId_default = 1L;   // Simular que é o "turno 1" (Turno.Descricao)
        int elementoId = 955615; // Simular que Pessoal.getIdInterno() = "510";

        Presenca dia3Dez2015 = new Presenca();
        dia3Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 3 ) );
        dia3Dez2015.setHoraInicio( servicoDia );
        dia3Dez2015.setNumHoras( numHoras );
        dia3Dez2015.setPeriodoId( periodoId );
        dia3Dez2015.setElementoId( elementoId );
        dia3Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia3Dez2015.setElementoReforcoReforcadoId( null );
        dia3Dez2015.setReforcoNaoReforcado( null );
        dia3Dez2015.setInstalacaoId( instalacaoId_default );
        dia3Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia3Dez2015.setTurnoId( turnoId_default );
        insert( dia3Dez2015 );

        Presenca dia4Dez2015 = new Presenca();
        dia4Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 4 ) );
        dia4Dez2015.setHoraInicio( servicoNoite );
        dia4Dez2015.setNumHoras( numHoras );
        dia4Dez2015.setPeriodoId( periodoId );
        dia4Dez2015.setElementoId( elementoId );
        dia4Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia4Dez2015.setElementoReforcoReforcadoId( null );
        dia4Dez2015.setReforcoNaoReforcado( null );
        dia4Dez2015.setInstalacaoId( instalacaoId_default );
        dia4Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia4Dez2015.setTurnoId( turnoId_default );
        insert( dia4Dez2015 );

        Presenca dia7Dez2015 = new Presenca();
        dia7Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 7 ) );
        dia7Dez2015.setHoraInicio( servicoDia );
        dia7Dez2015.setNumHoras( numHoras );
        dia7Dez2015.setPeriodoId( periodoId );
        dia7Dez2015.setElementoId( elementoId );
        dia7Dez2015.setTipoPresencaId( "X" ); // Férias confirmadas
        dia7Dez2015.setElementoReforcoReforcadoId( null );
        dia7Dez2015.setReforcoNaoReforcado( null );
        dia7Dez2015.setInstalacaoId( instalacaoId_default );
        dia7Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia7Dez2015.setTurnoId( turnoId_default );
        insert( dia7Dez2015 );

        Presenca dia8Dez2015 = new Presenca();
        dia8Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 8 ) );
        dia8Dez2015.setHoraInicio( servicoNoite );
        dia8Dez2015.setNumHoras( numHoras );
        dia8Dez2015.setPeriodoId( periodoId );
        dia8Dez2015.setElementoId( elementoId );
        dia8Dez2015.setTipoPresencaId( "S0" ); // Dispensa -> Troca
        dia8Dez2015.setElementoReforcoReforcadoId( 955632L ); // idInterno -> 519
        dia8Dez2015.setReforcoNaoReforcado( true );
        dia8Dez2015.setInstalacaoId( instalacaoId_default );
        dia8Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia8Dez2015.setTurnoId( turnoId_default );
        insert( dia8Dez2015 );

        Presenca dia11Dez2015 = new Presenca();
        dia11Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 11 ) );
        dia11Dez2015.setHoraInicio( servicoDia );
        dia11Dez2015.setNumHoras( numHoras );
        dia11Dez2015.setPeriodoId( periodoId );
        dia11Dez2015.setElementoId( elementoId );
        dia11Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia11Dez2015.setElementoReforcoReforcadoId( null );
        dia11Dez2015.setReforcoNaoReforcado( null );
        dia11Dez2015.setInstalacaoId( instalacaoId_default );
        dia11Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia11Dez2015.setTurnoId( turnoId_default );
        insert( dia11Dez2015 );

        Presenca dia12Dez2015 = new Presenca();
        dia12Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 12 ) );
        dia12Dez2015.setHoraInicio( servicoNoite );
        dia12Dez2015.setNumHoras( numHoras );
        dia12Dez2015.setPeriodoId( periodoId );
        dia12Dez2015.setElementoId( elementoId );
        dia12Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia12Dez2015.setElementoReforcoReforcadoId( null );
        dia12Dez2015.setReforcoNaoReforcado( null );
        dia12Dez2015.setInstalacaoId( instalacaoId_default );
        dia12Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia12Dez2015.setTurnoId( turnoId_default );
        insert( dia12Dez2015 );

        Presenca dia13Dez2015 = new Presenca();
        dia13Dez2015.setData( LocalDate.of( 2015, Month.JANUARY, 13 ) );
        dia13Dez2015.setHoraInicio( servicoNoite );
        dia13Dez2015.setNumHoras( numHoras );
        dia13Dez2015.setPeriodoId( periodoId );
        dia13Dez2015.setElementoId( elementoId );
        dia13Dez2015.setTipoPresencaId( "4S" ); // a dar troca
        dia13Dez2015.setElementoReforcoReforcadoId( 955492L ); // idInterno -> 441
        dia13Dez2015.setReforcoNaoReforcado( false );
        dia13Dez2015.setInstalacaoId( instalacaoId_default );
        dia13Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia13Dez2015.setTurnoId( 2L );
        insert( dia13Dez2015 );

        Presenca dia15Dez2015 = new Presenca();
        dia15Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 15 ) );
        dia15Dez2015.setHoraInicio( servicoDia );
        dia15Dez2015.setNumHoras( numHoras );
        dia15Dez2015.setPeriodoId( periodoId );
        dia15Dez2015.setElementoId( elementoId );
        dia15Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia15Dez2015.setElementoReforcoReforcadoId( null );
        dia15Dez2015.setReforcoNaoReforcado( null );
        dia15Dez2015.setInstalacaoId( instalacaoId_default );
        dia15Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia15Dez2015.setTurnoId( turnoId_default );
        insert( dia15Dez2015 );

        Presenca dia16Dez2015 = new Presenca();
        dia16Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 16 ) );
        dia16Dez2015.setHoraInicio( servicoNoite );
        dia16Dez2015.setNumHoras( numHoras );
        dia16Dez2015.setPeriodoId( periodoId );
        dia16Dez2015.setElementoId( elementoId );
        dia16Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia16Dez2015.setElementoReforcoReforcadoId( null );
        dia16Dez2015.setReforcoNaoReforcado( null );
        dia16Dez2015.setInstalacaoId( instalacaoId_default );
        dia16Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia16Dez2015.setTurnoId( turnoId_default );
        insert( dia16Dez2015 );

        Presenca dia19Dez2015 = new Presenca();
        dia19Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 19 ) );
        dia19Dez2015.setHoraInicio( servicoDia );
        dia19Dez2015.setNumHoras( numHoras );
        dia19Dez2015.setPeriodoId( periodoId );
        dia19Dez2015.setElementoId( elementoId );
        dia19Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia19Dez2015.setElementoReforcoReforcadoId( null );
        dia19Dez2015.setReforcoNaoReforcado( null );
        dia19Dez2015.setInstalacaoId( instalacaoId_default );
        dia19Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia19Dez2015.setTurnoId( turnoId_default );
        insert( dia19Dez2015 );

        Presenca dia20Dez2015 = new Presenca();
        dia20Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 20 ) );
        dia20Dez2015.setHoraInicio( servicoNoite );
        dia20Dez2015.setNumHoras( numHoras );
        dia20Dez2015.setPeriodoId( periodoId );
        dia20Dez2015.setElementoId( elementoId );
        dia20Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia20Dez2015.setElementoReforcoReforcadoId( null );
        dia20Dez2015.setReforcoNaoReforcado( null );
        dia20Dez2015.setInstalacaoId( instalacaoId_default );
        dia20Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia20Dez2015.setTurnoId( turnoId_default );
        insert( dia20Dez2015 );

        Presenca dia23Dez2015 = new Presenca();
        dia23Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 23 ) );
        dia23Dez2015.setHoraInicio( servicoDia );
        dia23Dez2015.setNumHoras( numHoras );
        dia23Dez2015.setPeriodoId( periodoId );
        dia23Dez2015.setElementoId( elementoId );
        dia23Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia23Dez2015.setElementoReforcoReforcadoId( null );
        dia23Dez2015.setReforcoNaoReforcado( null );
        dia23Dez2015.setInstalacaoId( instalacaoId_default );
        dia23Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia23Dez2015.setTurnoId( turnoId_default );
        insert( dia23Dez2015 );

        Presenca dia24Dez2015 = new Presenca();
        dia24Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 24 ) );
        dia24Dez2015.setHoraInicio( servicoNoite );
        dia24Dez2015.setNumHoras( numHoras );
        dia24Dez2015.setPeriodoId( periodoId );
        dia24Dez2015.setElementoId( elementoId );
        dia24Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia24Dez2015.setElementoReforcoReforcadoId( null );
        dia24Dez2015.setReforcoNaoReforcado( null );
        dia24Dez2015.setInstalacaoId( instalacaoId_default );
        dia24Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia24Dez2015.setTurnoId( turnoId_default );
        insert( dia24Dez2015 );

        Presenca dia27Dez2015 = new Presenca();
        dia27Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 27 ) );
        dia27Dez2015.setHoraInicio( servicoDia );
        dia27Dez2015.setNumHoras( numHoras );
        dia27Dez2015.setPeriodoId( periodoId );
        dia27Dez2015.setElementoId( elementoId );
        dia27Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia27Dez2015.setElementoReforcoReforcadoId( null );
        dia27Dez2015.setReforcoNaoReforcado( null );
        dia27Dez2015.setInstalacaoId( instalacaoId_default );
        dia27Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia27Dez2015.setTurnoId( turnoId_default );
        insert( dia27Dez2015 );

        Presenca dia28Dez2015 = new Presenca();
        dia28Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 28 ) );
        dia28Dez2015.setHoraInicio( servicoNoite );
        dia28Dez2015.setNumHoras( numHoras );
        dia28Dez2015.setPeriodoId( periodoId );
        dia28Dez2015.setElementoId( elementoId );
        dia28Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia28Dez2015.setElementoReforcoReforcadoId( null );
        dia28Dez2015.setReforcoNaoReforcado( null );
        dia28Dez2015.setInstalacaoId( instalacaoId_default );
        dia28Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia28Dez2015.setTurnoId( turnoId_default );
        insert( dia28Dez2015 );

        Presenca dia31Dez2015 = new Presenca();
        dia31Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 31 ) );
        dia31Dez2015.setHoraInicio( servicoDia );
        dia31Dez2015.setNumHoras( numHoras );
        dia31Dez2015.setPeriodoId( periodoId );
        dia31Dez2015.setElementoId( elementoId );
        dia31Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia31Dez2015.setElementoReforcoReforcadoId( null );
        dia31Dez2015.setReforcoNaoReforcado( null );
        dia31Dez2015.setInstalacaoId( instalacaoId_default );
        dia31Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia31Dez2015.setTurnoId( turnoId_default );
        insert( dia31Dez2015 );


        //
        // Presenças do '503'
        //

        instalacaoId_default = 4; // Simular que é do "Quartel de Benfica"
        turnoId_default = 3L;   // Simular que é o "turno 3" (Turno.Descricao)
            elementoId = 955495; // Simular que Pessoal.getIdInterno() = "503";

        Presenca dia1Dez2015 = new Presenca();
        dia1Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 1 ) );
        dia1Dez2015.setHoraInicio( servicoDia );
        dia1Dez2015.setNumHoras( numHoras );
        dia1Dez2015.setPeriodoId( periodoId );
        dia1Dez2015.setElementoId( elementoId );
        dia1Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia1Dez2015.setElementoReforcoReforcadoId( null );
        dia1Dez2015.setReforcoNaoReforcado( null );
        dia1Dez2015.setInstalacaoId( instalacaoId_default );
        dia1Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia1Dez2015.setTurnoId( turnoId_default );
        insert( dia1Dez2015 );

        Presenca dia2Dez2015 = new Presenca();
        dia2Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 2 ) );
        dia2Dez2015.setHoraInicio( servicoNoite );
        dia2Dez2015.setNumHoras( numHoras );
        dia2Dez2015.setPeriodoId( periodoId );
        dia2Dez2015.setElementoId( elementoId );
        dia2Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia2Dez2015.setElementoReforcoReforcadoId( null );
        dia2Dez2015.setReforcoNaoReforcado( null );
        dia2Dez2015.setInstalacaoId( instalacaoId_default );
        dia2Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia2Dez2015.setTurnoId( turnoId_default );
        insert( dia2Dez2015 );

        Presenca dia5Dez2015 = new Presenca();
        dia5Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 5 ) );
        dia5Dez2015.setHoraInicio( servicoDia );
        dia5Dez2015.setNumHoras( numHoras );
        dia5Dez2015.setPeriodoId( periodoId );
        dia5Dez2015.setElementoId( elementoId );
        dia5Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia5Dez2015.setElementoReforcoReforcadoId( null );
        dia5Dez2015.setReforcoNaoReforcado( null );
        dia5Dez2015.setInstalacaoId( instalacaoId_default );
        dia5Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia5Dez2015.setTurnoId( turnoId_default );
        insert( dia5Dez2015 );

        Presenca dia6Dez2015 = new Presenca();
        dia6Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 6 ) );
        dia6Dez2015.setHoraInicio( servicoNoite );
        dia6Dez2015.setNumHoras( numHoras );
        dia6Dez2015.setPeriodoId( periodoId );
        dia6Dez2015.setElementoId( elementoId );
        dia6Dez2015.setTipoPresencaId( "S0" ); // Troca com o '666'
        dia6Dez2015.setElementoReforcoReforcadoId( 955490L );
        dia6Dez2015.setReforcoNaoReforcado( true );
        dia6Dez2015.setInstalacaoId( instalacaoId_default );
        dia6Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia6Dez2015.setTurnoId( turnoId_default );
        insert( dia6Dez2015 );

        Presenca dia9Dez2015 = new Presenca();
        dia9Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 9 ) );
        dia9Dez2015.setHoraInicio( servicoDia );
        dia9Dez2015.setNumHoras( numHoras );
        dia9Dez2015.setPeriodoId( periodoId );
        dia9Dez2015.setElementoId( elementoId );
        dia9Dez2015.setTipoPresencaId( "S0" ); // Troca com o '571'
        dia9Dez2015.setElementoReforcoReforcadoId( 955603L );
        dia9Dez2015.setReforcoNaoReforcado( true );
        dia9Dez2015.setInstalacaoId( instalacaoId_default );
        dia9Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia9Dez2015.setTurnoId( turnoId_default );
        insert( dia9Dez2015 );

        Presenca dia10Dez2015 = new Presenca();
        dia10Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 10 ) );
        dia10Dez2015.setHoraInicio( servicoNoite );
        dia10Dez2015.setNumHoras( numHoras );
        dia10Dez2015.setPeriodoId( periodoId );
        dia10Dez2015.setElementoId( elementoId );
        dia10Dez2015.setTipoPresencaId( "S0" ); // Troca com o '666'
        dia10Dez2015.setElementoReforcoReforcadoId( 955490L );
        dia10Dez2015.setReforcoNaoReforcado( true );
        dia10Dez2015.setInstalacaoId( instalacaoId_default );
        dia10Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia10Dez2015.setTurnoId( turnoId_default );
        insert( dia10Dez2015 );

        Presenca dia13Dez2015_503 = new Presenca();
        dia13Dez2015_503.setData( LocalDate.of( 2015, Month.DECEMBER, 13 ) );
        dia13Dez2015_503.setHoraInicio( servicoDia );
        dia13Dez2015_503.setNumHoras( numHoras );
        dia13Dez2015_503.setPeriodoId( periodoId );
        dia13Dez2015_503.setElementoId( elementoId );
        dia13Dez2015_503.setTipoPresencaId( tipoPresencaId_default );
        dia13Dez2015_503.setElementoReforcoReforcadoId( null );
        dia13Dez2015_503.setReforcoNaoReforcado( null );
        dia13Dez2015_503.setInstalacaoId( instalacaoId_default );
        dia13Dez2015_503.setPostoFuncionalId( postoFuncionalId_default );
        dia13Dez2015_503.setTurnoId( turnoId_default );
        insert( dia13Dez2015_503 );

        Presenca dia14Dez2015 = new Presenca();
        dia14Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 14 ) );
        dia14Dez2015.setHoraInicio( servicoNoite );
        dia14Dez2015.setNumHoras( numHoras );
        dia14Dez2015.setPeriodoId( periodoId );
        dia14Dez2015.setElementoId( elementoId );
        dia14Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia14Dez2015.setElementoReforcoReforcadoId( null );
        dia14Dez2015.setReforcoNaoReforcado( null );
        dia14Dez2015.setInstalacaoId( instalacaoId_default );
        dia14Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia14Dez2015.setTurnoId( turnoId_default );
        insert( dia14Dez2015 );

        Presenca dia17Dez2015 = new Presenca();
        dia17Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 17 ) );
        dia17Dez2015.setHoraInicio( servicoDia );
        dia17Dez2015.setNumHoras( numHoras );
        dia17Dez2015.setPeriodoId( periodoId );
        dia17Dez2015.setElementoId( elementoId );
        dia17Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia17Dez2015.setElementoReforcoReforcadoId( null );
        dia17Dez2015.setReforcoNaoReforcado( null );
        dia17Dez2015.setInstalacaoId( instalacaoId_default );
        dia17Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia17Dez2015.setTurnoId( turnoId_default );
        insert( dia17Dez2015 );

        Presenca dia18Dez2015 = new Presenca();
        dia18Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 18 ) );
        dia18Dez2015.setHoraInicio( servicoNoite );
        dia18Dez2015.setNumHoras( numHoras );
        dia18Dez2015.setPeriodoId( periodoId );
        dia18Dez2015.setElementoId( elementoId );
        dia18Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia18Dez2015.setElementoReforcoReforcadoId( null );
        dia18Dez2015.setReforcoNaoReforcado( null );
        dia18Dez2015.setInstalacaoId( instalacaoId_default );
        dia18Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia18Dez2015.setTurnoId( turnoId_default );
        insert( dia18Dez2015 );

        Presenca dia21Dez2015 = new Presenca();
        dia21Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 21 ) );
        dia21Dez2015.setHoraInicio( servicoDia );
        dia21Dez2015.setNumHoras( numHoras );
        dia21Dez2015.setPeriodoId( periodoId );
        dia21Dez2015.setElementoId( elementoId );
        dia21Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia21Dez2015.setElementoReforcoReforcadoId( null );
        dia21Dez2015.setReforcoNaoReforcado( null );
        dia21Dez2015.setInstalacaoId( instalacaoId_default );
        dia21Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia21Dez2015.setTurnoId( turnoId_default );
        insert( dia21Dez2015 );

        Presenca dia22Dez2015 = new Presenca();
        dia22Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 22 ) );
        dia22Dez2015.setHoraInicio( servicoDia );
        dia22Dez2015.setNumHoras( numHoras );
        dia22Dez2015.setPeriodoId( periodoId );
        dia22Dez2015.setElementoId( elementoId );
        dia22Dez2015.setTipoPresencaId( "S0" ); // Dispensa
        dia22Dez2015.setElementoReforcoReforcadoId( null );
        dia22Dez2015.setReforcoNaoReforcado( null );
        dia22Dez2015.setInstalacaoId( instalacaoId_default );
        dia22Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia22Dez2015.setTurnoId( turnoId_default );
        insert( dia22Dez2015 );

        Presenca dia25Dez2015 = new Presenca();
        dia25Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 25 ) );
        dia25Dez2015.setHoraInicio( servicoDia );
        dia25Dez2015.setNumHoras( numHoras );
        dia25Dez2015.setPeriodoId( periodoId );
        dia25Dez2015.setElementoId( elementoId );
        dia25Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia25Dez2015.setElementoReforcoReforcadoId( null );
        dia25Dez2015.setReforcoNaoReforcado( null );
        dia25Dez2015.setInstalacaoId( instalacaoId_default );
        dia25Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia25Dez2015.setTurnoId( turnoId_default );
        insert( dia25Dez2015 );

        Presenca dia26Dez2015 = new Presenca();
        dia26Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 26 ) );
        dia26Dez2015.setHoraInicio( servicoNoite );
        dia26Dez2015.setNumHoras( numHoras );
        dia26Dez2015.setPeriodoId( periodoId );
        dia26Dez2015.setElementoId( elementoId );
        dia26Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia26Dez2015.setElementoReforcoReforcadoId( null );
        dia26Dez2015.setReforcoNaoReforcado( null );
        dia26Dez2015.setInstalacaoId( instalacaoId_default );
        dia26Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia26Dez2015.setTurnoId( turnoId_default );
        insert( dia26Dez2015 );

        Presenca dia26Dez2015_troca = new Presenca();
        dia26Dez2015_troca.setData( LocalDate.of( 2015, Month.DECEMBER, 26 ) );
        dia26Dez2015_troca.setHoraInicio( servicoNoite );
        dia26Dez2015_troca.setNumHoras( numHoras );
        dia26Dez2015_troca.setPeriodoId( periodoId );
        dia26Dez2015_troca.setElementoId( elementoId );
        dia26Dez2015_troca.setTipoPresencaId( "4S" );
        dia26Dez2015_troca.setElementoReforcoReforcadoId( 955511L ); // troca pelo '567'
        dia26Dez2015_troca.setReforcoNaoReforcado( false );
        dia26Dez2015_troca.setInstalacaoId( instalacaoId_default );
        dia26Dez2015_troca.setPostoFuncionalId( postoFuncionalId_default );
        dia26Dez2015_troca.setTurnoId( 4L );
        insert( dia26Dez2015_troca );

        Presenca dia29Dez2015_troca = new Presenca();
        dia29Dez2015_troca.setData( LocalDate.of( 2015, Month.DECEMBER, 29 ) );
        dia29Dez2015_troca.setHoraInicio( servicoNoite );
        dia29Dez2015_troca.setNumHoras( numHoras );
        dia29Dez2015_troca.setPeriodoId( periodoId );
        dia29Dez2015_troca.setElementoId( elementoId );
        dia29Dez2015_troca.setTipoPresencaId( "4S" );
        dia29Dez2015_troca.setElementoReforcoReforcadoId( 955603L ); // troca pelo '571'
        dia29Dez2015_troca.setReforcoNaoReforcado( false );
        dia29Dez2015_troca.setInstalacaoId( instalacaoId_default );
        dia29Dez2015_troca.setPostoFuncionalId( postoFuncionalId_default );
        dia29Dez2015_troca.setTurnoId( 2L );
        insert( dia29Dez2015_troca );

        Presenca dia29Dez2015 = new Presenca();
        dia29Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 29 ) );
        dia29Dez2015.setHoraInicio( servicoDia );
        dia29Dez2015.setNumHoras( numHoras );
        dia29Dez2015.setPeriodoId( periodoId );
        dia29Dez2015.setElementoId( elementoId );
        dia29Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia29Dez2015.setElementoReforcoReforcadoId( null );
        dia29Dez2015.setReforcoNaoReforcado( null );
        dia29Dez2015.setInstalacaoId( instalacaoId_default );
        dia29Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia29Dez2015.setTurnoId( turnoId_default );
        insert( dia29Dez2015 );

        Presenca dia30Dez2015 = new Presenca();
        dia30Dez2015.setData( LocalDate.of( 2015, Month.DECEMBER, 30 ) );
        dia30Dez2015.setHoraInicio( servicoNoite );
        dia30Dez2015.setNumHoras( numHoras );
        dia30Dez2015.setPeriodoId( periodoId );
        dia30Dez2015.setElementoId( elementoId );
        dia30Dez2015.setTipoPresencaId( tipoPresencaId_default );
        dia30Dez2015.setElementoReforcoReforcadoId( null );
        dia30Dez2015.setReforcoNaoReforcado( null );
        dia30Dez2015.setInstalacaoId( instalacaoId_default );
        dia30Dez2015.setPostoFuncionalId( postoFuncionalId_default );
        dia30Dez2015.setTurnoId( turnoId_default );
        insert( dia30Dez2015 );

        Presenca dia30Dez2015_troca = new Presenca();
        dia30Dez2015_troca.setData( LocalDate.of( 2015, Month.DECEMBER, 30 ) );
        dia30Dez2015_troca.setHoraInicio( servicoNoite );
        dia30Dez2015_troca.setNumHoras( numHoras );
        dia30Dez2015_troca.setPeriodoId( periodoId );
        dia30Dez2015_troca.setElementoId( elementoId );
        dia30Dez2015_troca.setTipoPresencaId( "4S" );
        dia30Dez2015_troca.setElementoReforcoReforcadoId( 764009L ); // troca pelo '900'
        dia30Dez2015_troca.setReforcoNaoReforcado( false );
        dia30Dez2015_troca.setInstalacaoId( instalacaoId_default );
        dia30Dez2015_troca.setPostoFuncionalId( postoFuncionalId_default );
        dia30Dez2015_troca.setTurnoId( 4L );
        insert( dia30Dez2015_troca );

    }
}
