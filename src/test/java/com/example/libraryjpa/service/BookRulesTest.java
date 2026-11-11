package com.example.libraryjpa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookRulesTest {

    private BookRules rules;

    @BeforeEach
    void setUp() {
        rules = new BookRules();
    }

    @ParameterizedTest(name = "{0} is a valid year")
    @ValueSource(ints = {1450, 2008, 2100})
    void acceptsValidYears(int year) {
        assertTrue(rules.isValidYear(year));
    }

    @ParameterizedTest(name = "{0} is not a valid year")
    @ValueSource(ints = {1449, 2101, -5})
    void rejectsInvalidYears(int year) {
        assertFalse(rules.isValidYear(year));
    }

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @CsvSource({
            "'  Clean   Code ', Clean Code",
            "'Effective Java ', Effective Java",
            "'Head  First Java', Head First Java"
    })
    void normalizesTitles(String title, String expected) {
        assertEquals(expected, rules.normalizeTitle(title));
    }

    @ParameterizedTest(name = "title [{0}] is rejected")
    @NullAndEmptySource
    @ValueSource(strings = "   ")
    @DisplayName("null, empty and blank titles throw an exception")
    void rejectsMissingTitles(String title) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> rules.normalizeTitle(title));
        assertEquals("Title is required", ex.getMessage());
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "Robert C. Martin, RCM",
            "Joshua Bloch, JB",
            "Kathy Sierra, KS"
    })
    void buildsInitials(String author, String expected) {
        assertEquals(expected, rules.initials(author));
    }
}
