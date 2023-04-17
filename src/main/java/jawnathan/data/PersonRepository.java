package jawnathan.data;

import jawnathan.models.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonRepository {

    List<Person> findAll();

    Person findById(int personId);

    Person add(Person person);

    boolean update(Person person);

    @Transactional
    boolean deleteById(int personId);
}
