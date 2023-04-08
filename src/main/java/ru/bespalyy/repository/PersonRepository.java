package ru.bespalyy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bespalyy.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
