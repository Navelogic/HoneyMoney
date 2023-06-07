package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.CategoryDAO;
import br.com.honeymoney.api.event.ResourceCreatedEvent;
import br.com.honeymoney.api.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ApplicationEventPublisher publisher;

    public ResponseEntity<?> findAll() {
        List<Category> categorys = categoryDAO.findAll();
        return !categorys.isEmpty() ? ResponseEntity.ok(categorys) : ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> findById(Long id) {
        Category category = categoryDAO.findById(id).orElse(null);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Category> save(Category category, HttpServletResponse response) {
        // Verificar se já existe uma categoria com o mesmo nome
        Category existingCategory = categoryDAO.findByName(category.getName());
        if (existingCategory != null) {
            // Retornar uma resposta informando a duplicidade
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        // Nenhuma categoria com o mesmo nome foi encontrada, prosseguir com o salvamento
        Category categorySaved = categoryDAO.save(category);

        // Publicar o evento
        publisher.publishEvent(new ResourceCreatedEvent(this, response, categorySaved.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
    }

    public ResponseEntity<?> delete(Long id) {
        Category category = categoryDAO.findById(id).orElse(null);
        if (category != null) {
            categoryDAO.delete(category);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Integer count() {
        int count = 0;
        for (Category category : categoryDAO.findAll()) {
            count++;
        }
        return count;
    }
}
