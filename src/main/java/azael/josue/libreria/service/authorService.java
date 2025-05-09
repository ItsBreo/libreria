package azael.josue.libreria.service;

import azael.josue.libreria.model.author;
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

    public author createAuthor(author author) {
        return authorRepository.save(author);
    }

    public author updateAuthor(Long id, author author) {
        if (authorRepository.existsById(id)) {
            author.setId(id);
            return authorRepository.save(author);
        }
        return null;
    }

    public boolean deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
