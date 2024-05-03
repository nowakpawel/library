package com.nowakpawel.library.web.service;

import com.nowakpawel.library.web.entity.Book;
import com.nowakpawel.library.web.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(isbn);

        if (!optionalBook.isEmpty()) {
            return optionalBook.get();
        }

        log.error("Book with %s does not exists".formatted(isbn));
        return null;
    }

    public Book updateBook(Book newBook, String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(isbn);
        if (optionalBook.isEmpty()) {
            log.error("Book with isbn %s not exists".formatted(isbn));
            return null;
        }

        Book book = optionalBook.get();

        book.setIsbn(newBook.getIsbn());
        book.setAuthor(newBook.getAuthor());
        book.setTitle(newBook.getTitle());

        return bookRepository.save(book);
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
