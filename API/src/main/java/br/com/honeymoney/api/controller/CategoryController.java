package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List findAll() {
        return categoryService.findAll();
    }
}
