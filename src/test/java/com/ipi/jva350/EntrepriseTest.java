package com.ipi.jva350;

import static org.junit.jupiter.api.Assertions.*;

import com.ipi.jva350.model.Entreprise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EntrepriseTest {
    @ParameterizedTest
    @CsvSource({
            "2023-01-01, true",  // Jour de l'An
            "2023-05-01, true",  // Fête du Travail
            "2023-12-25, true",  // Noël
            "2023-07-14, true",  // Fête Nationale
            "2023-08-15, true",  // Assomption
            "2023-11-11, true",  // Armistice
            "2023-04-01, false", // Jour non férié
            "2023-10-31, false"  // Jour non férié
    })
    public void testEstJourFerie(String date, boolean expected) {
        LocalDate localDate = LocalDate.parse(date);
        assertEquals(expected, Entreprise.estJourFerie(localDate));
    }
    @Test
    public void testEstDansPlage_DateWithinRange() {
        LocalDate date = LocalDate.of(2023, 6, 15);
        LocalDate start = LocalDate.of(2023, 6, 1);
        LocalDate end = LocalDate.of(2023, 6, 30);
        assertTrue(Entreprise.estDansPlage(date, start, end));
    }

    @Test
    public void testEstDansPlage_DateBeforeRange() {
        LocalDate date = LocalDate.of(2023, 5, 31);
        LocalDate start = LocalDate.of(2023, 6, 1);
        LocalDate end = LocalDate.of(2023, 6, 30);
        assertFalse(Entreprise.estDansPlage(date, start, end));
    }

    @Test
    public void testEstDansPlage_DateAfterRange() {
        LocalDate date = LocalDate.of(2023, 7, 1);
        LocalDate start = LocalDate.of(2023, 6, 1);
        LocalDate end = LocalDate.of(2023, 6, 30);
        assertFalse(Entreprise.estDansPlage(date, start, end));
    }

    @Test
    public void testEstDansPlage_DateOnStartBoundary() {
        LocalDate date = LocalDate.of(2023, 6, 1);
        LocalDate start = LocalDate.of(2023, 6, 1);
        LocalDate end = LocalDate.of(2023, 6, 30);
        assertTrue(Entreprise.estDansPlage(date, start, end));
    }

    @Test
    public void testEstDansPlage_DateOnEndBoundary() {
        LocalDate date = LocalDate.of(2023, 6, 30);
        LocalDate start = LocalDate.of(2023, 6, 1);
        LocalDate end = LocalDate.of(2023, 6, 30);
        assertTrue(Entreprise.estDansPlage(date, start, end));
    }
}