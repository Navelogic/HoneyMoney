package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.CategoryDAO;
import br.com.honeymoney.api.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
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

    public ResponseEntity<Category> save(Category category, HttpServletResponse response) {
        Category categorySaved = categoryDAO.save(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categorySaved.getId()).toUri();
        response.setHeader("Location", location.toASCIIString());
        return ResponseEntity.created(location).body(categorySaved);
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
