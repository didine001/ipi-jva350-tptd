package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalarieAideADomicileTest {

    @ParameterizedTest
    @CsvSource({
            "2022-11-01, 2022-11-05, 4",
            "2022-12-24, 2022-12-26, 1",
            "2022-11-01, 2022-11-01, 0",
            "2022-11-01, 2022-11-02, 1",
            "2022-11-05, 2022-11-01, 0"
    })
    void testCalculJoursDeCongeDecomptesPourPlage(String startDate, String endDate, int expectedDays) {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        LinkedHashSet<LocalDate> result = salarie.calculeJoursDeCongeDecomptesPourPlage(start, end);
        assertEquals(expectedDays, result.size(), "Le nombre de jours de congé calculé est incorrect");
    }
    @Test
    void testALegalementDroitADesCongesPayes_CasGeneral() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(20);
        assertTrue(salarie.aLegalementDroitADesCongesPayes());
    }

    @Test
    void testALegalementDroitADesCongesPayes_CasLimite() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(10);
        assertFalse(salarie.aLegalementDroitADesCongesPayes());
    }

    @Test
    void testALegalementDroitADesCongesPayes_CasSansDroit() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(5);
        assertFalse(salarie.aLegalementDroitADesCongesPayes());
    }

    @Test
    void testALegalementDroitADesCongesPayes_CasAvecUnJourDePlusQueLeLimite() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setJoursTravaillesAnneeNMoins1(11);
        assertTrue(salarie.aLegalementDroitADesCongesPayes());
    }
}