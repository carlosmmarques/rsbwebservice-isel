package pt.isel.ps.li61n.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import pt.isel.ps.li61n.model.IMapaForcaLogic;
import pt.isel.ps.li61n.model.IPessoalLogic;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static pt.isel.ps.li61n.RsbWebClientApplication.MAPA_FORCA_URL;

/**
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class MapaForcaControllerTest {

    //private MockMvc _mockMvc;
    private IMapaForcaLogic _mapaForcaMmockLogic;
    private IPessoalLogic _pessoalMockLogic;

    @Before
    public void prepare(){

        _mapaForcaMmockLogic = mock( IMapaForcaLogic.class );
        _pessoalMockLogic = mock( IPessoalLogic.class );
    }

    @Test
    public void testIndexPage() throws Exception {

        MapaForcaController controller = new MapaForcaController(
                _mapaForcaMmockLogic
                ,_pessoalMockLogic
        );
        MockMvc mockMvc = standaloneSetup( controller ).build(); // Set up MockMvc
        mockMvc
                .perform( get( MAPA_FORCA_URL ) ) // Perform GET
                .andExpect( view().name( controller.VIEW_NAME_INDEX ) ); // Expect index view
    }
}
