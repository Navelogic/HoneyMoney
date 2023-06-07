package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
