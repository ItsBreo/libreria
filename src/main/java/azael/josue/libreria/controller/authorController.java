package azael.josue.libreria.controller;

import azael.josue.libreria.model.author;
import azael.josue.libreria.service.authorService;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/author")
public class authorController {

    @Autowired
    private authorService authorService;

    @GetMapping
    public List<author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public author createAuthor(@RequestBody author author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/{id}")
    public author updateAuthor(@PathVariable Long id, @RequestBody author author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);
    }

}
