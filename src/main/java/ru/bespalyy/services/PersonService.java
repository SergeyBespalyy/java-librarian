package ru.bespalyy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bespalyy.exception.ValidationIdException;
import ru.bespalyy.model.Person;
import ru.bespalyy.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> index() {
        return personRepository.findAll();
    }

    public Person show(Integer id) {
        return personRepository.findById(id).orElseThrow(() -> new ValidationIdException("Некорректный Id"));
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void update(Integer id, Person person) {
        person.setPerson_id(id);
        personRepository.save(person);
    }

    public void delete(Integer id) {
        personRepository.deleteById(id);
    }
}
