package ru.bespalyy.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer person_id;

    @Column(name = "family")
    @NotEmpty(message = "Фамилия не может быть пустой")
    private String family;

    @Column(name = "yearbirthday")
    private Integer yearBirthday;

    public Person() {
    }

    public Person(Integer id, String family, int yearBirthday) {
        this.person_id = id;
        this.family = family;
        this.yearBirthday = yearBirthday;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getYearBirthday() {
        return yearBirthday;
    }

    public void setYearBirthday(Integer yearBirthday) {
        this.yearBirthday = yearBirthday;
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
        Person person = (Person) o;
        return Objects.equals(person_id, person.person_id) && Objects.equals(family, person.family) && Objects.equals(yearBirthday, person.yearBirthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, family, yearBirthday);
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", family='" + family + '\'' +
                ", yearBirthday=" + yearBirthday +
                '}';
    }
}
