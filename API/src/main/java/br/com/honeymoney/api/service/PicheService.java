package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.PicheDAO;
import br.com.honeymoney.api.event.ResourceCreatedEvent;
import br.com.honeymoney.api.model.Piche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class PicheService {

    @Autowired
    private PicheDAO picheDAO;

    @Autowired
    private ApplicationEventPublisher publisher;

    // CRUD METHODS

    // CREATE
    @Transactional
    public ResponseEntity<Piche> save(Piche piche, HttpServletResponse response){
        Piche picheSaved = picheDAO.save(piche);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, picheSaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(picheSaved);
    }

    // READ
    public ResponseEntity<Piche> findById(Long id) {
        Piche piche = picheDAO.findById(id).orElse(null);
        return piche != null ? ResponseEntity.ok(piche) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findAll(){
        List<Piche> piches = picheDAO.findAll();
        return !piches.isEmpty() ? ResponseEntity.ok(piches) : ResponseEntity.noContent().build();
    }

    // UPDATE
    @Transactional
    public ResponseEntity<Piche> update(Long id, Piche piche) {
        Piche picheSaved = picheDAO.findById(id).orElse(null);
        if (picheSaved != null) {
            piche.setId(id);
            picheSaved = picheDAO.save(piche);
            return ResponseEntity.ok(picheSaved);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Piche piche = picheDAO.findById(id).orElse(null);
        if (piche != null) {
            picheDAO.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // OTHER METHODS

    // TOTAL PICHES
    public Integer count(){
        int count = 0;
        for (Piche piche : picheDAO.findAll()) {
            count++;
        }
        return count;
    }

}
