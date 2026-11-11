package com.example.libraryjpa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LateFeeCalculatorTest {

    private LateFeeCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new LateFeeCalculator();
    }

    @Test
    @DisplayName("3 days late costs 1.50 euros")
    void threeDaysLate() {
        double fee = calculator.calculate(3);
        assertEquals(1.50, fee, 0.001);
    }

    @Test
    void negativeDaysThrowAnException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(-1));
        assertEquals("Days late cannot be negative", ex.getMessage());
    }

    @Test
    void theFeeHasAMaximum() {
        assertAll(
                () -> assertEquals(10.0, calculator.calculate(20), 0.001),
                () -> assertEquals(10.0, calculator.calculate(365), 0.001));
    }

    @ParameterizedTest(name = "{0} days late -> {1} euros")
    @CsvSource({"0, 0.00", "1, 0.50", "3, 1.50", "20, 10.00"})
    void calculatesTheFee(int daysLate, double expected) {
        assertEquals(expected, calculator.calculate(daysLate), 0.001);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -365})
    void rejectsNegativeDays(int daysLate) {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(daysLate));
    }
}
