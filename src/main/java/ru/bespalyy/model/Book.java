package ru.bespalyy.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {
    private Integer book_id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(max = 100, message = "Максимальная длина не должна превышать 100 символов")
    private String name;

    @NotEmpty(message = "Поле автор не должно быть пустым")
    private String author;

   // @Size(min = 0, message = "Год не может быть отрицательным")
    //@Pattern(regexp = "\\d{1,4}", message = "Год не может содержать более 5 цифр")
    private int year;

    private Integer person_id;

    public Book() {
    }

    public Book(Integer book_id, String name, String author, int year, Integer person_id) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }
}
