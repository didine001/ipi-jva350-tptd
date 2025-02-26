package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository repository;
    @BeforeEach
    void setUp() {
        SalarieAideADomicile salarie1 = new SalarieAideADomicile();
        salarie1.setCongesPayesAcquisAnneeNMoins1(30);
        salarie1.setCongesPayesPrisAnneeNMoins1(24);
        SalarieAideADomicile salarie2 = new SalarieAideADomicile();
        salarie2.setCongesPayesAcquisAnneeNMoins1(20);
        salarie2.setCongesPayesPrisAnneeNMoins1(15);
        repository.save(salarie1);
        repository.save(salarie2);
    }
    @Test
    void testFindByNom_WhenSalarieExists() {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Dupont");
        repository.save(salarie);
        SalarieAideADomicile result = repository.findByNom("Dupont");
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Dupont");
    }
    @Test
    void testFindByNom_WhenSalarieDoesNotExist() {
        SalarieAideADomicile result = repository.findByNom("Inconnu");
        assertThat(result).isNull();
    }
    @Test
    void testPartCongesPrisTotauxAnneeNMoins1() {
        Double result = repository.partCongesPrisTotauxAnneeNMoins1();
        assertEquals(0.78, result, 0.01);
    }
}
