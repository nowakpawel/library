package com.nowakpawel.library.web.controller;

import com.nowakpawel.library.web.entity.Book;
import com.nowakpawel.library.web.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequestMapping("/library")
@AllArgsConstructor
@Slf4j
public class LibraryController {
    private final BookService bookService;

    @GetMapping("/")
    @Operation(summary = "Find all books from library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find books",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<List<Book>> findAllBooks() {
        List<Book> books = bookService.findAll();
        log.info("findAllBooks: gettig all books...");

        return ResponseEntity.ok(books);
    }

    @PostMapping("/add")
    @Operation(summary = "Add book to library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New book added",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Book> createNewBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);

        return ResponseEntity.ok(newBook);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find book by its isbn")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find books",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Book> findBookById( @Parameter(description = "ISBN of book we want to find", example = "123456789")@PathVariable("id") String isbn) {
        Book foundBook = bookService.findByIsbn(isbn);

        return ResponseEntity.ok(foundBook);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update existing book in library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New book added",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Book> updateBook(@Parameter(description = "Id of book to update", example = "123456789") @PathVariable("id") String isbn, @RequestBody Book book) {

        Book updatedBook = bookService.updateBook(book, isbn);

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete existing book in library")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New book added",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Void> deleteBook(@Parameter(description = "Id of book to update", example = "123456789") @PathVariable("id") String isbn) {

        bookService.deleteBook(isbn);

        return ResponseEntity.ok().build();
    }
}
