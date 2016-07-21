package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPeriodosRepository;
import pt.isel.ps.li61n.model.dal.IPresencasRepository;
import pt.isel.ps.li61n.model.dal.ITiposPresencaRepository;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
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

    /*
    public Collection<Presenca> getAllPresencas() {
        try {
            return _presencasRepo.selectAll();
        }
        catch( RepositoryException e ){
            throw new RuntimeException( e );
        }
    }
    */

    @Override
    public Collection< Presenca > getPresencasByPeriodo( Long periodoId ) {
        return _presencasRepo.selectPresencasByPeriodo( periodoId );
    }

    @Override
    public Collection<Periodo> getAllPeriodos() {
        try {
            return _periodoRepo.selectAll();
        }
        catch (RepositoryException e) {
           throw new RuntimeException( e );
        }
    }

    @Override
    public Long insertPeriodo( Periodo novoPeriodo ) {
        // TODO: Validar dataInicio
            // > Not null
            // > inferiro à dataFim

        // TODO: Validar dataFim
            // > Not null
            // > superior à dataInicio (se necesário)

        Long id = null;
         try {
            id = _periodoRepo.insert( novoPeriodo );
        }
        catch( RepositoryException e ) {
            throw new RuntimeException( e );
        }
        return id;
    }

    @Override
    public Periodo getOnePeriodo( Long periodoId ) {
        try {
            return  _periodoRepo.selectOne( periodoId );
        }
        catch (RepositoryException e) {
          throw new RuntimeException( e );
        }
    }

    @Override
    public Collection<Presenca> getPresencasByPeriodoAndInstalacao( Long periodoId, Long instalacaoId ) {
        //try {
            return  _presencasRepo.selectPresencasByPeriodoAndInstalacao( periodoId, instalacaoId );
        //}
        //catch (RepositoryException e) {
        //    throw new RuntimeException( e );
       // }
    }
}
