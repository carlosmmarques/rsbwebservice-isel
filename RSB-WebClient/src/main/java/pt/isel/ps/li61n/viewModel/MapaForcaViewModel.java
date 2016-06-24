package pt.isel.ps.li61n.viewModel;

import pt.isel.ps.li61n.model.IMapaForcaLogic;
import pt.isel.ps.li61n.model.IPessoalLogic;
import pt.isel.ps.li61n.model.dal.mem.PresencasMemRepo;
import pt.isel.ps.li61n.model.entities.Pessoal;
import pt.isel.ps.li61n.model.entities.Presenca;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    public final Map< Integer, PresencaUI > presencas;

    // Versão 1
    //public final String[][] presencas;
    // public final String postoFuncional, idInterno;

    private IPessoalLogic _pessoalLogic;

    // Cache
    //private HashMap< Integer, Pessoal > _pessoal;

    public MapaForcaViewModel(
            String designacaoUnidadeEstrutural,
            Collection<Presenca> presencas,
            IPessoalLogic pessoalLogic
    ){
        _pessoalLogic = pessoalLogic;

        this.unidadeEstrutural = designacaoUnidadeEstrutural;

        this.periodo = getPeriodo( presencas );

        // Versão 2
        this.presencas = gerarTabelaPresencas( presencas );

        // Versão 1
        // this.presencas = gerarTabelaPresencas( presencas );;
        //this.postoFuncional = "B.Sap.";
        //this.idInterno = "123";
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

    private Map< Integer, PresencaUI > gerarTabelaPresencas( Collection<Presenca> presencas ) {

        //TODO: obter com base nas presencas
        int numDias = 31;  // TODO: (Reflexão) Se assumir que as presenças são sempre agrupadas em periodos
                            // , consigo obter o número de dias => (Problema) estar a criar dias que não vão
                            // ter registo (por exemplo, periodo ser um mês e as preenças são de 2 dias).

        int numTurnosPorDia = 2;
        int numElementos = 1;

        HashMap< Integer, PresencaUI > tabela = new HashMap<>();

        for( Presenca p : presencas ) {

            Integer elementoId = p.getElementoId();

            //
            // gerar ou obter contentor de registos presenças
            //
            String[] registos;
            if( tabela.containsKey( elementoId ) ){
                registos = tabela.get( elementoId ).presencas;
            }
            else{ // Criar registo de presenças

                Pessoal elemento = _pessoalLogic.getOne( elementoId );

                registos = new String[ numDias * numTurnosPorDia ];

                PresencaUI registoElemento = new PresencaUI(
                        elemento.getIdInterno()
                        ,elemento.getPostoFuncionalId()
                        ,registos
                );

                tabela.put( elementoId, registoElemento );
            }

            //
            // registar a presença
            //
            LocalDate data = p.getData();
            int diaMes = data.getDayOfMonth();

            // TODO: Melhorar para ter em conta o número de turnos...
            int idx = diaMes * numTurnosPorDia - numTurnosPorDia + ( p.getHoraInicio().getHour() > 12 ? 1 : 0 ) ;
            registos[ idx ] = p.getTipoPresencaId();
        }

        //
        // Preencher preenças "em branco"
        //

        for( PresencaUI presencaUI : tabela.values() ){

            String[] aux = presencaUI.presencas;

            for( int j = 0; j < aux.length ; j++ ){
                if( aux[ j ] == null )
                    aux[ j ] = " ";
            }
        }
        return tabela;
    }

    private String getPeriodo( Collection<Presenca> presencas ) {
        // TODO: IMPLEMENTAR!!!
        // v1 - Assumir que a vista é sempre ao periodo (mês)
        // v2- Não estar limitado ao mês, a ideia é percorer todas as presencas
        //   e procurar o a data inicio menor e maior (e/ou periodo ??).
        return "Mês de Dezembro de 2015";
    }

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

    public static void main( String[] args ){

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
}
