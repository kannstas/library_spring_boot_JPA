package ru.nastya.library_spring_boot_JPA.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.nastya.library_spring_boot_JPA.models.Reader;
import ru.nastya.library_spring_boot_JPA.services.ReadersService;

import java.util.List;


@Controller
@RequestMapping("/readers")
public class ReadersController {

    ReadersService readersService;

    @Autowired
    public ReadersController(ReadersService readersService) {
        this.readersService = readersService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<Reader> all = readersService.findAll();
        model.addAttribute("readers", all);
        return "reader/index";
    }

    @GetMapping("/{id}")
    public String showReader(@PathVariable("id") int id, Model model) {
        model.addAttribute("reader", readersService.findOne(id));
        return "reader/showReader";
    }

    @GetMapping("/new")
    public String newReader(@ModelAttribute("reader") Reader reader) {
        return "reader/create";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id) {
        model.addAttribute("reader", readersService.findOne(id));
        return "reader/edit";
    }

    @PostMapping()
    public String addReader(@ModelAttribute("reader") @Valid  Reader reader,
                            BindingResult result) {
        if (result.hasErrors()) {
            return  "redirect:/readers/new";
        }
        readersService.save(reader);
        return "redirect:/readers";
    }

    @PatchMapping("/{id}")
    public String updateReader(@ModelAttribute("reader") Reader reader,
                               BindingResult result,
                               @PathVariable("id") int id) {
        if (result.hasErrors()) {
            return "redirect:readers/new";
        }
       readersService.update(id, reader);
        return "redirect:/readers";
    }

    @DeleteMapping("/{id}")
    public String deleteReader(@PathVariable("id") int id) {
        readersService.delete(id);
        return "redirect:/readers";
    }
}