package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.library.models.entity.Character;

import java.util.Set;

public interface CharacterRepository extends JpaRepository<Character,Integer> {

    @Query("SELECT c FROM Character c WHERE c.age > 31 ORDER BY c.book.name DESC , c.lastName DESC,c.age")
    Set<Character> findCharactersInBookOrderedByLastNameDescendingThenByAge();

    Set<Character> findAllByAgeAfterOrderByLastNameDescAgeAsc(int age);
}
