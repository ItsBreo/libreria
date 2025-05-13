package azael.josue.libreria.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private boolean isActive;


    @ManyToMany
    @JoinTable(
        name = "author_publisher",
        joinColumns = @JoinColumn(name = "publisher_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<author> authors;

    @ManyToMany
    @JoinTable(
        name = "book_publisher",
        joinColumns = @JoinColumn(name = "publisher_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<book> books;

    // Constructor vacío para Springboot
    public publisher() {
    }

    public publisher(Long id, String name, String country, boolean isActive, List<author> authors, List<book> books) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.isActive = isActive;
        this.authors = authors;
        this.books = books;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<author> authors) {
        this.authors = authors;
    }

    public List<book> getBooks() {
        return books;
    }

    public void setBooks(List<book> books) {
        this.books = books;
    }
    
}
