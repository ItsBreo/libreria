package azael.josue.libreria.controller;

import azael.josue.libreria.model.book;
import azael.josue.libreria.service.bookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class bookController {

    @Autowired
    private bookService bookService;

    @GetMapping
    public List<book> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id The unique identifier of the book to retrieve.
     * @return The book object corresponding to the given ID.
     */
    @GetMapping("/{id}")
    public book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public book createBook(@RequestBody book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public book updateBook(@PathVariable Long id, @RequestBody book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public List<book> searchBooks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) Integer publishedYear,
        @RequestParam(required = false) String sortBy,
        @RequestParam(defaultValue = "asc") String order)
    { return bookService.searchBooks(title, publishedYear, sortBy, order); }
}
