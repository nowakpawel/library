package com.nowakpawel.library.web.controller;

import com.google.gson.Gson;
import com.nowakpawel.library.web.entity.Book;
import com.nowakpawel.library.web.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LibraryController.class)
class LibraryControllerTest {
    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;
    private final String ROOT_PATH = "/library";
    private final String ISBN = "1234567890";
    private final String TITLE = "Getting Things Done";
    private final String AUTHOR = "D. Allen";

    @Test
    void shouldGetAllBooks() throws Exception {
        List<Book> bookList = List.of(
                new Book("123456789", "It", "S. King"),
                new Book("987654321", "Pet Cementary", "S. King"),
                new Book("159835490", "A time to kill", "John Grisham")
        );

        when(bookService.findAll()).thenReturn(bookList);

        mockMvc.perform(get(ROOT_PATH + "/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(new Gson().toJson(bookList)));
    }

    @Test
    void shouldCreateBook() throws Exception {
        Book book = new Book(ISBN, TITLE, AUTHOR);

        when(bookService.addBook(book)).thenReturn(book);

        mockMvc.perform(post(ROOT_PATH + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"isbn\": \"%s\", \"title\": \"%s\", \"author\": \"%s\" }".formatted(ISBN, TITLE, AUTHOR)))
                .andExpect(status().is(200))
                .andExpect(content().json(new Gson().toJson(book)));
    }

    @Test
    void shouldFindBookById() throws Exception {
        Book book = new Book(ISBN, TITLE, AUTHOR);

        when(bookService.findByIsbn(ISBN)).thenReturn(book);

        mockMvc.perform(get(ROOT_PATH + "/" + ISBN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(new Gson().toJson(book)));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        final String NEW_ISBN = "12345";
        String NEW_TITLE = "Test book";
        String NEW_AUTHOR = "Some Author";

        Book newBook = new Book(NEW_ISBN, NEW_TITLE, NEW_AUTHOR);

        when(bookService.updateBook(newBook, ISBN)).thenReturn(newBook);

        mockMvc.perform(put(ROOT_PATH + "/update/" + ISBN)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"isbn\": \"%s\", \"title\": \"%s\", \"author\": \"%s\" }".formatted(NEW_ISBN, NEW_TITLE, NEW_AUTHOR)))
                .andExpect(status().is(200))
                .andExpect(content().json(new Gson().toJson(newBook)));
    }

    @Test
    void shouldDeleteBookWithGivenIsbn() throws Exception{
        mockMvc.perform(delete(ROOT_PATH + "/delete/" + ISBN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

    }

}