package ru.nastya.library_spring_boot_JPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nastya.library_spring_boot_JPA.models.Author;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
}
