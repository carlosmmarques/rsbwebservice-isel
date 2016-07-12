package pt.isel.ps.li61n.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import pt.isel.ps.li61n.model.IPessoalLogic;
import pt.isel.ps.li61n.model.IPresencasLogic;
import pt.isel.ps.li61n.model.ITurnosLogic;
import pt.isel.ps.li61n.model.IUnidadesEstruturaisLogic;
import pt.isel.ps.li61n.model.entities.Elemento;
import pt.isel.ps.li61n.util.web.UrlGenerator;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static pt.isel.ps.li61n.RsbWebClientApplication.DATE_FORMATTER;
import static pt.isel.ps.li61n.RsbWebClientApplication.PESSOAL_URL;

/**
 * Created on 30/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PessoalControllerTest {

    private String _baseUrl = PESSOAL_URL;
    private IPessoalLogic _mockPessoalLogic;
    private IUnidadesEstruturaisLogic _mockUnidadesEstruturaisLogic;
    private IPresencasLogic _mockPresencasLogic;
    private ITurnosLogic _mockTurnoLogic;

    @Before
    public void prepare(){

        _mockPessoalLogic = mock( IPessoalLogic.class );
        _mockUnidadesEstruturaisLogic = mock( IUnidadesEstruturaisLogic.class );
        _mockPresencasLogic = mock( IPresencasLogic.class );
        _mockTurnoLogic = mock( ITurnosLogic.class );
    }

    @Test public void test_InsertView() throws Exception {

        // Arrange
        PessoalController controller = new PessoalController(
                                                _mockPessoalLogic
                                                ,_mockUnidadesEstruturaisLogic
                                                ,_mockPresencasLogic
                                                ,_mockTurnoLogic
        );

        MockMvc mockMvc = standaloneSetup( controller ).build();
        String url = _baseUrl + PessoalController.PAGE_PATH_INSERT;

        // Act & Assert
        mockMvc.perform( get( url ) )
                .andExpect(
                        view().name( PessoalController.VIEW_NAME_INSERT )
                )
                .andExpect( model().attributeExists( "action" ) );
        ;
    }

    //
    // Validar que ao fazer um POST com dados válidos, o cliente é redirecionado para a página com
    //  os detalhes do recurso gerado.
    //
    @Test public void testRegistoElemento() throws Exception {

        //
        // Arrange
        //
        String nome = "João Alberto Jardim";
        LocalDate dataNascimento = LocalDate.of( 1943, Month.FEBRUARY, 4 );
        int numMecanografico = 123456;
        int matricula = 1234;
        //String idInterno = "123";
        //String postoFuncionalId = "1";

        Elemento unsaved = new Elemento();
        unsaved.setNome( nome );
        unsaved.setDataNascimento( dataNascimento );
        unsaved.setNumMecanografico( numMecanografico );
        unsaved.setMatricula( 1234 );

        Long idReturned = 1L;

        when( _mockPessoalLogic.create( unsaved ) ).thenReturn( idReturned );

        PessoalController controller = new PessoalController(
                                                _mockPessoalLogic
                                                ,_mockUnidadesEstruturaisLogic
                                                ,_mockPresencasLogic
                                                ,_mockTurnoLogic
        );
        MockMvc mockMvc = standaloneSetup( controller ).build();

        //
        // Act & Assert
        //
        mockMvc.perform(
                    post( PESSOAL_URL )
                        .param( "numMecanografico", Integer.toString( numMecanografico ) )
                        .param( "nome", nome )
                        .param( "matricula", Integer.toString( matricula ) )
                        .param( "dataNascimento", dataNascimento.format( DATE_FORMATTER ) )
                )
                .andExpect( status().is3xxRedirection() )
                //.andExpect( redirectedUrl( UrlGenerator.detalhesPessoal( idReturned ) ) )
        ;


        // verificar que o método 'create' é chamado pelo menos uma vez
        verify( _mockPessoalLogic, atLeastOnce() ).create( unsaved );
    }


    @Test public void datepPattern(){
        String pattern = "dd/MM/yyyy";
        String test = "10/11/1988";
        LocalDate result = LocalDate.parse( test, DateTimeFormatter.ofPattern( pattern ) );

        assertEquals( "1988-11-10", result.toString() );

    }

}
