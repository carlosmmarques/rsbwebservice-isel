package pt.isel.ps.li61n.model.dal.web;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadesEstruturaisWebServiceTest {

//    private UnidadesEstruturaisWebService _webService;
//
//    @Before
//    public void prepare(){
//        _webService = new UnidadesEstruturaisWebService();
//    }
//
//    @Test public void insertUnidadeEstrutural() throws RepositoryException {
//        //
//        // Arrange
//        //
//        UnidadeEstrutural test = new UnidadeEstrutural();
//
//        String designacao = "UnidadeEstrutural_Designacao";
//        Long tipoUnidadeEstruturalId = 4L; // secção
//        Long unidadeEstruturalMaeId = null;
//
//        test.setDesignacao( designacao );
//        test.setTipo_id( tipoUnidadeEstruturalId );
//        test.setUnidadeEstruturalMae_id( unidadeEstruturalMaeId );
//
//        //
//        // Act
//        //
//        Long result = _webService.insert( test );
//
//        //
//        // Assert
//        //
//        assertNotNull( result );
//    }
//
//    @Test public void insertInstalcao() throws RepositoryException {
//        //
//        // Arrange
//        //
//        Instalacao test = new Instalacao();
//
//        String designacao = "instalacao_designacao";
//        String descricao = "Isto é uma DESCRIÇÃO de uma instalacao que pertence à unidade estrutural '1' localizada na 'instalacao_localizacao'";
//        String localizacao = "instalacao_localizacao";
//
//        Long unidadeEstrututalId = 1L;
//
//        test.setDesignacao( designacao );
//        test.setDescricao( descricao );
//        test.setLocalizacao(localizacao );
//        test.setUnidadeEstruturalId( unidadeEstrututalId );
//
//        //
//        // Act
//        //
//        Long result = _webService.insertInstalacao( test );
//
//        //
//        // Assert
//        //
//        assertNotNull( result );
//    }
//
//
//    @Test public void insertInstalcao_nullValues() throws RepositoryException {
//        //
//        // Arrange
//        //
//        Instalacao test = new Instalacao();
//        Long ueId = null; // is required!
//        test.setUnidadeEstruturalId( ueId );
//
//        //
//        // Act
//        //
//        Long result = null;
//        try {
//            result = _webService.insertInstalacao(test);
//
//        //
//        // Assert
//        //
//            fail();
//        }
//        catch( NullPointerException e ){
//            assertEquals( "Identificador da unidadade estrutural é obrigatório!", e.getMessage() );
//        }
//        assertNull( result );
//    }
}