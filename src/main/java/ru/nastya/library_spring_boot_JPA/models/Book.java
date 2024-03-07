package ru.nastya.library_spring_boot_JPA.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @Column(name = "book_title")
    @NotEmpty(message = "Поле не должно быть пустым")
    private String bookTitle;
    @Column(name = "year_of_creation")
    private int yearOfCreation;

    @Column(name = "is_busy")
    private boolean isBusy;

    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "reader_id")
    private Reader reader;

    @ManyToMany()
    @JoinTable(
            name = "books_authors",
            joinColumns =  @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id")
    )
    private List<Author> authors;

    public Book(String bookTitle, int yearOfCreation) {
        this.bookTitle = bookTitle;
        this.yearOfCreation = yearOfCreation;
    }

    public Book() {}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}