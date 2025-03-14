package com.ipi.jva350;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class Jva350ApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures that the Spring application context loads successfully.
        // It is intentionally left empty because the @SpringBootTest annotation
        // will fail the test if the application context cannot be loaded.
    }

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> Jva350Application.main(new String[]{}));
    }
}
