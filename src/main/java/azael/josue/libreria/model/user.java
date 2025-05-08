package azael.josue.libreria.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")  // Nombre de tabla en la base de datos
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    /* ------------------ Un usuario puede tener muchos préstamos ----------------- */
    @OneToMany(mappedBy = "user")
    private List<loanBook> loans;

    public user(Long id, String name, String email, String password, List<loanBook> loans) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.loans = loans;
    }

    // Constructor vacío para Springboot
    public user() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<loanBook> getLoans() {
        return loans;
    }

    public void setLoans(List<loanBook> loans) {
        this.loans = loans;
    }
    
    

}
