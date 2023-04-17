package jawnathan.data.mappers;

import jawnathan.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setPersonId(resultSet.getInt("person_id"));
        person.setFirstName(resultSet.getString("first_name"));
        person.setMiddleName(resultSet.getString("middle_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setPhoto(resultSet.getString("photo"));
        return person;
    }
}
