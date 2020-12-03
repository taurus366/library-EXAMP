package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.library.models.entity.Author;
import softuni.library.models.entity.Book;
import softuni.library.models.entity.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library,Integer> {

    Optional<Library> getByName(String name);

}
