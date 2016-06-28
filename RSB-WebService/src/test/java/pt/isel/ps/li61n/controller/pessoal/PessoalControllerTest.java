package pt.isel.ps.li61n.controller.pessoal;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * PessoalControllerTest - Description
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class PessoalControllerTest {

    @Test
    public void testObterElementosDoPessoal() throws Exception {
        PessoalController pessoalController = new PessoalController();
        MockMvc mockMvc = standaloneSetup(pessoalController).build();
        mockMvc
                .perform(get("/pessoal"))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[*].path", containsInAnyOrder(
                        "id",
                        "uri_pessoa",
                        "uri_instalacaoPorOmissao",
                        "uri_postoFuncionalPorOmissao",
                        "uri_tipoPresencaPorOmissao",
                        "uri_turnoPorOmissao",
                        "uri_categoria",
                        "idInterno",
                        "Matricula",
                        "nome",
                        "dataIngresso",
                        "numMecanografico"
        )));
   }

    @Test
    public void testObterUmElementoDoPessoal() throws Exception {

    }

    @Test
    public void testObterRegistosDeFormacaoDeUmElemento() throws Exception {

    }

    @Test
    public void testObterUmRegistoDeFormacaoDeUmElemento() throws Exception {

    }

    @Test
    public void testObterResponsabilidadesOperacionaisDeUmElemento() throws Exception {

    }

    @Test
    public void testInserirUmElementoDoPessoal() throws Exception {

    }

    @Test
    public void testActualizarUmElementoDoPessoal() throws Exception {

    }

    @Test
    public void testActualizarFormacaoDeElementoDoPessoal() throws Exception {

    }

    @Test
    public void testEliminarElementoDoPessoal() throws Exception {

    }
}