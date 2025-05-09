package azael.josue.libreria.service;

import azael.josue.libreria.model.author;
import azael.josue.libreria.model.book;
import azael.josue.libreria.repository.authorRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class authorService {

    @Autowired
    private authorRepository authorRepository;

    // Constructor
    public authorService(authorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public author createAuthor(author newAuthor) {
        // Asignar el autor a cada libro
        if (newAuthor.getBooks() != null) {
            for (book b : newAuthor.getBooks()) {
                b.setAuthor(newAuthor); // Asignar el autor al libro
            }
        }
        return authorRepository.save(newAuthor);
    }

    public author updateAuthor(Long id, author updatedAuthor) {
        author existingAuthor = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Autor no encontrado con ID: " + id));

        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setNacionality(updatedAuthor.getNacionality());

        // Actualizar la lista de libros
        if (updatedAuthor.getBooks() != null) {
            existingAuthor.getBooks().clear();
            for (book b : updatedAuthor.getBooks()) {
                b.setAuthor(existingAuthor); // Asignar el autor al libro
                existingAuthor.getBooks().add(b);
            }
        }

        return authorRepository.save(existingAuthor);
    }

    public boolean deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
