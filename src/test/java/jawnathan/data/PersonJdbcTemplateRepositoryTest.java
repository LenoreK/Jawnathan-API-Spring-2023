package jawnathan.data;

import jawnathan.models.Group;
import jawnathan.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PersonJdbcTemplateRepositoryTest {

    @Autowired
    PersonJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void findAll() {
        List<Person> people = repository.findAll();
        assertNotNull(people);
    }

    @Test
    void findById() {
        Person actual = repository.findById(2);
        assertNotNull(actual);
        assertEquals(2, actual.getPersonId());
    }

    @Test
    void add() {
        Person person = new Person();
        person.setFirstName("Lenore");
        person.setMiddleName("Christine");
        person.setLastName("Kelly");
        person.setPhoto("something.com");
        Person actual = repository.add(person);
        assertNotNull(actual);
        assertEquals(4, actual.getPersonId());
    }

    @Test
    void update() {
        Person person = new Person();
        person.setPersonId(2);
        person.setFirstName("Lenore");
        person.setMiddleName("Christine");
        person.setLastName("Kelly");
        person.setPhoto("something.com");
        assertTrue(repository.update(person));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(3000));
    }
}