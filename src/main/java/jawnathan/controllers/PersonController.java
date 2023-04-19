package jawnathan.controllers;

import jawnathan.domain.GroupService;
import jawnathan.domain.PersonService;
import jawnathan.domain.Result;
import jawnathan.models.Group;
import jawnathan.models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findById(@PathVariable int personId) {
        Person person = personService.findById(personId);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Person person) {
        Result<Person> result = personService.add(person);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Object> update(@PathVariable int personId, @RequestBody Person person) {
        if (personId != person.getPersonId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Person> result = personService.update(person);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Void> deleteById(@PathVariable int personId) {
        if (personService.deleteById(personId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
