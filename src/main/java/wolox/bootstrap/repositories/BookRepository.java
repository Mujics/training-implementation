package wolox.bootstrap.repositories;

import org.springframework.data.repository.CrudRepository;
import wolox.bootstrap.models.Book;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findByAuthor(String author);
    Optional<Book> findByIsbn(String isbn);
}
