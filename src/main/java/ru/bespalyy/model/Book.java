package ru.bespalyy.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer book_id;

    @Column(name = "name")
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(max = 100, message = "Максимальная длина не должна превышать 100 символов")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Поле автор не должно быть пустым")
    private String author;

    @Column(name = "year")
    private int year;

@Column(name = "person_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && Objects.equals(book_id, book.book_id) && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, name, author, year);
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", person_id=" + person_id +
                '}';
    }
}
