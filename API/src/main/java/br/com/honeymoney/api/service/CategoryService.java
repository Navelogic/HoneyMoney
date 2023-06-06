package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.CategoryDAO;
import br.com.honeymoney.api.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public ResponseEntity<?> findAll() {
        List<Category> categorys = categoryDAO.findAll();
        return !categorys.isEmpty() ? ResponseEntity.ok(categorys) : ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> findById(Long id) {
        Category category = categoryDAO.findById(id).orElse(null);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.noContent().build();
    }

    public Category save(Category category) {
        return categoryDAO.save(category);
    }


    public ResponseEntity<?> delete(Long id) {
        Category category = categoryDAO.findById(id).orElse(null);
        if (category != null) {
            categoryDAO.delete(category);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
