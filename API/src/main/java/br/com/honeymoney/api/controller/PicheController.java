package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.model.Piche;
import br.com.honeymoney.api.service.PicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/piches")
public class PicheController {

    @Autowired
    private PicheService picheService;

    // Test
    @RequestMapping("/test")
    public String test() {
        return "PicheController OK!";
    }
    // END Test

    // CRUD

    // CREATE
    @PostMapping
    public ResponseEntity<Piche> save(@RequestBody @Valid Piche piche, HttpServletResponse response) {
        return picheService.save(piche, response);
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Piche> findById(@PathVariable Long id) {
        return picheService.findById(id);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return picheService.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Piche> update(@PathVariable Long id, @RequestBody @Valid Piche piche) {
        return picheService.update(id, piche);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return picheService.delete(id);
    }

    // OTHER METHODS

    // TOTAL PICHES
    @GetMapping("/count")
    public Integer count() {
        return picheService.count();
    }
}
