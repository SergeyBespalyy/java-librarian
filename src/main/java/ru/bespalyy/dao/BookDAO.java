package ru.bespalyy.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bespalyy.model.Book;
import ru.bespalyy.model.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> showByPersonId(Integer id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(Integer id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?,?,?)", book.getName(),
                book.getAuthor(), book.getYear());
    }

    public void update(Integer id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, year=? WHERE book_id=?", book.getName(),
                book.getAuthor(), book.getYear(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void putBook(Integer id, Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person.getPerson_id(), id);
    }

    public void takeBook(Integer id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", id);
    }
}
