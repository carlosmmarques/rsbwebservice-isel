package pt.isel.ps.li61n.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPresencasRepository;
import pt.isel.ps.li61n.model.dal.ITiposPresencaRepository;
import pt.isel.ps.li61n.model.dal.exceptions.PropertyEntityException;
import pt.isel.ps.li61n.model.dal.exceptions.RepositoryException;
import pt.isel.ps.li61n.model.entities.Presenca;
import pt.isel.ps.li61n.model.entities.TipoPresenca;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created on 11/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class PresencasLogic implements IPresencasLogic {

    private IPresencasRepository _presencasRepo;
    private ITiposPresencaRepository _tipoPresencasRepo;

    @Autowired
    public PresencasLogic(
            IPresencasRepository presencasRepo
            ,ITiposPresencaRepository tipoPresencasRepo
    ) {
        this._presencasRepo = presencasRepo;
        System.out.println(
                this.getClass().getSimpleName() +
                        " is using " + presencasRepo.getClass().getSimpleName()
        );

        this._tipoPresencasRepo = tipoPresencasRepo;
        System.out.println(
                this.getClass().getSimpleName() +
                        " is using " + tipoPresencasRepo.getClass().getSimpleName()
        );
    }

    @Override
    public Collection<Presenca> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public Presenca getOne(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Long create(Presenca element) throws PropertyEntityException {
        throw new NotImplementedException();
    }

    @Override
    public Collection< TipoPresenca > getAllTiposPresenca() {
        try {
            return _tipoPresencasRepo.selectAll();
        }
        catch( RepositoryException e ) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public Collection<TipoPresenca> getTiposPresencaBy(boolean isAusente, boolean isReforco) {

        return getAllTiposPresenca()
                .stream()
                .filter(
                    tipoPresenca -> tipoPresenca.isAusencia() == isAusente &&
                                            tipoPresenca.isReforco() == isReforco
                )
                .collect( Collectors.toList() );

    }
}
