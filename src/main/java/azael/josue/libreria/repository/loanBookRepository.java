package azael.josue.libreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import azael.josue.libreria.model.loanBook;

public interface loanBookRepository extends JpaRepository<loanBook, Long> { // Hereda de JpaRepository y la entidad es loanBook y la clave es Long{
    
}
