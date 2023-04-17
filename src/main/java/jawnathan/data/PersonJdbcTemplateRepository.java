package jawnathan.data;

import jawnathan.data.mappers.PersonMapper;
import jawnathan.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PersonJdbcTemplateRepository implements PersonRepository {
    private final JdbcTemplate jdbcTemplate;

    public PersonJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        final String sql = "select person_id, first_name, middle_name, last_name, photo from person;";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    @Override
    public Person findById(int personId){
        final String sql = "select person_id, first_name, middle_name, last_name, photo "
                + "from person "
                + "where person_id = ?;";
        return jdbcTemplate.query(sql, new PersonMapper(), personId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person add(Person person) {
        final String sql = "insert into person (first_name, middle_name, last_name, photo) values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getMiddleName());
            ps.setString(3, person.getLastName());
            ps.setString(4, person.getPhoto());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        person.setPersonId(keyHolder.getKey().intValue());
        return person;
    }

    @Override
    public boolean update(Person person){
        final String sql = "update person set "
                + "first_name = ?, "
                + "middle_name = ?, "
                + "last_name = ?, "
                + "photo = ? "
                + "where person_id = ?";

        return jdbcTemplate.update(sql, person.getFirstName(), person.getMiddleName(), person.getLastName(), person.getPhoto(), person.getPersonId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int personId){
        jdbcTemplate.update("delete from group_person where person_id = ?", personId);
        jdbcTemplate.update("delete from person_gig_role where person_id = ?", personId);
        return jdbcTemplate.update("delete from person where person_id = ?", personId) > 0;
    }
}
