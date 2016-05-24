package pt.isel.ps.li61n.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup
        .MockMvcBuilders.standaloneSetup;

/**
 * Created on 24/05/2016.
 *
 * @author  Carlos Marques - carlosmmarques@gmail.com
 *          Tiago Venturinha - tventurinha@gmail.com
 */
public class IndexControllerTest {

    @Test
    public void testIndexPage() throws Exception {

        IndexController controller = new IndexController();
        MockMvc mockMvc = standaloneSetup(controller).build(); // Set up MockMvc
        mockMvc
            .perform( get( "/" ) ) // Perform GET /
            .andExpect( view().name( "index" ) ); // Expect index view
    }
}
