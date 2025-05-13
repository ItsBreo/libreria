package azael.josue.libreria.controller;

import azael.josue.libreria.model.book;
import azael.josue.libreria.model.loanBook;
import azael.josue.libreria.model.user;
import azael.josue.libreria.repository.bookRepository;
import azael.josue.libreria.repository.loanBookRepository;
import azael.josue.libreria.repository.userRepository;
import azael.josue.libreria.service.loanBookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
public class loanBookController {

    @Autowired
    private bookRepository bookRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private loanBookRepository loanBookRepository;

    @Autowired
    private loanBookService loanBookService;

    @PostMapping
        public ResponseEntity<loanBook> createLoan(@RequestBody loanBook loan) {
        // Validar que el libro y usuario existen realmente (opcional pero recomendable)
        book book = bookRepository.findById(loan.getBook().getId())
        .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        user user = userRepository.findById(loan.getUser().getId())
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        loan.setBook(book);
        loan.setUser(user);

        loanBook saved = loanBookRepository.save(loan);
        return ResponseEntity.ok(saved);
}


    @PutMapping("/{loanId}/return")
    public loanBook returnBook(@PathVariable Long loanId) {
        return loanBookService.returnBook(loanId);
    }

     // GET (listar todos los préstamos)
     @GetMapping
     public List<loanBook> getAllLoans() {
         return loanBookRepository.findAll();
     }

}