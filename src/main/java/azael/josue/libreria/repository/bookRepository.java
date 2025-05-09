package azael.josue.libreria.repository;

import azael.josue.libreria.model.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface bookRepository extends JpaRepository<book, Long>, JpaSpecificationExecutor<book> {
}