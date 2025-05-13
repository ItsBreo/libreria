package azael.josue.libreria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.concurrent.Flow.Publisher;

@Entity
@Table(name = "books")
public class book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private int publishedYear;

    /* ------------------ Un libro es escrito por un solo autor ----------------- */
    @ManyToOne // Unión de muchos a uno
    @JoinColumn(name = "author_id") // Unión de columnas con la FK de autor
    private author author;

    // Getter para authorId
    public Long getAuthorId() {
        if (author == null) {
            return null;
        } else {
            return author.getId();
        }
    }

    
    /* ------------------ Al ser una relación de muchos a muchos se crea una tabla nueva con las dos PK de la relación -------------- */
    // Relación ManyToMany con Publisher, bidireccional
    @ManyToMany(mappedBy = "books")
    private List<publisher> publishers;

    /* ------------------ Un libro puede tener muchos préstamos --------------- */
    @OneToMany(mappedBy = "book")
    private List<loanBook> loans;
    

    public book(Long id, String title, String isbn, int publishedYear, author author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.author = author;
    }

    // Constructor vacío para SpringBoot
    public book() {
    }

    /* ------------------ Getters y Setters ----------------- */

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public int getPublishedYear() {
        return publishedYear;
    }


    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }


    public author getAuthor() {
        return author;
    }


    public void setAuthor(author author) {
        this.author = author;
    }
}
