package ru.nastya.library_spring_boot_JPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nastya.library_spring_boot_JPA.models.Author;
import ru.nastya.library_spring_boot_JPA.models.Book;
import ru.nastya.library_spring_boot_JPA.repositories.AuthorsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorsService {
    private AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    public Author findOne(int id) {
        return authorsRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Author author) {
        authorsRepository.save(author);
    }

    @Transactional
    public void update(int id, Author updateAuthor) {
        updateAuthor.setId(id);
        authorsRepository.save(updateAuthor);
    }

    @Transactional
    public void delete(int id) {
        authorsRepository.deleteById(id);
    }
}