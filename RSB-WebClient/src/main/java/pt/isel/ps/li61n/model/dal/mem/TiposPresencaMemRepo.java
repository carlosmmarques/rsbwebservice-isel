package pt.isel.ps.li61n.model.dal.mem;

import org.springframework.stereotype.Component;
import pt.isel.ps.li61n.model.dal.ITiposPresencaRepository;
import pt.isel.ps.li61n.model.entities.TipoPresenca;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created on 21/06/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@Component
public class TiposPresencaMemRepo implements ITiposPresencaRepository {

    private HashMap<String, TipoPresenca > _repo;

    public TiposPresencaMemRepo( ){
        _repo = new HashMap<>();

        populate();
    }


    public void insert( TipoPresenca element, String id ) {
        element.setId( id );
        _repo.put( id, element );
    }

    @Override
    public String insert( TipoPresenca element ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TipoPresenca selectOne( String id ) {
        return _repo.get( id );
    }

    @Override
    public Collection<TipoPresenca> selectAll() {
        return _repo.values();
    }

    @Override
    public void delete(String s) {
        throw new NotImplementedException();
    }

    @Override
    public void update(String s) {
        throw new NotImplementedException();
    }

    private void populate() {

        TipoPresenca doenca = new TipoPresenca();
            doenca.setId( "A" );
            doenca.setAusencia( true );
            doenca.setReforco( false );
            doenca.setTipoPresencaReforcoId( null );
            doenca.setAbreviatura( "Doença" );
            doenca.setDescricao( "Situação de Doente" );
        _repo.put( doenca.getId(), doenca );

        TipoPresenca acdServ = new TipoPresenca();
            acdServ.setId( "B" );
            acdServ.setAusencia( true );
            acdServ.setReforco( false );
            acdServ.setTipoPresencaReforcoId( null );
            acdServ.setAbreviatura( "Ac.Serv" );
            acdServ.setDescricao( "Acidente em Serviço" );
            _repo.put( acdServ.getId(), acdServ );

        TipoPresenca casamento = new TipoPresenca();
            casamento.setId( "C" );
            casamento.setAusencia( true );
            casamento.setReforco( false );
            casamento.setTipoPresencaReforcoId( null );
            casamento.setAbreviatura( "Casamento" );
            casamento.setDescricao( "Dispensa de Casamento" );
            _repo.put( casamento.getId(), casamento );

        TipoPresenca nojo = new TipoPresenca();
            nojo.setId( "D" );
            nojo.setAusencia( true );
            nojo.setReforco( false );
            nojo.setTipoPresencaReforcoId( null );
            nojo.setAbreviatura( "Nojo" );
            nojo.setDescricao( "Dispensa de Nojo" );
            _repo.put( nojo.getId(), nojo );

        TipoPresenca assFam = new TipoPresenca();
            assFam.setId( "E" );
            assFam.setAusencia( true );
            assFam.setReforco( false );
            assFam.setTipoPresencaReforcoId( null );
            assFam.setAbreviatura( "Ass.Fam.Men." );
            assFam.setDescricao( "Assistência à familia a menores" );
            _repo.put( assFam.getId(), assFam );

        TipoPresenca actSind = new TipoPresenca();
            actSind.setId( "SG" );
            actSind.setAusencia( true );
            actSind.setReforco( false );
            actSind.setTipoPresencaReforcoId( null );
            actSind.setAbreviatura( "Act.Sind." );
            actSind.setDescricao( "Actividade Sindical" );
            _repo.put( actSind.getId(), actSind );

        TipoPresenca falta = new TipoPresenca();
            falta.setId( "H" );
            falta.setAusencia( true );
            falta.setReforco( false );
            falta.setTipoPresencaReforcoId( null );
            falta.setAbreviatura( "F.Injust." );
            falta.setDescricao( "Falta injustificada" );
            _repo.put( falta.getId(), falta );

        TipoPresenca licPatern = new TipoPresenca();
            licPatern.setId( "I" );
            licPatern.setAusencia( true );
            licPatern.setReforco( false );
            licPatern.setTipoPresencaReforcoId( null );
            licPatern.setAbreviatura( "Lic.Patern." );
            licPatern.setDescricao( "Licença de Paternidade" );
            _repo.put( licPatern.getId(), licPatern );

        TipoPresenca inactivo = new TipoPresenca();
            inactivo.setId( "J" );
            inactivo.setAusencia( true );
            inactivo.setReforco( false );
            inactivo.setTipoPresencaReforcoId( null );
            inactivo.setAbreviatura( "Inact.Quadro" );
            inactivo.setDescricao( "Inactividade Quadro" );
            _repo.put( inactivo.getId(), inactivo );

        TipoPresenca familia = new TipoPresenca();
            familia.setId( "F" );
            familia.setAusencia( true );
            familia.setReforco( false );
            familia.setTipoPresencaReforcoId( null );
            familia.setAbreviatura( "Ass.Familia" );
            familia.setDescricao( "Assistência à família" );
            _repo.put( familia.getId(), familia );

        TipoPresenca parental = new TipoPresenca();
            parental.setId( "L" );
            parental.setAusencia( true );
            parental.setReforco( false );
            parental.setTipoPresencaReforcoId( null );
            parental.setAbreviatura( "Lic.Parental" );
            parental.setDescricao( "Licença Parental" );
            _repo.put( parental.getId(), parental );

        TipoPresenca nascimento = new TipoPresenca();
            nascimento.setId( "M" );
            nascimento.setAusencia( true );
            nascimento.setReforco( false );
            nascimento.setTipoPresencaReforcoId( null );
            nascimento.setAbreviatura( "Lic.Nasc." );
            nascimento.setDescricao( "Licença de Nascimento" );
            _repo.put( nascimento.getId(), nascimento );

        TipoPresenca hospital = new TipoPresenca();
            hospital.setId( "N" );
            hospital.setAusencia( true );
            hospital.setReforco( false );
            hospital.setTipoPresencaReforcoId( null );
            hospital.setAbreviatura( "Hospital" );
            hospital.setDescricao( "Hospital" );
            _repo.put( hospital.getId(), hospital );

        TipoPresenca feriasConf = new TipoPresenca();
            feriasConf.setId( "X" );
            feriasConf.setAusencia( true );
            feriasConf.setReforco( false );
            feriasConf.setTipoPresencaReforcoId( null );
            feriasConf.setAbreviatura( "Férias Confirmadas" );
            feriasConf.setDescricao( "Férias confirmadas" );
            _repo.put( feriasConf.getId(), feriasConf );

        TipoPresenca feriasPorConfirmar = new TipoPresenca();
            feriasPorConfirmar.setId( "Z" );
            feriasPorConfirmar.setAusencia( true );
            feriasPorConfirmar.setReforco( false );
            feriasPorConfirmar.setTipoPresencaReforcoId( null );
            feriasPorConfirmar.setAbreviatura( "Férias N/Confirm." );
            feriasPorConfirmar.setDescricao( "Férias não confirmadas" );
            _repo.put( feriasPorConfirmar.getId(), feriasPorConfirmar );

        TipoPresenca transferencia = new TipoPresenca();
            transferencia.setId( "T" );
            transferencia.setAusencia( true );
            transferencia.setReforco( false );
            transferencia.setTipoPresencaReforcoId( null );
            transferencia.setAbreviatura( "Transferência" );
            transferencia.setDescricao( "Transferência para outra Unidade" );
            _repo.put( transferencia.getId(), transferencia );

        TipoPresenca naoAplicavel = new TipoPresenca();
            naoAplicavel.setId( "0" );
            naoAplicavel.setAusencia( true );
            naoAplicavel.setReforco( false );
            naoAplicavel.setTipoPresencaReforcoId( null );
            naoAplicavel.setAbreviatura( "N/A" );
            naoAplicavel.setDescricao( "Não aplicável" );
            _repo.put( naoAplicavel.getId(), naoAplicavel );

        TipoPresenca servInterno = new TipoPresenca();
            servInterno.setId( "0" );
            servInterno.setAusencia( false );
            servInterno.setReforco( false );
            servInterno.setTipoPresencaReforcoId( null );
            servInterno.setAbreviatura( "Serviço Interno" );
            servInterno.setDescricao( "Serviços internos" );
            _repo.put( servInterno.getId(), servInterno );

        TipoPresenca dispensa = new TipoPresenca();
            dispensa.setId( "S0" );
            dispensa.setAusencia( true );
            dispensa.setReforco( false );
            dispensa.setTipoPresencaReforcoId( null );
            dispensa.setAbreviatura( "Disp.Compensação" );
            dispensa.setDescricao( "Dispensa por compensação" );
            _repo.put( dispensa.getId(), dispensa );

        TipoPresenca cpo = new TipoPresenca();
            cpo.setId( "S1" );
            cpo.setAusencia( false );
            cpo.setReforco( false );
            cpo.setTipoPresencaReforcoId( "1S" );
            cpo.setAbreviatura( "CPO/CH Cidade" );
            cpo.setDescricao( "Comandantede de Permanência às Operações/ Chefe de Serviço à Cidade" );
            _repo.put( cpo.getId(), cpo );

        TipoPresenca ch1a = new TipoPresenca();
            ch1a.setId( "S2" );
            ch1a.setAusencia( false );
            ch1a.setReforco( false );
            ch1a.setTipoPresencaReforcoId( "2S" );
            ch1a.setAbreviatura( "Ch.1ªInterv." );
            ch1a.setDescricao( "Chefe de 1ª Intervenção" );
            _repo.put( ch1a.getId(), ch1a );

        TipoPresenca subchefe = new TipoPresenca();
            subchefe.setId( "S3" );
            subchefe.setAusencia( false );
            subchefe.setReforco( false );
            subchefe.setTipoPresencaReforcoId( "3S" );
            subchefe.setAbreviatura( "S/chefe" );
            subchefe.setDescricao( "Subchefe" );
            _repo.put( subchefe.getId(), subchefe );

        TipoPresenca linha = new TipoPresenca();
            linha.setId( "S4" );
            linha.setAusencia( false );
            linha.setReforco( false );
            linha.setTipoPresencaReforcoId( "4S" );
            linha.setAbreviatura( "Linha" );
            linha.setDescricao( "Operacional de Linha" );
            _repo.put( linha.getId(), linha );

        TipoPresenca motorista = new TipoPresenca();
            motorista.setId( "S5" );
            motorista.setAusencia( false );
            motorista.setReforco( false );
            motorista.setTipoPresencaReforcoId( "5S" );
            motorista.setAbreviatura( "Motorista" );
            motorista.setDescricao( "Operacional Motorista" );
            _repo.put( motorista.getId(), motorista );

        TipoPresenca merg_mac_nisac = new TipoPresenca();
            merg_mac_nisac.setId( "S6" );
            merg_mac_nisac.setAusencia( false );
            merg_mac_nisac.setReforco( false );
            merg_mac_nisac.setTipoPresencaReforcoId( "6S" );
            merg_mac_nisac.setAbreviatura( "Mg/Mac/Nisac" );
            merg_mac_nisac.setDescricao( "Mergulhador/Maqueiro/Apoio Especializado" );
            _repo.put( merg_mac_nisac.getId(), merg_mac_nisac );

        TipoPresenca formacao = new TipoPresenca();
            formacao.setId( "S9" );
            formacao.setAusencia( false );
            formacao.setReforco( false );
            formacao.setTipoPresencaReforcoId( "9S" );
            formacao.setAbreviatura( "Formação" );
            formacao.setDescricao( "Formação" );
            _repo.put( formacao.getId(), formacao );

        TipoPresenca cpo_reforco = new TipoPresenca();
            cpo_reforco.setId( "1S" );
            cpo_reforco.setAusencia( false );
            cpo_reforco.setReforco( true );
            cpo_reforco.setTipoPresencaReforcoId( null );
            cpo_reforco.setAbreviatura( "CPO/CH Cidade (Reforço)" );
            cpo_reforco.setDescricao( "Reforço de CPO/CH Cidade" );
            _repo.put( cpo_reforco.getId(), cpo_reforco );

        TipoPresenca ch1a_reforco = new TipoPresenca();
            ch1a_reforco.setId( "2S" );
            ch1a_reforco.setAusencia( false );
            ch1a_reforco.setReforco( true );
            ch1a_reforco.setTipoPresencaReforcoId( null );
            ch1a_reforco.setAbreviatura( "Ch.1ªInterv. (Reforço)" );
            ch1a_reforco.setDescricao( "Reforço de Chefe de 1ª Intervenção" );
            _repo.put( ch1a.getId(), ch1a );

        TipoPresenca subchefe_reforco = new TipoPresenca();
            subchefe_reforco.setId( "3S" );
            subchefe_reforco.setAusencia( false );
            subchefe_reforco.setReforco( true );
            subchefe_reforco.setTipoPresencaReforcoId( null );
            subchefe_reforco.setAbreviatura( "S/chefe (Reforço)" );
            subchefe_reforco.setDescricao( "Reforço de Subchefe" );
            _repo.put( subchefe_reforco.getId(), subchefe_reforco );

        TipoPresenca linha_reforco = new TipoPresenca();
        linha_reforco.setId( "4S" );
        linha_reforco.setAusencia( false );
        linha_reforco.setReforco( true );
        linha_reforco.setTipoPresencaReforcoId( null );
        linha_reforco.setAbreviatura( "Linha (Reforço)" );
        linha_reforco.setDescricao( "Reforço de Operacional de Linha" );
        _repo.put( linha_reforco.getId(), linha_reforco );

        TipoPresenca motorista_reforco = new TipoPresenca();
        motorista_reforco.setId( "5S" );
        motorista_reforco.setAusencia( false );
        motorista_reforco.setReforco( true );
        motorista_reforco.setTipoPresencaReforcoId( null );
        motorista_reforco.setAbreviatura( "Motorista (Reforço)" );
        motorista_reforco.setDescricao( "Reforço de Operacional Motorista" );
        _repo.put( motorista_reforco.getId(), motorista_reforco );

        TipoPresenca merg_mac_nisac_reforco = new TipoPresenca();
        merg_mac_nisac_reforco.setId( "6S" );
        merg_mac_nisac_reforco.setAusencia( false );
        merg_mac_nisac_reforco.setReforco( true );
        merg_mac_nisac_reforco.setTipoPresencaReforcoId( null );
        merg_mac_nisac_reforco.setAbreviatura( "Mg/Mac/Nisac (Reforço)" );
        merg_mac_nisac_reforco.setDescricao( "Reforço de Mergulhador/Maqueiro/Apoio Especializado" );
        _repo.put( merg_mac_nisac_reforco.getId(), merg_mac_nisac_reforco );
    }
}
