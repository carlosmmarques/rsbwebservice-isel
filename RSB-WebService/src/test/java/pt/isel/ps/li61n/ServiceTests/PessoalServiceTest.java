package pt.isel.ps.li61n.ServiceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.model.entities.ElementoDoPessoal;
import pt.isel.ps.li61n.model.services.IPessoalService;

import java.util.Collection;
import java.util.Optional;

/**
 * PessoalServiceTest - Description
 * Created on 15/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RsbWebserviceApplication.class)
public class PessoalServiceTest {

    @Autowired
    IPessoalService pessoalService;

    @Test
    public void testObterUmElementoDoPessoal() throws Exception{
        ElementoDoPessoal elementoDoPessoal = pessoalService.obterElementoDoPessoal(1l);

        Assert.isTrue(elementoDoPessoal.getIdInterno().equals("14"));
    }

    @Test
    public void testObterElementosdoPessoal() throws Exception{
        Collection<ElementoDoPessoal> elementosDoPessoal = pessoalService.obterElementosDoPessoal(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
        );
        Assert.notEmpty(elementosDoPessoal);
    }

}