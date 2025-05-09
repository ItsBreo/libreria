package azael.josue.libreria.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nacionality;

    /* ------------------ Un autor escribe muchos libros ----------------- */
    @OneToMany(mappedBy = "author",
    cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<book> books;

    /* ------------------ Los autores dependen de las editoriales ----------------- */
    /* ------------------ Al ser una relación de muchos a muchos se crea una tabla nueva con las dos PK de la relación -------------- */
    @ManyToMany
    @JoinTable(name = "author_publisher",  // Nombre de la tabla
    joinColumns = @JoinColumn(name = "author_id"), // Unión de columnas con id_autor
    inverseJoinColumns = @JoinColumn(name = "publisher_id")) // Unión de columnas con id_publisher
    private List<publisher> publishers;
    
    public author(Long id, String name, String nacionality, List<book> books) {
        this.id = id;
        this.name = name;
        this.nacionality = nacionality;
        this.books = books;
    }

    // Constructor vacío para Springboot
    public author() {
    }

    /* ------------------ Getters y Setters ----------------- */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public List<book> getBooks() {
        return books;
    }

    public void setBooks(List<book> books) {
        this.books = books;
    }

    
}
