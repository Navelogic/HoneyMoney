package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.model.Client;
import br.com.honeymoney.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Test
    @RequestMapping("/test")
    public String test() {
        return "ClientController OK!";
    }
    // END Test

    @GetMapping
    public ResponseEntity<?> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/count")
    public Integer count() {
        return clientService.count();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Client client, HttpServletResponse response) {
        return clientService.save(client, response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return clientService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody @Valid Client client) {
        return clientService.update(id, client);
    }
}
