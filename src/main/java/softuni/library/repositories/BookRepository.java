package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.library.models.entity.Author;
import softuni.library.models.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
