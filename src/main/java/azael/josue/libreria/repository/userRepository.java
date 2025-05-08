package azael.josue.libreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import azael.josue.libreria.model.user;

public interface userRepository extends JpaRepository<user, Long>{ // Hereda de JpaRepository y la entidad es user y la clave es Long

} 
