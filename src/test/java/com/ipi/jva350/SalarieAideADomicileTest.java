package com.ipi.jva350;
import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalarieAideADomicileTest {

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