package com.example.libraryjpa.config;

import com.example.libraryjpa.entity.Book;
import com.example.libraryjpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            bookRepository.saveAll(List.of(
                    new Book("Clean Code", "Robert C. Martin", 2008),
                    new Book("The Clean Coder", "Robert C. Martin", 2011),
                    new Book("Effective Java", "Joshua Bloch", 2018),
                    new Book("Head First Java", "Kathy Sierra", 2005)));
            log.info("Sample books loaded");
        }
        log.info("Total books: {}", bookRepository.count());
    }
}
