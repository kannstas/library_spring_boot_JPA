package ru.nastya.library_spring_boot_JPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nastya.library_spring_boot_JPA.models.Author;
import ru.nastya.library_spring_boot_JPA.models.Book;
import ru.nastya.library_spring_boot_JPA.repositories.BooksRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
       return booksRepository.findAll();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save (Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update (int id, Book updatedBook) {
        updatedBook.setBookId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete (int id) {
        booksRepository.deleteById(id);
    }
}