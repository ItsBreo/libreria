package azael.josue.libreria.service;

import azael.josue.libreria.model.user;
import azael.josue.libreria.repository.userRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
  
    @Autowired
    private userRepository userRepository;

    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    public user getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public user createUser(user newUser) {
        return userRepository.save(newUser);
    }

    public user updateUser(user updatedUser, Long id) {
        // Verificamos si el usuario existe en la base de datos
        Optional<user> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }

        // Obtenemos el usuario existente
        user existingUser = optionalUser.get();

        // Aplicamos las actualizaciones en el usuario existente
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setLoans(updatedUser.getLoans());

        // Guardamos los cambios en la base de datos
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }
}