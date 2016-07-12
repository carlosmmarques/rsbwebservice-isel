package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPeriodosRepository;
import pt.isel.ps.li61n.model.dal.IPresencasRepository;
import pt.isel.ps.li61n.model.dal.ITiposPresencaRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Presenca;

import java.util.Collection;

/**
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class MapaForcaLogic implements IMapaForcaLogic {

    private IPeriodosRepository _periodoRepo;
    private IPresencasRepository _presencasRepo;
    private ITiposPresencaRepository _tiposPresencaRepo;

    @Autowired
    public MapaForcaLogic(
            IPeriodosRepository periodoRepository
            ,IPresencasRepository presencasRepository
            ,ITiposPresencaRepository tiposPresencaRepository
    ) {
        this._periodoRepo = periodoRepository;
        System.out.println(
                this.getClass().getSimpleName() +
                        " is using " + periodoRepository.getClass().getSimpleName()
        );

        this._presencasRepo = presencasRepository;
        System.out.println(
                this.getClass().getSimpleName() +
                        " is using " + presencasRepository.getClass().getSimpleName()
        );

        this._tiposPresencaRepo = tiposPresencaRepository;
        System.out.println(
                this.getClass().getSimpleName() +
                        " is using " + tiposPresencaRepository.getClass().getSimpleName()
        );
    }

    @Override
    public Collection<Presenca> getAllPresencas() {
        try {
            return _presencasRepo.selectAll();
        }
        catch( RepositoryException e ){
            throw new RuntimeException( e );
        }
    }
}
