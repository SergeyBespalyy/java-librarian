package ru.bespalyy.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.bespalyy.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {

        Person person = new Person();

        person.setPerson_id(resultSet.getInt("id"));
        person.setFamily(resultSet.getString("family"));
        person.setYearBirthday(resultSet.getInt("yearBirthday"));

        return person;
    }
}
