package com.ipi.jva350;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository repository;

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
}
