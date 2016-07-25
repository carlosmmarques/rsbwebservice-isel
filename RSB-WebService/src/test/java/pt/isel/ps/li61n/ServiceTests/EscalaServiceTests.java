package pt.isel.ps.li61n.ServiceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pt.isel.ps.li61n.RsbWebserviceApplication;
import pt.isel.ps.li61n.controller.error.exception.ConflictoException;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.model.services.IEscalaService;
import pt.isel.ps.li61n.model.services.IPessoalService;
import pt.isel.ps.li61n.model.services.IPresencaService;

import java.util.Collection;

/**
 * EscalaServiceTests - Description
 * Created on 09/05/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RsbWebserviceApplication.class)
public class EscalaServiceTests {

    @Autowired
    IEscalaService escalaService;

    @Autowired
    IPresencaService presencaService;

    @Autowired
    IPessoalService pessoalService;

    @Test(expected = ConflictoException.class)
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE)
    public void testarPopularPresencasDeUmElemento() throws Exception {

        Collection<Presenca> presencas = escalaService.popularPresencas(
                presencaService.obterPeriodo(1l),
                pessoalService.obterElementoDoPessoal(1l)
        );
    }
}