package ru.bespalyy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bespalyy.dao.BookDAO;
import ru.bespalyy.dao.PersonDAO;
import ru.bespalyy.model.Book;
import ru.bespalyy.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model,
                       @ModelAttribute("person") Person person,
                       @ModelAttribute("bookModel") Book book) {
        model.addAttribute("books", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        return "book/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("books") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("books") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("books", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") Integer id) {

        if (bindingResult.hasErrors())
            return "book/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/bookPut")
    public String putBook(@PathVariable("id") Integer id,
                          @ModelAttribute("person") Person person) {
        bookDAO.putBook(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/bookTake")
    public String takeBook(@PathVariable("id") Integer id) {
        bookDAO.takeBook(id);

        return "redirect:/books/" + id;
    }
}
