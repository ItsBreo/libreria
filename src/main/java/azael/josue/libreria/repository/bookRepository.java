package azael.josue.libreria.repository;

import azael.josue.libreria.model.book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface bookRepository extends JpaRepository<book, Long> {

    @Query("SELECT b FROM book b " +
           "WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
           "AND (:publishedYear IS NULL OR b.publishedYear = :publishedYear) " +
           "ORDER BY " +
           "CASE WHEN :order = 'desc' THEN b.publishedYear END DESC, " +
           "CASE WHEN :order = 'asc' THEN b.publishedYear END ASC")
    List<book> searchBooks(@Param("title") String title,
                           @Param("publishedYear") Integer publishedYear,
                           @Param("order") String order);
}
