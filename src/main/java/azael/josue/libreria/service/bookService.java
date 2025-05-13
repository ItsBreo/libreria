package azael.josue.libreria.service;

import azael.josue.libreria.model.author;
import azael.josue.libreria.model.book;
import azael.josue.libreria.repository.authorRepository;
import azael.josue.libreria.repository.bookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class bookService {

    @Autowired
    private final bookRepository bookRepository;
    private final authorRepository authorRepository;

    public bookService(bookRepository bookRepository, authorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<book> getAllBooks() {
        return bookRepository.findAll();
    }

    public book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public book createBook(book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            // Busca el autor por ID y lo asigna al libro
            author author = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado con ID: " + book.getAuthor().getId()));
            book.setAuthor(author);
        } else {
            throw new IllegalArgumentException("El autor es obligatorio para crear un libro.");
        }
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

    public List<book> searchBooks(String title, Integer year, String sortBy, String order) {
        List<book> books = bookRepository.findAll();

        // Filtrar por título si se proporciona
        if (title != null && !title.isEmpty()) {
            books = books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        }

        // Filtrar por año si se proporciona
        if (year != null) {
            books = books.stream()
            .filter(book -> Integer.valueOf(book.getPublishedYear()).equals(year))
            .collect(Collectors.toList());
        }

        // Ordenar si se proporciona un campo de ordenación
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<book> comparator = switch (sortBy) {
                case "titulo" -> Comparator.comparing(book::getTitle);
                case "anio" -> Comparator.comparing(book::getPublishedYear);
                // Añade más casos según los campos por los que quieras permitir ordenar
                default -> (b1, b2) -> 0; // No ordenar si el campo no es reconocido
            };

            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }

            books.sort(comparator);
        }

        return books;
    }
}