package azael.josue.libreria.controller;

import azael.josue.libreria.model.loanBook;
import azael.josue.libreria.service.loanBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
public class loanBookController {

    @Autowired
    private loanBookService loanBookService;

    @PostMapping
    public loanBook loanBook(@RequestParam Long bookId, @RequestParam Long userId) {
        return loanBookService.loanBook(bookId, userId);
    }

    @PutMapping("/{loanId}/return")
    public loanBook returnBook(@PathVariable Long loanId) {
        return loanBookService.returnBook(loanId);
    }
}