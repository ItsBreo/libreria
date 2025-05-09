package azael.josue.libreria.service;

import azael.josue.libreria.model.publisher;
import azael.josue.libreria.repository.publisherRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class publisherService {

    @Autowired
    private publisherRepository publisherRepository;

    public publisher createPublisher(publisher newPublisher) {
        return publisherRepository.save(newPublisher);
    }

    public publisher updatePublisher(Long id, publisher updatedPublisher) {
        publisher existingPublisher = publisherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Editorial no encontrada con ID: " + id));

        existingPublisher.setName(updatedPublisher.getName());
        existingPublisher.setCountry(updatedPublisher.getCountry());
        existingPublisher.setActive(updatedPublisher.isActive());

        return publisherRepository.save(existingPublisher);
    }

    public void deletePublisher(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new IllegalArgumentException("Editorial no encontrada con ID: " + id);
        }
        publisherRepository.deleteById(id);
    }

    public List<publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Editorial no encontrada con ID: " + id));
    }
}