package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.ClientDAO;
import br.com.honeymoney.api.dao.ReleaseDAO;
import br.com.honeymoney.api.dao.filter.ReleaseFilter;
import br.com.honeymoney.api.event.ResourceCreatedEvent;
import br.com.honeymoney.api.model.Client;
import br.com.honeymoney.api.model.Release;
import br.com.honeymoney.api.model.TypeRelease;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ReleaseService {

    @Autowired
    private ReleaseDAO releaseDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ApplicationEventPublisher publisher;

    // CRUD

    // CREATE
    @Transactional
    public ResponseEntity<Release> save(@Valid @RequestBody Release release, HttpServletResponse response) {
        // Buscar o cliente pelo ID fornecido no objeto Release
        Optional<Client> client = clientDAO.findById(release.getClient().getId());

        // Verificar se o cliente não existe ou está inativo
        if (client.isEmpty() || client.get().isInactive()) {
            throw new ClientNonexistentOuInactiveException();
        }

        // Salvar o objeto Release
        Release releaseSaved = releaseDAO.save(release);

        // Publicar um evento informando que um novo recurso foi criado
        publisher.publishEvent(new ResourceCreatedEvent(this, response, releaseSaved.getId()));

        // Retornar uma resposta com o objeto Release salvo e o status HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(releaseSaved);
    }

    // READ
    public ResponseEntity<Release> findById(Long id) {
        // Buscar um objeto Release pelo ID
        Release release = releaseDAO.findById(id).orElse(null);

        // Retornar o objeto Release, caso exista, com o status HTTP 200 (OK)
        // Caso contrário, retornar o status HTTP 404 (Not Found)
        return release != null ? ResponseEntity.ok(release) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findAll() {
        // Buscar todos os objetos Release
        List<Release> piches = releaseDAO.findAll();

        // Retornar a lista de Piches, caso exista, com o status HTTP 200 (OK)
        // Caso contrário, retornar o status HTTP 204 (No Content)
        return !piches.isEmpty() ? ResponseEntity.ok(piches) : ResponseEntity.noContent().build();
    }

    // UPDATE
    @Transactional
    public ResponseEntity<Release> update(Long id, Release release) {
        // Buscar o objeto Release pelo ID
        Release releaseSaved = releaseDAO.findById(id).orElse(null);

        // Verificar se o objeto Release foi encontrado
        if (releaseSaved != null) {
            // Atualizar o ID do objeto Release com o ID fornecido
            release.setId(id);

            // Salvar as alterações no objeto Release
            releaseSaved = releaseDAO.save(release);

            // Retornar o objeto Release atualizado com o status HTTP 200 (OK)
            return ResponseEntity.ok(releaseSaved);
        }

        // Caso o objeto Release não tenha sido encontrado, retornar o status HTTP 404 (Not Found)
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        // Buscar o objeto Release pelo ID
        Release release = releaseDAO.findById(id).orElse(null);

        // Verificar se o objeto Release foi encontrado
        if (release != null) {
            // Excluir o objeto Release
            releaseDAO.deleteById(id);

            // Retornar o status HTTP 200 (OK)
            return ResponseEntity.ok().build();
        }

        // Caso o objeto Release não tenha sido encontrado, retornar o status HTTP 404 (Not Found)
        return ResponseEntity.notFound().build();
    }

    // OTHER METHODS

    // TOTAL Release
    public Integer count() {
        int count = 0;
        for (Release release : releaseDAO.findAll()) {
            count++;
        }
        return count;
    }

    // SOMA DE TODOS OS VALORES DE ENTRADA
    public BigDecimal sum() {
        BigDecimal sum = BigDecimal.ZERO;
        List<Release> releases = releaseDAO.findAll();

        for (Release release : releases) {
            if (release.getType() == TypeRelease.RECEITA) {
                sum = sum.add(release.getPrice());
            }
        }
        return sum;
    }

}
