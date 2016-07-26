package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.controller.UrlGenerator;
import pt.isel.ps.li61n.model.entities.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class MapaForcaViewModel {

    public final String unidadeEstrutural;

    public final String periodo;

    // Versão 2
    public final Collection< PresencaUI > presencas;

    // dias
    public final List< DiaInfo > dias;

    public  final int dimensaoRegisto;
    public final Collection<String> turnos;

    // Cache
    //private HashMap< Integer, Elemento > _pessoal;

    public MapaForcaViewModel(
            Periodo periodo
            ,UnidadeEstrutural unidadeEstrutural
            ,Collection< Presenca > presencas
    ){
        LocalDate dataInicio = periodo.getDataInicio();
        LocalDate dataFim = periodo.getDataFim();
        //TODO: Validar datas?? Supostamente já foram validadas antes da chamada deste método

        this.periodo = String.format( "%s - %s", dataInicio.toString(), dataFim.toString() );

        this.unidadeEstrutural = unidadeEstrutural.getDesignacao();


        // Versão 2
        //
        // Gerar informação sobre os dias do periodo
        //

        // Cache
        this.dias = new LinkedList<>();
        HashMap< LocalDate, DiaInfo > cacheDias = new HashMap<>();

        // Crias representantes dos dias
        LocalDate dataAux = dataInicio;
        while( !dataAux.isAfter( dataFim ) ){

            int diaDoMes = dataAux.getDayOfMonth();
            DayOfWeek diaDaSemana = dataAux.getDayOfWeek();
            boolean isFeriadoOuFimDeSemana =
                    diaDaSemana == DayOfWeek.SATURDAY
                            || diaDaSemana == DayOfWeek.SUNDAY
                            || isFeriado( diaDoMes, dataAux.getMonthValue() );

            DiaInfo dia = new DiaInfo( Integer.toString( diaDoMes ), isFeriadoOuFimDeSemana );
            this.dias.add( dia );

            cacheDias.put( dataAux, dia );
            dataAux = dataAux.plusDays( 1L );
        }


        // variavel auxiliar para contabilizar a dimensão do contentor de registo das presencas
        //int numRegistos = 0;

        //
        // Obter turnos presentes nas presencas e agrupa-los por dia.
        // Agrupar presenças pelo elemento que realizou as mesmas.
        //

        // cache
        HashMap< Long, PresencasElemento > cachePresencas = new HashMap<>();
        HashMap< TurnoInfo, TurnoInfo > cacheTurnoInfo = new HashMap<>();

        for( Presenca p : presencas ){

            // verificar se há registo do turno para a dataAux da presenca
            dataAux = p.getData();
            DiaInfo dia = cacheDias.get( dataAux );
            if( dia == null ){
                // TODO: Reflectir se alguma vez isto chega a acontecer!?
                throw new RuntimeException( "Erro na construção da página do MF!!" );
            }

            Turno turno = p.getTurno();
            LocalTime horaInicio = p.getHoraInicio();
            TurnoInfo turnoPresenca = new TurnoInfo( turno, horaInicio );

            // Utilizar classe já criada (cache) para evitar criar vários objectos com a mesma informação.
            TurnoInfo turnoCached = cacheTurnoInfo.get( turnoPresenca );
            if( turnoCached == null ){
                turnoCached = turnoPresenca;
                cacheTurnoInfo.put( turnoCached, turnoCached );
            }
            // registar turno
            //if( dia.addTurno( turnoCached ) ){
            //    numRegistos++;
            //}
            dia.addTurno( turnoCached );

            //
            // Agrupar presencas por elemento
            Elemento elemento = p.getElemento();
            Long elemId = elemento.getId();
            PresencasElemento pElem= cachePresencas.get( elemId );
            if( pElem == null ){
                pElem = new PresencasElemento( elemento );
                cachePresencas.put( elemId, pElem );
            }
            pElem.presencas.add( p );
        }

        //
        // gerar contentor de registos
        //

        // saber dimensão do contentor para conter todas as presencas no periodo
        this.dimensaoRegisto = setDimensao( this.dias );

        // registar presencas
        HashMap< Long,  PresencaUI > tabela = new HashMap<>();
        Collection< PresencasElemento > presencasElementos = cachePresencas.values();

        for( PresencasElemento pElem : presencasElementos ){

            List< Presenca > presencasElemento = pElem.presencas;
            for( Presenca p : presencasElemento ){

                // adicionar registo de presenca
                Elemento elemento = p.getElemento();
                Long elementoId = elemento.getId();

                String[] registo;

                if( tabela.containsKey( elementoId ) ){
                    registo = tabela.get( elementoId ).presencas;
                }
                else{ // Criar registo de presenças

                    registo = new String[ dimensaoRegisto ];

                    PresencaUI registoElemento = new PresencaUI(
                            elemento.getIdInterno()
                            ,p.getPostoFuncional() == null ? "" : p.getPostoFuncional().getDesignacao()
                            ,registo
                            , UrlGenerator.detalhesPessoal( elementoId )
                    );

                    tabela.put( elementoId, registoElemento );
                }

                dataAux = p.getData();

                // obter o representante do dia respectivo e calcular indice de registo de presenca
                DiaInfo diaInfo = cacheDias.get( dataAux );
                int idx = diaInfo.getIndex();
                int idxTurno = 0;
                for( TurnoInfo tf : diaInfo.turnos ){
                    if ( tf.turno.equals( p.getTurno() ) ){
                        break;
                    }
                    idxTurno++;
                }
                if( idxTurno < diaInfo.turnos.size() ){
                    idx += idxTurno;
                }

                registo[ idx ] = p.getTipoPresencaId();
            }
        }

        //
        // Preencher presenças "em branco"
        //
        Collection< PresencaUI > result = tabela.values();
        for( PresencaUI presencaUI : result ){

            String[] aux = presencaUI.presencas;
            for( int j = 0; j < aux.length ; j++ ){
                if( aux[ j ] == null )
                    aux[ j ] = " ";
            }
        }

        // prepara para o thymeleaf //TODO: arranjar alternativa
        Collection< String > turnos = new ArrayList<>( this.dimensaoRegisto );
        for( DiaInfo d : this.dias ){
            for( TurnoInfo t : d.turnos ){
                turnos.add( t.turno.getDesignacao() );
            }
        }
        this.turnos = turnos;

        this.presencas =  result;
    }

    private final static class TurnoInfo{
        public final Turno turno;
        public final LocalTime horaInicio;

        public TurnoInfo( Turno turno, LocalTime horaInicio ){
            this.turno = turno;
            this.horaInicio = horaInicio;
        }

        @Override
        public boolean equals( Object otherTurno ) {
            if ( this == otherTurno ) return true;

            TurnoInfo turnoInfo = (TurnoInfo) otherTurno;

            String thisDesignacao = this.turno.getDesignacao();
            String otherDesignacao = turnoInfo.turno.getDesignacao();

            return thisDesignacao.equals( otherDesignacao )
                    && this.horaInicio.equals( turnoInfo.horaInicio );
        }

        @Override
        public int hashCode() {
            int result = turno != null ? turno.hashCode() : 0;
            result = 31 * result + (horaInicio != null ? horaInicio.hashCode() : 0);
            return result;
        }

        @Override
        public String toString(){
            return "turno " + this.turno.getDesignacao() + " - " + this.horaInicio.toString();
        }
    }

    private final static class DiaInfo{
        public final String diaMes;
        public final List< TurnoInfo > turnos;
        public final boolean feriadoOuFimDeSemana;

        private int index;

        public DiaInfo( String diaMes, boolean feriadoOuFimDeSemana ) {
            this.diaMes = diaMes;
            this.feriadoOuFimDeSemana = feriadoOuFimDeSemana;
            this.turnos = new LinkedList<>();
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        /**
         * Verifica e adiciona se o turno presente em DiaInfo já contei o turno respectivo.
         * @param turno que se pretende adicionar
         * @return true se já o tem ou se
         */
        public boolean addTurno( TurnoInfo turno ){
            // verificar se a lista já tem o turno que se presente adicionar e se tiver,
            // adicionar por ordem de 'horaInicio'

            int listSize = turnos.size();
            Boolean added = null;

            for( int i = 0; i < listSize; i++ ){
                // verificar se já existe
                TurnoInfo t = turnos.get( i );
                if( t.equals( turno ) ){
                    added = false;
                    break;
                }
                if( turno.horaInicio.isBefore( t.horaInicio ) ){ // ordenar por horaInicio
                    turnos.add( i, turno );
                    added = true;
                    break;
                }
            }
            if( added == null ) {
                turnos.add( turno );
                added = true;
            }
            return added;
        }
        /*
        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder();
            builder.append( "{ dia " ).append( this.diaMes ).append( ": {" );
            for( TurnoInfo tf : this.turnos ){
                builder.append( ' ' );
                builder.append( tf.toString() );
                builder.append(',');
            }
            builder.deleteCharAt( builder.length() - 1 );
            builder.append( " }" );
            return builder.toString();
        }
        */
    }

    private class PresencasElemento {
        public final Elemento elemento;
        public final List< Presenca > presencas;

        public PresencasElemento( Elemento elemento ){
            this.elemento = elemento;
            this.presencas = new LinkedList<>();
        }
    }

    // Versão 2
    /*
    private Collection< PresencaUI > gerarTabelaPresencas(
                                    Collection<Presenca> presencas
                                    ,LocalDate dataInicio
                                    ,LocalDate dataFim
    ) {

    }*/

    /**
     *  Calcular a dimensão do array para conter os dias com os respectivos turnos.
     */
    private static int setDimensao(List< DiaInfo > diasPeriodo ) {
        int result = 0;
        int idx = 0;
        for( DiaInfo dia : diasPeriodo ){
            dia.setIndex( idx );
            int numTurnos = dia.turnos.size();
            if( numTurnos == 0 ) { // Na eventualidade de um dia nao ter registo de turnos.
                numTurnos = 1;
            }
            idx += numTurnos;
            result += numTurnos;
        }
        return result;
    }

    /**
     * Verificar se determinado dia de um mês corresponde a um feriado.
     *
     */
    private boolean isFeriado( int diaDoMes, int valorMes ) {
        // TODO: Implementar
        return false;
    }

    // Versão 1
    /*
    private static String[][] gerarTabelaPresencas( Collection<Presenca> presencas ) {

        int numDias = 31;
        int numTurnosPorDia = 2;
        int numElementos = 1;

        String[][] tabela = new String[ numElementos ][ numDias * numTurnosPorDia ];

        for( Presenca p : presencas ) {
            LocalDate data = p.getData();
            int diaMes = data.getDayOfMonth();

            int idx = diaMes * numTurnosPorDia - numTurnosPorDia + ( p.getHoraInicio().getHour() > 12 ? 1 : 0 ) ;
            tabela[ 0 ][ idx ] = p.getTipoPresencaId();
        }

        for( int i = 0; i < tabela.length; i++ ){
            for( int j = 0; j < tabela[i].length; j++ ){
                if( tabela[i][j] == null )
                    tabela[i][j] = " ";
            }
        }
        return tabela;
    }
    */

    ////////////////////////////////////
    //
    // Testes
    //
    ////////////////////////////////////
    /*
    public MapaForcaViewModel(Map<Integer, PresencaUI> presencas_v2) {
        this.presencas_v2 = null;
        this.unidadeEstrutural = null;
        this.periodo = null;
        this.presencas = null;
        this.postoFuncional = null;
        this.idInterno = null;
    }
    private void test(){
      Collection< Presenca > presencas =
                new PresencasMemRepo().selectAll();


        String[][] tabela = gerarTabelaPresencas( presencas );


        for( int i = 0; i < tabela.length; i++ ){
            System.out.print( "|" );
            for( int j = 0; j < tabela[i].length; j++ ){
                System.out.print( tabela[i][j] );
            }
            System.out.println( "|" );
        }

    }
    */

    private static DiaInfo testAddTurno(){
        Turno t1 = new Turno(1L, "1");
        Turno t2 = new Turno(2L, "2");
        Turno t3 = new Turno(3L, "3");
        Turno t4 = new Turno(4L, "4");

        LocalTime horaDia = LocalTime.of( 8, 0 );
        LocalTime horaTarde = LocalTime.of( 15, 0 );
        LocalTime horaNoite = LocalTime.of( 20, 0 );

        DiaInfo dia = new DiaInfo( "1", false );

        dia.addTurno( new TurnoInfo( t1, horaDia ) );
        dia.addTurno( new TurnoInfo( t4, horaNoite ) );
        dia.addTurno( new TurnoInfo( t1, horaDia ) );
        dia.addTurno( new TurnoInfo( t2, horaTarde ) );
        dia.addTurno( new TurnoInfo( t1, horaDia.minusHours( 1 ) ) );
        dia.addTurno( new TurnoInfo( t4, horaNoite.plusHours( 1 ) ) );

        return dia;
    }

    private static void testMetodo_getDimensao(){

        List< DiaInfo> dias = new LinkedList<>();
        DiaInfo dia1 = testAddTurno(); // 5 turnos
        DiaInfo dia2 = testAddTurno(); // 5 turnos
        dias.add( dia1 );
        dias.add( dia2 );

        int total = setDimensao( dias ); // esperado 10;

    }

//    public static void main( String[] args ) {
//
//        testAddTurno();
//        // testMetodo_getDimensao();
//
//    }
}
