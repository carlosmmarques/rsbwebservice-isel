package pt.isel.ps.li61n.model.dal.web;

import org.junit.Before;
import org.junit.Test;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.UnidadeEstrutural;

import static org.junit.Assert.assertNotNull;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadesEstruturaisWebServiceTest {

    private UnidadesEstruturaisWebService _webService;

    @Before
    public void prepare(){
        _webService = new UnidadesEstruturaisWebService();
    }

    @Test public void insertUnidadeEstrutural() throws RepositoryException {
        //
        // Arrange
        //
        UnidadeEstrutural test = new UnidadeEstrutural();

        String designacao = "UnidadeEstrutural_Designacao";
        Long tipoUnidadeEstruturalId = 4L; // secção
        Long unidadeEstruturalMaeId = null;

        test.setDesignacao( designacao );
        test.setTipo_id( tipoUnidadeEstruturalId );
        test.setUnidadeEstruturalMae_id( unidadeEstruturalMaeId );

        //
        // Act
        //
        Long result = _webService.insert( test );

        //
        // Assert
        //
        assertNotNull( result );
    }
}
