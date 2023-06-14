package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.dao.filter.ReleaseFilter;
import br.com.honeymoney.api.model.Release;
import br.com.honeymoney.api.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/releases")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    // Test
    @GetMapping("/test")
    public String test() {
        return "ReleaseController OK!";
    }
    // END Test

    // CRUD

    // CREATE
    @PostMapping
    public ResponseEntity<Release> save(@RequestBody @Valid Release release, HttpServletResponse response) {
        // Chamar o método save do ReleaseService para criar um Release
        return releaseService.save(release, response);
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Release> findById(@PathVariable Long id) {
        // Chamar o método findById do ReleaseService para buscar um Release pelo ID
        return releaseService.findById(id);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        // Chamar o método findAll do ReleaseService para buscar todos os Piches
        return releaseService.findAll();
    }

    @GetMapping("/sum")
    public BigDecimal sum(){
        return releaseService.sum();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Release> update(@PathVariable Long id, @RequestBody @Valid Release release) {
        // Chamar o método update do ReleaseService para atualizar um Release pelo ID
        return releaseService.update(id, release);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // Chamar o método delete do ReleaseService para excluir um Release pelo ID
        return releaseService.delete(id);
    }

    // OTHER METHODS

    // TOTAL PICHES
    @GetMapping("/count")
    public Integer count() {
        // Chamar o método count do ReleaseService para contar o número total de Piches
        return releaseService.count();
    }
}
