package com.ipi.jva350;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LinkedHashSetStringTest{

    private static final String DELIMITER = ",";

    @Test
    void testConvertToDatabaseColumn() {
        LinkedHashSet<LocalDate> localDates = new LinkedHashSet<>();
        localDates.add(LocalDate.of(2023, 5, 1));
        localDates.add(LocalDate.of(2023, 5, 2));
        localDates.add(LocalDate.of(2023, 5, 3));

        String result = convertToDatabaseColumn(localDates);
        assertEquals("2023-05-01,2023-05-02,2023-05-03", result);
        localDates.clear();
        result = convertToDatabaseColumn(localDates);
        assertEquals("", result);
        result = convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void testConvertToEntityAttribute() {
        String datesString = "2023-05-01,2023-05-02,2023-05-03";
        LinkedHashSet<LocalDate> result = convertToEntityAttribute(datesString);
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(LocalDate.of(2023, 5, 1)));
        assertTrue(result.contains(LocalDate.of(2023, 5, 2)));
        assertTrue(result.contains(LocalDate.of(2023, 5, 3)));
        datesString = "";
        result = convertToEntityAttribute(datesString);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        result = convertToEntityAttribute(null);
        assertNull(result);
    }
    public String convertToDatabaseColumn(LinkedHashSet<LocalDate> localDates) {
        return localDates == null ? null
                : localDates.stream().map(d -> d.toString()).collect(Collectors.joining(DELIMITER));
    }

    public LinkedHashSet<LocalDate> convertToEntityAttribute(String datesString) {
        return datesString == null ? null
                : new LinkedHashSet<>(Arrays.stream(datesString.split(DELIMITER))
                .filter(d -> !d.isEmpty()).map(ds -> LocalDate.parse(ds)).collect(Collectors.toList()));
    }
}
