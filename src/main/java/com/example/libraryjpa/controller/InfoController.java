package com.example.libraryjpa.controller;

import com.example.libraryjpa.config.LibraryProperties;
import com.example.libraryjpa.transfer.AppInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class InfoController {

    private final LibraryProperties library;
    private final Environment environment;

    // GET /api/info
    @GetMapping
    public AppInfo info() {
        return new AppInfo(library.name(), library.loanDays(), library.supportEmail(),
                List.of(environment.getActiveProfiles()));
    }
}
