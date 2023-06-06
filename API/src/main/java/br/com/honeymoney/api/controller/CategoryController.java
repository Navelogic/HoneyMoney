package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.model.Category;
import br.com.honeymoney.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity<Category> save(@RequestBody Category category, HttpServletResponse response) {
        return categoryService.save(category, response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}