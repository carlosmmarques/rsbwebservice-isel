package pt.isel.ps.li61n;

import org.apache.tomcat.jni.Local;
import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created on 17/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class LocalDateTest {

    @Test public void testLocalDateEquals() {
        //
        // Arrange
        //
        int dia = 12;
        int mes = 02;
        int ano = 2006;

        String data = "2006-02-12";

        //
        // Act
        //
        LocalDate date1 = LocalDate.parse(data);
        LocalDate date2 = LocalDate.of(ano, mes, dia);

        //
        // Assert
        //
        assertTrue( date1 != date2 );
        assertEquals(date1, date2);
    }

    @Test public void testLocalDate_getFromMap() {
        //
        // Arrange
        //
        int dia = 12;
        int mes = 02;
        int ano = 2006;

        String expect = "2006-02-12";

        LocalDate datePut = LocalDate.parse( expect );
        LocalDate dateGet = LocalDate.of(ano, mes, dia);

        HashMap< LocalDate, String > map = new HashMap<>();
        map.put( datePut, expect );

        //
        // Act
        //
        String test = map.get( dateGet );

        //
        // Assert
        //
        assertEquals( expect, test );
    }
}
