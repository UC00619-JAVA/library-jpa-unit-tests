package com.example.libraryjpa.service;

public class LateFeeCalculator {

    public static final double FEE_PER_DAY = 0.50;
    public static final double MAX_FEE = 10.00;

    // fee for returning a book late
    public double calculate(int daysLate) {
        if (daysLate < 0) {
            throw new IllegalArgumentException("Days late cannot be negative");
        }
        return Math.min(daysLate * FEE_PER_DAY, MAX_FEE);
    }
}
