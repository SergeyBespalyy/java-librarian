package ru.bespalyy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bespalyy.model.Book;
import ru.bespalyy.model.Person;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByOwner(Person owner);

    List<Book> findByNameStartingWith(String name);
}
