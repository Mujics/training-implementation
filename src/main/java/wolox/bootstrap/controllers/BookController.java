package wolox.bootstrap.controllers;

import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wolox.bootstrap.models.Book;
import wolox.bootstrap.repositories.BookRepository;

@RequestMapping("/api/books")
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    // Create
    @RequestMapping("/create")
    @PostMapping("/books/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book ) {
        return bookRepository.save(book);
    }

    // Find All
    @GetMapping("/view")
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    // Find One
    @GetMapping("/view/{id}")
    public Book findById(@PathVariable int id) throws RuntimeException {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("The book does not exist"));
    }

    // Delete
    @DeleteMapping("/view/{id}")
    public void delete(@PathVariable int id) {
        bookRepository.deleteById(id);
    }

}
