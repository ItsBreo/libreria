package azael.josue.libreria.controller;

import azael.josue.libreria.model.user;
import azael.josue.libreria.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class userController {

    @Autowired
    private userService userService;

    @GetMapping
    public List<user> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public user getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public user createUser(@RequestBody user newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/{id}")
    public user updateUser(@PathVariable Long id, @RequestBody user updatedUser) {
        return userService.updateUser(updatedUser, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}