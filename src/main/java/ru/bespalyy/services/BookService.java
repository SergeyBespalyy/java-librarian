package ru.bespalyy.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.bespalyy.exception.ValidationIdException;
import ru.bespalyy.model.Book;
import ru.bespalyy.model.Person;
import ru.bespalyy.repository.BookRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PersonService personService;

    public BookService(BookRepository bookRepository, PersonService personService) {
        this.bookRepository = bookRepository;
        this.personService = personService;
    }

    public List<Book> index(Integer page, Integer itemsPerPage, Boolean isSort) {
        if (page == null || itemsPerPage == null) {
            if (isSort) {
                return bookRepository.findAll(Sort.by("year"));
            } else {
                return bookRepository.findAll();
            }
        }

        if (isSort) {
            return bookRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("year"))).getContent();
        } else {
            return bookRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
        }

    }

    public List<Book> showByPersonId(Integer id) {
        List<Book> bookList = bookRepository.findByOwner(personService.show(id));
        return bookList.stream().peek(Book::setIsOverdue).collect(Collectors.toList());
    }

    public Book show(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new ValidationIdException("Id not found"));
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void update(Integer id, Book book) {
        book.setBook_id(id);
        bookRepository.save(book);
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    public void putBook(Integer id, Person person) {
        Book updateBook = show(id);
        updateBook.setTimeAtUse(new Date());
        updateBook.setOwner(person);
        bookRepository.save(updateBook);
    }

    public void takeBook(Integer id) {
        Book updateBook = show(id);
        updateBook.setTimeAtUse(null);
        updateBook.setOwner(null);
        bookRepository.save(updateBook);
    }

    public List<Book> searchBook(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>();
        } else {
            return bookRepository.findByNameStartingWith(name);
        }
    }
}
