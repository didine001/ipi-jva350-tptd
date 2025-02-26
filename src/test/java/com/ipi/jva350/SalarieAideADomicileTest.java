package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    void testConstructeur_SalarieAideADomicile() {
        String nom = "Dupont";
        LocalDate moisDebutContrat = LocalDate.of(2020, 1, 1);
        LocalDate moisEnCours = LocalDate.of(2024, 2, 1);
        double joursTravaillesAnneeN = 150;
        double congesPayesAcquisAnneeN = 10;
        double joursTravaillesAnneeNMoins1 = 200;
        double congesPayesAcquisAnneeNMoins1 = 20;
        double congesPayesPrisAnneeNMoins1 = 5;
        SalarieAideADomicile salarie = new SalarieAideADomicile(
                nom, moisDebutContrat, moisEnCours,
                joursTravaillesAnneeN, congesPayesAcquisAnneeN,
                joursTravaillesAnneeNMoins1, congesPayesAcquisAnneeNMoins1, congesPayesPrisAnneeNMoins1
        );
        assertThat(salarie.getNom()).isEqualTo(nom);
        assertThat(salarie.getMoisDebutContrat()).isEqualTo(moisDebutContrat);
        assertThat(salarie.getMoisEnCours()).isEqualTo(moisEnCours);
        assertThat(salarie.getJoursTravaillesAnneeN()).isEqualTo(joursTravaillesAnneeN);
        assertThat(salarie.getCongesPayesAcquisAnneeN()).isEqualTo(congesPayesAcquisAnneeN);
        assertThat(salarie.getJoursTravaillesAnneeNMoins1()).isEqualTo(joursTravaillesAnneeNMoins1);
        assertThat(salarie.getCongesPayesAcquisAnneeNMoins1()).isEqualTo(congesPayesAcquisAnneeNMoins1);
        assertThat(salarie.getCongesPayesPrisAnneeNMoins1()).isEqualTo(congesPayesPrisAnneeNMoins1);
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
    @Test
    void testSetAndGetMoisEnCours() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate moisEnCours = LocalDate.of(2024, 3, 1);
        salarie.setMoisEnCours(moisEnCours);
        assertThat(salarie.getMoisEnCours()).isEqualTo(moisEnCours);
    }
    @Test
    void testSetAndGetMoisDebutContrat() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate moisDebutContrat = LocalDate.of(2020, 1, 1);
        salarie.setMoisDebutContrat(moisDebutContrat);
        assertThat(salarie.getMoisDebutContrat()).isEqualTo(moisDebutContrat);
    }
    @Test
    void testEquals_SameObject() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setId(1L);
        salarie.setNom("Dupont");
        assertThat(salarie.equals(salarie)).isTrue();
    }
    @Test
    void testEquals_DifferentObjectsSameValues() {
        SalarieAideADomicile salarie1 = new SalarieAideADomicile();
        salarie1.setId(1L);
        salarie1.setNom("Dupont");
        SalarieAideADomicile salarie2 = new SalarieAideADomicile();
        salarie2.setId(1L);
        salarie2.setNom("Dupont");
        assertThat(salarie1.equals(salarie2)).isTrue();
    }
    @Test
    void testEquals_DifferentObjectsDifferentValues() {
        SalarieAideADomicile salarie1 = new SalarieAideADomicile();
        salarie1.setId(1L);
        salarie1.setNom("Dupont");
        SalarieAideADomicile salarie2 = new SalarieAideADomicile();
        salarie2.setId(2L);
        salarie2.setNom("Martin");
        assertThat(salarie1.equals(salarie2)).isFalse();
    }
    @Test
    void testEquals_NullObject() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setId(1L);
        salarie.setNom("Dupont");
        assertThat(salarie.equals(null)).isFalse();
    }
    @Test
    void testHashCode() {
        SalarieAideADomicile salarie1 = new SalarieAideADomicile();
        salarie1.setId(1L);
        salarie1.setNom("Dupont");
        SalarieAideADomicile salarie2 = new SalarieAideADomicile();
        salarie2.setId(1L);
        salarie2.setNom("Dupont");
        assertThat(salarie1.hashCode()).isEqualTo(salarie2.hashCode());
    }
    @Test
    void testHashCode_DifferentValues() {
        SalarieAideADomicile salarie1 = new SalarieAideADomicile();
        salarie1.setId(1L);
        salarie1.setNom("Dupont");

        SalarieAideADomicile salarie2 = new SalarieAideADomicile();
        salarie2.setId(2L);
        salarie2.setNom("Martin");
        assertThat(salarie1.hashCode()).isNotEqualTo(salarie2.hashCode());
    }
    @Test
    public void testEstJourOuvrable() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        LocalDate lundi = LocalDate.of(2024, 2, 26);
        LocalDate dimanche = LocalDate.of(2024, 2, 25);

        Assertions.assertTrue(salarie.estJourOuvrable(lundi));
        Assertions.assertFalse(salarie.estJourOuvrable(dimanche));
    }
    @Test
    public void testGetCongesPayesRestantAnneeNMoins1() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setCongesPayesAcquisAnneeNMoins1(15);
        salarie.setCongesPayesPrisAnneeNMoins1(5);
        Assertions.assertEquals(10, salarie.getCongesPayesRestantAnneeNMoins1());
    }
}