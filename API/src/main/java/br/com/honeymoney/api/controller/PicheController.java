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
    @GetMapping("/test")
    public String test() {
        return "PicheController OK!";
    }
    // END Test

    // CRUD

    // CREATE
    @PostMapping
    public ResponseEntity<Piche> save(@RequestBody @Valid Piche piche, HttpServletResponse response) {
        // Chamar o método save do PicheService para criar um novo Piche
        return picheService.save(piche, response);
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Piche> findById(@PathVariable Long id) {
        // Chamar o método findById do PicheService para buscar um Piche pelo ID
        return picheService.findById(id);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        // Chamar o método findAll do PicheService para buscar todos os Piches
        return picheService.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Piche> update(@PathVariable Long id, @RequestBody @Valid Piche piche) {
        // Chamar o método update do PicheService para atualizar um Piche pelo ID
        return picheService.update(id, piche);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // Chamar o método delete do PicheService para excluir um Piche pelo ID
        return picheService.delete(id);
    }

    // OTHER METHODS

    // TOTAL PICHES
    @GetMapping("/count")
    public Integer count() {
        // Chamar o método count do PicheService para contar o número total de Piches
        return picheService.count();
    }
}
