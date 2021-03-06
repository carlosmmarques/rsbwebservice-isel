package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.IPeriodosRepository;
import pt.isel.ps.li61n.model.entities.Periodo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created on 20/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ConditionalOnMissingBean( IPeriodosRepository.class )
@Component
public class PeriodosRepository implements IPeriodosRepository {

    private HashMap< Long, Periodo > _repo;
    private long _repoSize;

    public PeriodosRepository() {
        _repo = new HashMap<>();
        _repoSize = 1;

        populate();
    }

    @Override
    public Long insert( Periodo element ) {
        Long id = _repoSize++;
        element.setId( id );
        _repo.put( id, element );
        return  id;
    }

    @Override
    public Periodo selectOne( Long aLong ) {
        return _repo.get( aLong );
    }

    @Override
    public Collection<Periodo> selectAll() {
        return _repo.values();
    }

    @Override
    public void delete( Long aLong ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update( Periodo aLong ) {
        throw new UnsupportedOperationException();
    }

    private void populate(){

        Periodo dezembro2015 = new Periodo();
        dezembro2015.setDataInicio( LocalDate.of( 2015, Month.DECEMBER, 1 ) );
        dezembro2015.setDataFim( LocalDate.of( 2015, Month.DECEMBER, 31 ) );
        this.insert( dezembro2015 );

        Periodo janeiro2016 = new Periodo();
        janeiro2016.setDataInicio( LocalDate.of( 2016, Month.JANUARY, 1 ) );
        janeiro2016.setDataFim( LocalDate.of( 2016, Month.JANUARY, 31 ) );
        this.insert( janeiro2016 );

        /*
        Periodo fevereiro2015 = new Periodo();
        fevereiro2015.setDataInicio( LocalDate.of( 2015, Month.FEBRUARY, 1 ) );
        fevereiro2015.setDataFim( LocalDate.of( 2015, Month.FEBRUARY, 28 ) );
        this.insert( fevereiro2015 );

        Periodo marco2015 = new Periodo();
        marco2015.setDataInicio( LocalDate.of( 2015, Month.MARCH, 1 ) );
        marco2015.setDataFim( LocalDate.of( 2015, Month.MARCH, 31 ) );
        this.insert( marco2015 );

        Periodo abril2015 = new Periodo();
        abril2015.setDataInicio( LocalDate.of( 2015, Month.APRIL, 1 ) );
        abril2015.setDataFim( LocalDate.of( 2015, Month.APRIL, 30 ) );
        this.insert( abril2015 );

        Periodo maio2015 = new Periodo();
        maio2015.setDataInicio( LocalDate.of( 2015, Month.MAY, 1 ) );
        maio2015.setDataFim( LocalDate.of( 2015, Month.MAY, 31 ) );
        this.insert( maio2015 );

        Periodo junho2015 = new Periodo();
        junho2015.setDataInicio( LocalDate.of( 2015, Month.JUNE, 1 ) );
        junho2015.setDataFim( LocalDate.of( 2015, Month.JUNE, 30 ) );
        this.insert( junho2015 );

        Periodo julho2015 = new Periodo();
        julho2015.setDataInicio( LocalDate.of( 2015, Month.JULY, 1 ) );
        julho2015.setDataFim( LocalDate.of( 2015, Month.JULY, 31 ) );
        this.insert( julho2015 );

        Periodo agosto2015 = new Periodo();
        agosto2015.setDataInicio( LocalDate.of( 2015, Month.AUGUST, 1 ) );
        agosto2015.setDataFim( LocalDate.of( 2015, Month.AUGUST, 31 ) );
        this.insert( agosto2015 );

        Periodo setembro2015 = new Periodo();
        setembro2015.setDataInicio( LocalDate.of( 2015, Month.SEPTEMBER, 1 ) );
        setembro2015.setDataFim( LocalDate.of( 2015, Month.SEPTEMBER, 30 ) );
        this.insert( setembro2015 );

        Periodo outubro2015 = new Periodo();
        outubro2015.setDataInicio( LocalDate.of( 2015, Month.OCTOBER, 1 ) );
        outubro2015.setDataFim( LocalDate.of( 2015, Month.OCTOBER, 31 ) );
        this.insert( outubro2015 );

        Periodo novembro2015 = new Periodo();
        novembro2015.setDataInicio( LocalDate.of( 2015, Month.NOVEMBER, 1 ) );
        novembro2015.setDataFim( LocalDate.of( 2015, Month.NOVEMBER, 30 ) );
        this.insert( novembro2015 );
        */

    }
}
