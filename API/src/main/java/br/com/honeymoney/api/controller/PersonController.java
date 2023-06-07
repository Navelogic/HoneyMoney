package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.model.Person;
import br.com.honeymoney.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    // Test
    @RequestMapping("/test")
    public String test() {
        return "PersonController OK!";
    }
    // END Test

    // CRUD methods
    @GetMapping
    public ResponseEntity<?> findAll() {
        return personService.findAll();
    }

    @GetMapping("/count")
    public Integer count() {
        return personService.count();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(Long id) {
        return personService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Person person, HttpServletResponse response) {
        return personService.save(person, response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return personService.delete(id);
    }
}
