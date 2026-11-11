package com.example.libraryjpa.service;

import com.example.libraryjpa.transfer.BookRequest;
import com.example.libraryjpa.transfer.BookResponse;
import com.example.libraryjpa.transfer.PageResponse;
import com.example.libraryjpa.exception.BookNotFoundException;
import com.example.libraryjpa.entity.Book;
import com.example.libraryjpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public PageResponse<BookResponse> findAll(String author, Pageable pageable) {
        Page<Book> books = (author == null)
                ? bookRepository.findAll(pageable)
                : bookRepository.findByAuthor(author, pageable);
        return PageResponse.from(books.map(BookResponse::from));
    }

    public List<BookResponse> findAvailable() {
        return bookRepository.findAvailableBooks().stream()
                .map(BookResponse::from)
                .toList();
    }

    public BookResponse findById(Long id) {
        return BookResponse.from(getBook(id));
    }

    @Transactional
    public BookResponse create(BookRequest request) {
        Book book = new Book(request.title(), request.author(), request.publishedYear());
        return BookResponse.from(bookRepository.save(book));
    }

    @Transactional
    public BookResponse update(Long id, BookRequest request) {
        Book book = getBook(id);
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setPublishedYear(request.publishedYear());
        return BookResponse.from(bookRepository.save(book));
    }

    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    private Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
