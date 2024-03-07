package ru.nastya.library_spring_boot_JPA.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "surname")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 100, message = "Фамилия не может быть больше 100")
    private String surname;

    @Column(name = "name")
    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(max = 100, message = "Имя не может быть больше 100")
    private String name;

    @Column(name = "middle_name")
    @Size(max = 100, message = "Имя не может быть больше 100")
    private String middleName;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST)
    private List <Book> books;

    public Author(String surname, String name, String middleName) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
    }

    public Author() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}