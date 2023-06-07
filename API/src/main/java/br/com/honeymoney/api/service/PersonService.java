package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.PersonDAO;
import br.com.honeymoney.api.event.ResourceCreatedEvent;
import br.com.honeymoney.api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private ApplicationEventPublisher publisher;

    // CRUD methods

    public ResponseEntity<?> findAll() {
        List<Person> persons = personDAO.findAll();
        return !persons.isEmpty() ? ResponseEntity.ok(persons) : ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> findById(Long id) {
        Person person = personDAO.findById(id).orElse(null);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Person> save(Person person, HttpServletResponse response){
        // Verificar se já existe uma pessoa com o mesmo email
        Person existingPerson = personDAO.findByEmail(person.getEmail());
        if (existingPerson != null) {
            // Retornar uma resposta informando a duplicidade
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        // Nenhuma pessoa com o mesmo email foi encontrada, prosseguir com o salvamento
        Person personSaved = personDAO.save(person);

        // Publicar o evento
        publisher.publishEvent(new ResourceCreatedEvent(this, response, personSaved.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(personSaved);
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Person person = personDAO.findById(id).orElse(null);
        if (person != null) {
            personDAO.delete(person);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Bussiness methods
    public Integer count() {
        int count = 0;
        for (Person person : personDAO.findAll()) {
            count++;
        }
        return count;
    }

}
