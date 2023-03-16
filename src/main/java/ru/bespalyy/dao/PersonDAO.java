package ru.bespalyy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bespalyy.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(Integer id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(family, yearBirthday) VALUES(?,?)", person.getFamily(),
                person.getYearBirthday());
    }

    public void update(Integer id, Person person) {
        jdbcTemplate.update("UPDATE Person SET family=?, yearBirthday=? WHERE person_id=?", person.getFamily(),
                person.getYearBirthday(), id);
    }


    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }
}
