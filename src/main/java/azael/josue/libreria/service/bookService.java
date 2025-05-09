package azael.josue.libreria.service;

import azael.josue.libreria.model.book;
import azael.josue.libreria.repository.bookRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class bookService {

    private final bookRepository bookRepository;

    public bookService(bookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<book> getAllBooks() {
        return bookRepository.findAll();
    }

    public book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public book createBook(book book) {
        return bookRepository.save(book);
    }

    public book updateBook(Long id, book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            return bookRepository.save(book);
        }
        return null;
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<book> searchBooks(String title, Integer publishedYear, String sortBy, String order) {
    // Validamos que el campo de ordenación sea "publishedYear"
    if (sortBy != null && !sortBy.equalsIgnoreCase("publishedYear")) {
        throw new IllegalArgumentException("El campo de ordenación solo puede ser por 'publishedYear'");
    }

    // Delegar la búsqueda al repositorio
    return bookRepository.searchBooks(title, publishedYear, order);
    }
}