package jawnathan.domain;

import jawnathan.data.PersonRepository;
import jawnathan.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(int personId) {
        return repository.findById(personId);
    }

    public Result<Person> add(Person person) {
        Result<Person> result = validate(person);
        if (!result.isSuccess()) {
            return result;
        }

        if (person.getPersonId() != 0) {
            result.addMessage("personId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        person = repository.add(person);
        result.setPayload(person);
        return result;
    }

    public Result<Person> update(Person person) {
        Result<Person> result = validate(person);
        if (!result.isSuccess()) {
            return result;
        }

        if (person.getPersonId() <= 0) {
            result.addMessage("personId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(person)) {
            String msg = String.format("personId: %s, not found", person.getPersonId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int personId) {
        return repository.deleteById(personId);
    }

    private Result<Person> validate(Person person){
        Result<Person> result = new Result<>();
        if (person == null){
            result.addMessage("person cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(person.getFirstName())){
            result.addMessage("first name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(person.getLastName())){
            result.addMessage("last name is required", ResultType.INVALID);
        }
        return result;
    }
}
