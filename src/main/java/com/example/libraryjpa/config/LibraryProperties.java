package com.example.libraryjpa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "library")
public record LibraryProperties(String name, int loanDays, String supportEmail) {
}
