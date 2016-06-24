package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPeriodoRepository;
import pt.isel.ps.li61n.model.dal.IPresencasRepository;
import pt.isel.ps.li61n.model.dal.ITiposPresencaRepository;
import pt.isel.ps.li61n.model.entities.Periodo;
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

    private IPeriodoRepository  _periodoRepo;
    private IPresencasRepository _presencasRepo;
    private ITiposPresencaRepository _tiposPresencaRepo;

    @Autowired
    public MapaForcaLogic(
            IPeriodoRepository periodoRepository
            ,IPresencasRepository presencasRepository
            ,ITiposPresencaRepository tiposPresencaRepository
    ) {
        this._periodoRepo = periodoRepository;
        this._presencasRepo = presencasRepository;
        this._tiposPresencaRepo = tiposPresencaRepository;
    }

    @Override
    public Collection<Presenca> getAllPresencas() {
        return _presencasRepo.selectAll();
    }
}
