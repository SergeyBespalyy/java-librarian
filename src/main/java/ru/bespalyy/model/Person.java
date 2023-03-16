package ru.bespalyy.model;

import javax.validation.constraints.NotEmpty;

public class Person {
    private Integer person_id;

    @NotEmpty(message = "Фамилия не может быть пустой")
    private String family;

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

}
