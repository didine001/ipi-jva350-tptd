package com.ipi.jva350;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class Jva350ApplicationTests {
    @Test
    void contextLoads() {
    }
    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> Jva350Application.main(new String[]{}));
    }
}
