package azael.josue.libreria.service;

import azael.josue.libreria.model.user;
import azael.josue.libreria.model.book;
import azael.josue.libreria.model.loanBook;
import azael.josue.libreria.repository.userRepository;
import azael.josue.libreria.repository.bookRepository;
import azael.josue.libreria.repository.loanBookRepository;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loanBookService {

    @Autowired
    private loanBookRepository loanBookRepository;

    @Autowired
    private bookRepository bookRepository;

    @Autowired
    private userRepository userRepository;

    public loanBook loanBook(Long bookId, Long userId) {
        // Verificamos si el libro existe y está disponible
        book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Libro no encontrado con ID: " + bookId));

        // Verificamos si el usuario existe
        user user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + userId));

        // Creamos un nuevo préstamo
        loanBook loan = new loanBook();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(new Date(System.currentTimeMillis()));
        loan.setReturned(false);

        return loanBookRepository.save(loan);
    }

    public loanBook returnBook(Long loanId) {
        // Verificamos si el préstamo existe
        loanBook loan = loanBookRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con ID: " + loanId));

        // Marcamos el préstamo como devuelto
        loan.setReturned(true);
        loan.setReturnDate(new Date(System.currentTimeMillis()));

        return loanBookRepository.save(loan);
    }
}
