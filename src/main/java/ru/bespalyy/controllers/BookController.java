package ru.bespalyy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bespalyy.model.Book;
import ru.bespalyy.model.Person;
import ru.bespalyy.services.BookService;
import ru.bespalyy.services.PersonService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(required = false) Integer page,
                        @RequestParam(name = "books_per_page", required = false) Integer itemsPerPage,
                        @RequestParam(name = "sort_by_year", defaultValue = "false") Boolean isSort) {

        model.addAttribute("books", bookService.index(page, itemsPerPage, isSort));
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model,
                       @ModelAttribute("person") Person person,
                       @ModelAttribute("bookModel") Book book) {
        model.addAttribute("books", bookService.show(id));
        model.addAttribute("people", personService.index());
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

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("books", bookService.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") Integer id) {

        if (bindingResult.hasErrors())
            return "book/edit";

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/bookPut")
    public String putBook(@PathVariable("id") Integer id,
                          @ModelAttribute("person") Person person) {
        bookService.putBook(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/bookTake")
    public String takeBook(@PathVariable("id") Integer id) {
        bookService.takeBook(id);

        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String searchBook(Model model,
                             @RequestParam(required = false, name = "sh") String name) {
        if (name != null) {
            model.addAttribute("books", bookService.searchBook(name));
        }

        return "book/search";
    }
}
