package com.ipi.jva350;

import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.mockito.Mockito.*;@ExtendWith(MockitoExtension.class)
public class SalarieAideADomicileServiceTest {

    @InjectMocks
    private SalarieAideADomicileService salarieAideADomicileService;

    @Test
    public void testAjouteConge_SalarieSansDroitConges() {

        SalarieAideADomicile salarie = mock(SalarieAideADomicile.class);
        LocalDate startDate = LocalDate.of(2022, 11, 1);
        LocalDate endDate = LocalDate.of(2022, 11, 5);

        Mockito.when(salarie.aLegalementDroitADesCongesPayes()).thenReturn(false);
        SalarieException e = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, startDate, endDate)
        );
        Assertions.assertEquals(e.getMessage(), "N'a pas légalement droit à des congés payés !");
    }

    @Test
    public void testAjouteConge_AucuneDateDeConge() {
        SalarieAideADomicile salarie = mock(SalarieAideADomicile.class);
        LocalDate startDate = LocalDate.of(2022, 11, 1);
        LocalDate endDate = LocalDate.of(2022, 11, 5);

        Mockito.when(salarie.aLegalementDroitADesCongesPayes()).thenReturn(true);
        Mockito.when(salarie.calculeJoursDeCongeDecomptesPourPlage(startDate, endDate)).thenReturn(new LinkedHashSet<>());
        SalarieException e = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, startDate, endDate)
        );
        Assertions.assertEquals(e.getMessage(), "Pas besoin de congés !");
    }

    @Test
    public void testAjouteConge_SalariePrendCongeAvantMoisEnCours() {
        // Given
        SalarieAideADomicile salarie = mock(SalarieAideADomicile.class);
        LocalDate startDate = LocalDate.of(2022, 10, 1);
        LocalDate endDate = LocalDate.of(2022, 10, 5);

        Mockito.when(salarie.aLegalementDroitADesCongesPayes()).thenReturn(true);
        Mockito.when(salarie.calculeJoursDeCongeDecomptesPourPlage(startDate, endDate))
                .thenReturn(new LinkedHashSet<>(Arrays.asList(startDate, LocalDate.of(2022, 10, 2))));

        Mockito.when(salarie.getMoisEnCours()).thenReturn(LocalDate.of(2022, 11, 1));

        SalarieException e = Assertions.assertThrows(SalarieException.class, () ->
                salarieAideADomicileService.ajouteConge(salarie, startDate, endDate)
        );

        Assertions.assertEquals(e.getMessage(), "Pas possible de prendre de congé avant le mois en cours !");
    }
}
