package com.ipi.jva350;

import static org.junit.jupiter.api.Assertions.*;

import com.ipi.jva350.model.Entreprise;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class EntrepriseTest {

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