package pt.isel.ps.li61n.model.dal.web;

import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.junit.Before;
import org.junit.Test;
import pt.isel.ps.li61n.model.dal.exceptions.ElementoNotFoundException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.dal.web.exceptions.WebServiceException;
import pt.isel.ps.li61n.model.entities.Elemento;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

/**
 * Created on 07/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PessoalWebServiceTest {

    private PessoalWebService _webService;

    @Before
    public void prepare(){
        _webService = new PessoalWebService();
    }

    @Test public void getOneElementByNumMecanografico() throws ElementoNotFoundException {
        //
        // Arrange
        //
        int numMecanografico = 955615;

        //
        // Act
        //
        Elemento test = _webService.getOneByNumMecanografico( numMecanografico );

        //
        // Assert
        //
        assertNotNull( test );
        assertEquals( numMecanografico, test.getNumMecanografico() );
    }

    @Test public void getOneElementByNumMecanografico_nullValue() throws ElementoNotFoundException {
        //
        // Arrange
        //
        Integer numMecanografico = null;

        //
        // Act
        //
        Elemento test = _webService.getOneByNumMecanografico( numMecanografico );

        //
        // Assert
        //
        assertNull( test );
        //assertEquals( numMecanografico, test.getNumMecanografico() );
    }

    @Test public void getOneElementByNumMecanografico_notExistValue(){
        //
        // Arrange
        //
        int numMecanografico = -1;

        //
        // Act
        //
        try{
            _webService.getOneByNumMecanografico( numMecanografico );

            //
            // Assert
            //
            fail();
        }
        catch( ElementoNotFoundException e ){
            // OK!
        }
    }

    @Test public void insertElemento(){
        //
        // Arrange
        //
        Elemento test = new Elemento();
        int testMatricula = 1;
        test.setMatricula( testMatricula );

        int testNumMecanografico = 1_000_000;
        test.setNumMecanografico( testNumMecanografico );

        Long testPostoFuncional = 15L; // sem posto
        test.setPostoFuncionalId( testPostoFuncional );

        String testTipoPresenca = "0"; // não aplicavel
        test.setTipoPresencaId( testTipoPresenca );

        Long testTurno = 9L; // sem turno
        test.setTurnoId( testTurno );

        Long testInstalacao = 1L;  // Quartel Sede //TODO: Colocar todos os quartéis.
        test.setInstalacaoId( testInstalacao );

        LocalDate testDataNascimento = LocalDate.of( 1943, Month.FEBRUARY, 4 );
        test.setDataNascimento( testDataNascimento );

        String testNome = "João Alberto Jardim";
        test.setNome( testNome );

        //
        // Act
        //
        try{
            Long id = _webService.insert( test );

            //
            // Assert
            //
            assertNotNull( id );
        }
        catch( RepositoryException e ){
            fail( "Teste mal feito! Motivo: " + e.getMessage() );
        }
    }
}
