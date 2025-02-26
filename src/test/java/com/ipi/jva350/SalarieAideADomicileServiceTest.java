package com.ipi.jva350;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.service.SalarieAideADomicileService;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SalarieAideADomicileServiceTest {

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }
    @InjectMocks
    private SalarieAideADomicileService salarieAideADomicileService;

    @Mock
    private SalarieAideADomicileRepository repository;
    private SalarieAideADomicile salarieAideADomicile;

    @Test
    public void testAjouteConge_SalarieSansDroitConges() {
        SalarieAideADomicile salarie = mock(SalarieAideADomicile.class);
        LocalDate startDate = LocalDate.of(2022, 11, 1);
        LocalDate endDate = LocalDate.of(2022, 11, 5);

        Mockito.when(salarie.aLegalementDroitADesCongesPayes()).thenReturn(false);
        SalarieException e = assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, startDate, endDate)
        );
        assertEquals("N'a pas légalement droit à des congés payés !", e.getMessage());
    }

    @Test
    public void testAjouteConge_AucuneDateDeConge() {
        SalarieAideADomicile salarie = mock(SalarieAideADomicile.class);
        LocalDate startDate = LocalDate.of(2022, 11, 1);
        LocalDate endDate = LocalDate.of(2022, 11, 5);

        Mockito.when(salarie.aLegalementDroitADesCongesPayes()).thenReturn(true);
        Mockito.when(salarie.calculeJoursDeCongeDecomptesPourPlage(startDate, endDate)).thenReturn(new LinkedHashSet<>());
        SalarieException e = assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, startDate, endDate)
        );
        assertEquals("Pas besoin de congés !", e.getMessage());
    }

    @Test
    public void testAjouteConge_SalariePrendCongeAvantMoisEnCours() {
        SalarieAideADomicile salarie = mock(SalarieAideADomicile.class);
        LocalDate startDate = LocalDate.of(2022, 10, 1);
        LocalDate endDate = LocalDate.of(2022, 10, 5);

        Mockito.when(salarie.aLegalementDroitADesCongesPayes()).thenReturn(true);
        Mockito.when(salarie.calculeJoursDeCongeDecomptesPourPlage(startDate, endDate))
                .thenReturn(new LinkedHashSet<>(Arrays.asList(startDate, LocalDate.of(2022, 10, 2))));
        Mockito.when(salarie.getMoisEnCours()).thenReturn(LocalDate.of(2022, 11, 1));

        SalarieException e = assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, startDate, endDate)
        );
        assertEquals("Pas possible de prendre de congé avant le mois en cours !", e.getMessage());
    }

    @Test
    public void testCreerSalarie_Succes() throws SalarieException {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Durand");

        when(repository.findByNom("Durand")).thenReturn(null);

        salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        verify(repository, times(1)).save(salarie);
    }

    @Test
    public void testCreerSalarie_NomDejaPris() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Dupont");

        when(repository.findByNom("Dupont")).thenReturn(new SalarieAideADomicile());

        assertThrows(SalarieException.class, () -> salarieAideADomicileService.creerSalarieAideADomicile(salarie));
    }
    @Test
    public void testCreerSalarie_IdDejaFournie() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setId(1L);
        salarie.setNom("Martin");

        assertThrows(SalarieException.class, () -> salarieAideADomicileService.creerSalarieAideADomicile(salarie));
    }
}