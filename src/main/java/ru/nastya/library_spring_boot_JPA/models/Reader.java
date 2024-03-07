package ru.nastya.library_spring_boot_JPA.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @Column(name = "reader_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int readerId;

    @Column(name = "surname")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 100, message = "Фамилия не может быть больше 100")
    private String surname;

    @Column(name = "name")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 100, message = "Имя не может быть больше 100")
    private String name;
    @Column(name = "middle_name")
    @Size(max = 100, message = " Поле не может быть больше 100")
    private String middleName;

    @Column(name = "year_of_birth")
    @NotNull(message = "Поле не должно быть пустым")
    private int yearOfBirth;

    @OneToMany(mappedBy = "reader")
    private List<Book> books;


    public Reader(String surname, String name, String middleName, int yearOfBirth) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.yearOfBirth = yearOfBirth;
    }

    public Reader() {}

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

