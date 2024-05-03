package com.nowakpawel.library.web.service;

import com.nowakpawel.library.web.entity.Book;
import com.nowakpawel.library.web.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private final String ISBN = "123456457";
    private final String TITLE = "Getting Things Done";
    private final String AUTHOR = "D. Allen";
    private Book book = new Book();

    @BeforeEach
    void setup() {
        book = new Book(ISBN, TITLE, AUTHOR);
    }

    @AfterEach
    void tearDown() {
        book = null;
    }

    @Test
    void shouldFindAllBooksFromRepository() {
        List<Book> testBookList = List.of(
                new Book("123456789", "It", "S. King"),
                new Book("987654321", "Pet Cementary", "S. King"),
                new Book("159835490", "A time to kill", "John Grisham")
        );

        when(bookRepository.findAll()).thenReturn(testBookList);
        assertEquals(bookService.findAll().size(), 3);
    }

    @Test
    void shouldAddBook() {
        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.addBook(book);

        assertAll(
                () -> assertEquals(ISBN, savedBook.getIsbn()),
                () -> assertEquals(TITLE, savedBook.getTitle()),
                () -> assertEquals(AUTHOR, savedBook.getAuthor())
        );
    }

    @Test
    void shouldFindBookById() {
        when(bookRepository.findById(ISBN)).thenReturn(Optional.of(book));

        Book foundBook = bookService.findByIsbn(ISBN);

        assertAll(
                () -> assertEquals(ISBN, foundBook.getIsbn()),
                () -> assertEquals(TITLE, foundBook.getTitle()),
                () -> assertEquals(AUTHOR, foundBook.getAuthor())
        );
    }

    @Test
    void shouldNotFoundBookByInvalidIds() {
        final String ISBN = "000";

        when(bookRepository.findById(ISBN)).thenReturn(Optional.empty());
        Book foundBook = bookService.findByIsbn(ISBN);

        assertNull(foundBook);
    }

    @Test
    void shouldUpdateBook() {
        final String NEW_ISBN = "456";
        final String NEW_TITLE = "New book";
        final String NEW_AUTHOR = "New Author";

        Book book = new Book(ISBN, TITLE, AUTHOR);
        Book newBook = new Book(NEW_ISBN, NEW_TITLE, NEW_AUTHOR);

        when(bookRepository.findById(ISBN)).thenReturn(Optional.of(book));

        bookService.updateBook(newBook, ISBN);

        verify(bookRepository).save(book);
    }

    @Test
    void shouldDeleteBook() {
        final String ISBN = "123";
        bookService.deleteBook(ISBN);

        verify(bookRepository).deleteById(ISBN);
    }

}