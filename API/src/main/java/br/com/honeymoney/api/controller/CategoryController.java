package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.model.Category;
import br.com.honeymoney.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/api/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Test
    @GetMapping("/test")
    public String test() {
        return "CategoryController OK!";
    }
    // END Test

    @GetMapping
    public ResponseEntity<?> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody Category category, HttpServletResponse response) {
        Category categorySaved = categoryService.save(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categorySaved.getId()).toUri();
        response.setHeader("Location", location.toASCIIString());
        return ResponseEntity.created(location).build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}