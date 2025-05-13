package azael.josue.libreria.service;

import azael.josue.libreria.model.author;
import azael.josue.libreria.model.book;
import azael.josue.libreria.model.publisher;
import azael.josue.libreria.repository.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class publisherService {

    @Autowired
    private publisherRepository publisherRepository;

    @Autowired
    private bookRepository bookRepository;

    @Autowired
    private authorRepository authorRepository;

    // Crear un Publisher
    public publisher createPublisher(publisher newPublisher) {
        return publisherRepository.save(newPublisher);
    }

    // Obtener todos los Publishers
    public List<publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    // Obtener Publisher por ID
    public publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
    }

    // Actualizar Publisher
    public publisher updatePublisher(Long id, publisher updatedPublisher) {
        publisher existingPublisher = publisherRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));

        existingPublisher.setName(updatedPublisher.getName());
        existingPublisher.setCountry(updatedPublisher.getCountry());
        existingPublisher.setActive(updatedPublisher.isActive());

        return publisherRepository.save(existingPublisher);
    }

    // Eliminar Publisher
    public void deletePublisher(Long id) {
        publisher publisher = publisherRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        
        publisherRepository.delete(publisher);
    }

    // Agregar un libro a un publisher
    public publisher addBookToPublisher(Long publisherId, Long bookId) {
        publisher publisher = publisherRepository.findById(publisherId)
            .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));

        book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        publisher.getBooks().add(book);  // Se añade el libro al publisher
        return publisherRepository.save(publisher);
    }

    // Agregar un autor a un publisher
    public publisher addAuthorToPublisher(Long publisherId, Long authorId) {
        publisher publisher = publisherRepository.findById(publisherId)
            .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));

        author author = authorRepository.findById(authorId)
            .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        publisher.getAuthors().add(author);  // Se añade el autor al publisher
        return publisherRepository.save(publisher);
    }
}
