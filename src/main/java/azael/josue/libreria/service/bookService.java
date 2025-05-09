package azael.josue.libreria.service;

import azael.josue.libreria.model.book;
import azael.josue.libreria.repository.bookRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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

    

    public List<book> searchBooks(String title, Integer year, String sortBy, String order) {
        // Construirmos la especificación dinámica
        Specification<book> spec = Specification.where(null);

        if (title != null && !title.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }

        if (year != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("publishedYear"), year));
        }

        // Configuramos la ordenación
        if (sortBy == null || (!sortBy.equals("publishedYear") && !sortBy.equals("title"))) {
            sortBy = "id"; // Valor predeterminado
        }

        Sort sort;
        if (order != null && order.equalsIgnoreCase("desc")) {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        } else {
            sort = Sort.by(Sort.Direction.ASC, sortBy);
        }

        // Ejecutamos la consulta
        return bookRepository.findAll(spec, sort);
    }
}