package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.ClientDAO;
import br.com.honeymoney.api.dao.PicheDAO;
import br.com.honeymoney.api.event.ResourceCreatedEvent;
import br.com.honeymoney.api.model.Client;
import br.com.honeymoney.api.model.Piche;
import br.com.honeymoney.api.service.exception.ClientNonexistentOuInactiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PicheService {

    @Autowired
    private PicheDAO picheDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ApplicationEventPublisher publisher;

    // CRUD METHODS

    // CREATE
    @Transactional
    public ResponseEntity<Piche> save(@Valid @RequestBody Piche piche, HttpServletResponse response) {
        // Buscar o cliente pelo ID fornecido no objeto Piche
        Optional<Client> client = clientDAO.findById(piche.getClient().getId());

        // Verificar se o cliente não existe ou está inativo
        if (client.isEmpty() || client.get().isInactive()) {
            throw new ClientNonexistentOuInactiveException();
        }

        // Salvar o objeto Piche
        Piche picheSaved = picheDAO.save(piche);

        // Publicar um evento informando que um novo recurso foi criado
        publisher.publishEvent(new ResourceCreatedEvent(this, response, picheSaved.getId()));

        // Retornar uma resposta com o objeto Piche salvo e o status HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(picheSaved);
    }

    // READ
    public ResponseEntity<Piche> findById(Long id) {
        // Buscar um objeto Piche pelo ID
        Piche piche = picheDAO.findById(id).orElse(null);

        // Retornar o objeto Piche, caso exista, com o status HTTP 200 (OK)
        // Caso contrário, retornar o status HTTP 404 (Not Found)
        return piche != null ? ResponseEntity.ok(piche) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findAll() {
        // Buscar todos os objetos Piche
        List<Piche> piches = picheDAO.findAll();

        // Retornar a lista de Piches, caso exista, com o status HTTP 200 (OK)
        // Caso contrário, retornar o status HTTP 204 (No Content)
        return !piches.isEmpty() ? ResponseEntity.ok(piches) : ResponseEntity.noContent().build();
    }

    // UPDATE
    @Transactional
    public ResponseEntity<Piche> update(Long id, Piche piche) {
        // Buscar o objeto Piche pelo ID
        Piche picheSaved = picheDAO.findById(id).orElse(null);

        // Verificar se o objeto Piche foi encontrado
        if (picheSaved != null) {
            // Atualizar o ID do objeto Piche com o ID fornecido
            piche.setId(id);

            // Salvar as alterações no objeto Piche
            picheSaved = picheDAO.save(piche);

            // Retornar o objeto Piche atualizado com o status HTTP 200 (OK)
            return ResponseEntity.ok(picheSaved);
        }

        // Caso o objeto Piche não tenha sido encontrado, retornar o status HTTP 404 (Not Found)
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        // Buscar o objeto Piche pelo ID
        Piche piche = picheDAO.findById(id).orElse(null);

        // Verificar se o objeto Piche foi encontrado
        if (piche != null) {
            // Excluir o objeto Piche
            picheDAO.deleteById(id);

            // Retornar o status HTTP 200 (OK)
            return ResponseEntity.ok().build();
        }

        // Caso o objeto Piche não tenha sido encontrado, retornar o status HTTP 404 (Not Found)
        return ResponseEntity.notFound().build();
    }

    // OTHER METHODS

    // TOTAL PICHES
    public Integer count() {
        int count = 0;
        for (Piche piche : picheDAO.findAll()) {
            count++;
        }
        return count;
    }
}
