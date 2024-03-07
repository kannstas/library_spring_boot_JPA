package ru.nastya.library_spring_boot_JPA.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.nastya.library_spring_boot_JPA.models.Author;
import ru.nastya.library_spring_boot_JPA.models.Book;
import ru.nastya.library_spring_boot_JPA.models.Reader;
import ru.nastya.library_spring_boot_JPA.services.AuthorsService;
import ru.nastya.library_spring_boot_JPA.services.BooksService;
import ru.nastya.library_spring_boot_JPA.services.ReadersService;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BooksService booksService;

    private AuthorsService authorsService;
    private ReadersService readersService;

    @Autowired
    public BooksController(BooksService booksService, AuthorsService authorsService, ReadersService readersService) {
        this.booksService = booksService;
        this.authorsService = authorsService;
        this.readersService = readersService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Book> books = booksService.findAll();
        model.addAttribute("books", books);
        return "book/index";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @ModelAttribute("reader") Reader reader,
                           @PathVariable("id") int id) {

        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("authors", booksService.findOne(id).getAuthors());
        model.addAttribute("owner", booksService.findOne(id).getReader());
        model.addAttribute("readers", readersService.findAll());
        return "book/showBook";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book,
                          @ModelAttribute("author") Author author) {
        return "book/create";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model,
                           @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        List<Author> authors = booksService.findOne(id).getAuthors();
        model.addAttribute("authors", authors);
        return "book/editBook";
    }


    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book,
                             @ModelAttribute("authors") List<Author> authors,
                             BindingResult result,
                             @PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "redirect:readers/new";
        }

        booksService.update(id, book);

        for (Author author : authors) {
            if (author.getName() != null && author.getSurname() != null) {
                authorsService.update(author.getId(), author);
            }
        }

        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        Book book = booksService.findOne(id);

        book.setBusy(false);
        book.setReader(null);
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignReader(@PathVariable("id") int id, @ModelAttribute Reader reader) {

         Book book = booksService.findOne(id);
         book.setReader(reader);
         booksService.update(id, book);
        return "redirect:/books/" + id;
    }

    @PostMapping()
    public String addBook(
            @ModelAttribute("author") Author author,
            @ModelAttribute("book") Book book,
            BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/new";
        }
        authorsService.save(author);
        booksService.save(book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBooks(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

}