package com.example.libraryjpa.service;

public class BookRules {

    public static final int MIN_YEAR = 1450;
    public static final int MAX_YEAR = 2100;

    // true between 1450 and 2100 (inclusive)
    public boolean isValidYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    // "  Clean   Code " -> "Clean Code"
    public String normalizeTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        return title.trim().replaceAll("\\s+", " ");
    }

    // "Robert C. Martin" -> "RCM"
    public String initials(String author) {
        StringBuilder result = new StringBuilder();
        for (String word : author.trim().split("\\s+")) {
            result.append(Character.toUpperCase(word.charAt(0)));
        }
        return result.toString();
    }
}
