package azael.josue.libreria.controller;

import azael.josue.libreria.model.publisher;
import azael.josue.libreria.service.publisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publisher")
public class publisherController {

    @Autowired
    private publisherService publisherService;

    @GetMapping
    public List<publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public publisher getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping
    public publisher createPublisher(@RequestBody publisher newPublisher) {
        return publisherService.createPublisher(newPublisher);
    }

    @PutMapping("/{id}")
    public publisher updatePublisher(@PathVariable Long id, @RequestBody publisher updatedPublisher) {
        return publisherService.updatePublisher(id, updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }
}