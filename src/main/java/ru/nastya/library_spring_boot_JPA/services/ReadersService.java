package ru.nastya.library_spring_boot_JPA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nastya.library_spring_boot_JPA.models.Reader;
import ru.nastya.library_spring_boot_JPA.repositories.ReadersRepository;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReadersService {
    ReadersRepository readersRepository;

    @Autowired
    public ReadersService(ReadersRepository readersRepository) {
        this.readersRepository = readersRepository;
    }

    public List<Reader> findAll () {
        return readersRepository.findAll();
    }

    public Reader findOne (int id) {
        return readersRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save (Reader reader) {
        readersRepository.save(reader);
    }

    @Transactional
    public void update (int id, Reader updateReader) {
        updateReader.setReaderId(id);
        readersRepository.save(updateReader);
    }

    @Transactional
    public void delete (int id) {
        readersRepository.deleteById(id);
    }
}