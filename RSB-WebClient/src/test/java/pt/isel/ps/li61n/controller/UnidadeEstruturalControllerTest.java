package pt.isel.ps.li61n.controller;

/**
 * Created on 03/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UnidadeEstruturalControllerTest {

//    private String _ue_url_base = UNIDADES_ESTRUTURAIS_URL;
//
//    //private MockMvc _mockMvc;
//    private IUnidadesEstruturaisLogic _mockLogic;
//
//    @Before
//    public void prepare(){
//        _mockLogic = mock( IUnidadesEstruturaisLogic.class );
//    }
//
//    /**
//     * Verificar que é gerada a vista correcta com os respectivos dados para o pedido
//        GET /unidadesEstruturais
//     */
//    @Test public void test_IndexPage() throws Exception {
//        //
//        // Arrange
//        //
//        UnidadeEstruturalController controller =
//                new UnidadeEstruturalController( _mockLogic );
//
//        MockMvc mockMvc = standaloneSetup( controller ).build();
//
//        // para dar mais expressividade ao código e para tentar criar 'template' de teste
//        String request_url = UNIDADES_ESTRUTURAIS_URL;
//        String view_name = UnidadeEstruturalController.VIEW_NAME_UE_ALL;
//        String model_element_name = UnidadeEstruturalController.MODEL_UE_LIST;
//
//        ///
//        // Act & Assert //TODO: Tentar converter estes testes para usar assertEquals.
//        //
//        mockMvc.perform( get( request_url ) )
//                .andExpect(
//                        view().name( view_name ) )
//                .andExpect(
//                    model().attributeExists( model_element_name ) )
//        ;
//    }
//
//    /**
//     * Verificar que é gerada a view correcta para o pedido
//      GET /unidadesEstruturais/{id}
//     */
//     @Test public void test_DetailPage() throws Exception {
//         //
//         // Arrange
//         //
//         Long idTest = new Long( 1 );
//         UnidadeEstrutural elementTest = new UnidadeEstrutural( idTest);
//
//         when( _mockLogic.getOne( idTest ) )
//                .thenReturn( elementTest );
//
//         UnidadeEstruturalController controller =
//                new UnidadeEstruturalController( _mockLogic );
//
//         MockMvc mockMvc = standaloneSetup( controller ).build();
//
//         // para dar mais expressividade ao código e para tentar criar 'template' de teste
//         String request_url = UNIDADES_ESTRUTURAIS_URL + "/" + idTest;
//         String view_name = UnidadeEstruturalController.VIEW_NAME_UE_DETAILS;
//         String model_element_name = UnidadeEstruturalController.MODEL_UE_ELEMENT;
//
//         //TODO: Tentar converter estes testes para usar assertEquals.
//         //
//         // Act & Assert
//         //
//         mockMvc.perform( get( request_url ) )
//             .andExpect(
//                 view().name( view_name ) )
//             .andExpect(
//                 model().attributeExists( model_element_name ) )
//             .andExpect(
//                 model().attribute(
//                            model_element_name,
//                            is( elementTest )
//                 )
//             );
//    }
//
//    //TODO: Fazer testes para pedidos de elementos q não existem.
    // por exemplo, não existir elemento com id '3'.
}
