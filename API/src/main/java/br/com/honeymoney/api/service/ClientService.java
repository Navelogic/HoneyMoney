package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.ClientDAO;
import br.com.honeymoney.api.event.ResourceCreatedEvent;
import br.com.honeymoney.api.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ApplicationEventPublisher publisher;

    public ResponseEntity<?> findAll() {
        // Buscar todos os clientes do banco de dados
        List<Client> clients = clientDAO.findAll();
        // Verificar se existem clientes e retornar uma resposta apropriada
        return !clients.isEmpty() ? ResponseEntity.ok(clients) : ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> findById(Long id) {
        // Buscar um cliente pelo ID no banco de dados
        Client client = clientDAO.findById(id).orElse(null);
        // Verificar se o cliente foi encontrado e retornar uma resposta apropriada
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Client> save(Client client, HttpServletResponse response){
        // Verificar se já existe um cliente com o mesmo email, register ou nome
        Client clientFound = clientDAO.findByEmailOrRegisterOrName(client.getEmail(), client.getRegister(), client.getName());
        if (clientFound != null) {
            // Retornar uma resposta informando a duplicidade
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        // Nenhum cliente com o mesmo email, cpf_cnpj ou nome foi encontrado, prosseguir com o salvamento
        Client clientSaved = clientDAO.save(client);

        // Publicar o evento
        publisher.publishEvent(new ResourceCreatedEvent(this, response, clientSaved.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(clientSaved);
    }

    @Transactional
    public ResponseEntity<Client> update(Long id, Client client) {
        // Buscar o cliente pelo ID no banco de dados
        Client clientSaved = clientDAO.findById(id).orElse(null);
        if (clientSaved != null) {
            client.setId(id);
            // Atualizar os atributos do cliente e salvar no banco de dados
            clientSaved = clientDAO.save(client);
            return ResponseEntity.ok(clientSaved);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) {
        // Buscar o cliente pelo ID no banco de dados
        Client client = clientDAO.findById(id).orElse(null);
        if (client != null) {
            // Excluir o cliente do banco de dados
            clientDAO.delete(client);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Bussiness rules

    // TOTAL CLIENTS
    public Integer count() {
        int count = 0;
        // Contar o número total de clientes no banco de dados
        for (Client client : clientDAO.findAll()) {
            count++;
        }
        return count;
    }

    // UPDATE ACTIVE ATTRIBUTE
    public void updateAtributeActive(Long id, Boolean active) {
        // Buscar o cliente pelo ID no banco de dados
        Client client = clientDAO.findById(id).orElse(null);
        if (client != null) {
            // Atualizar o atributo "active" do cliente e salvar no banco de dados
            client.setActive(active);
            clientDAO.save(client);
        }
    }
}
