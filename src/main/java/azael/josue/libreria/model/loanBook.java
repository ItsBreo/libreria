package azael.josue.libreria.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_book")  // Table name in the database
public class loanBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date loanDate;
    private Date returnDate;
    private boolean returned;
    
    /* ------------------ Hay muchos préstamos para un libro ----------------- */
    @ManyToOne 
    @JoinColumn(name = "book_id")
    private book book;

    /* ------------------ Hay muchos prestamos para un usuario ----------------- */
    @ManyToOne 
    @JoinColumn(name = "user_id")
    private user user;
    
    // Constructor vacío para Springboot
    public loanBook() {
    }

    public loanBook(Long id, Date loanDate, Date returnDate, boolean returned, user user) {
        this.id = id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.returned = returned;
        this.user = user;
    }

    /* ------------------ Getters y Setters ----------------- */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public book getBook() {
        return book;
    }

    public void setBook(book book) {
        this.book = book;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    
}
