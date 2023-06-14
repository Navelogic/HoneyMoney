package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.ClientDAO;
import br.com.honeymoney.api.event.ResourceCreatedEvent;
import br.com.honeymoney.api.model.Client;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ApplicationEventPublisher publisher;

    // CRUD

    // CREATE
    @Transactional
    public ResponseEntity<Client> save(Client client, HttpServletResponse response) {
        // Verificar se já existe um cliente com o mesmo endereço eletrónico, register ou nome
        Client clientFound = clientDAO.findByEmailOrRegisterOrName(
                client.getEmail(),
                client.getRegister(),
                client.getName()
        );

        if (clientFound != null) {
            // Retornar uma resposta informando a duplicidade
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        // Nenhum cliente com o mesmo endereço eletrónico, cpf_cnpj ou nome foi encontrado,
        // prosseguir com o salvamento
        Client clientSaved = clientDAO.save(client);

        // Publicar um evento para adicionar o header Location com o URI do recurso criado
        publisher.publishEvent(new ResourceCreatedEvent(this, response, clientSaved.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(clientSaved);
    }

    // READ
    public ResponseEntity<Client> findById(Long id) {
        Optional<Client> client = clientDAO.findById(id);
        return client.isPresent() ? ResponseEntity.ok(client.get()) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findAll() {
        // Buscar todos os clientes do banco de dados
        List<Client> clients = clientDAO.findAll();
        // Verificar se existem clientes e retornar uma resposta apropriada
        return !clients.isEmpty() ? ResponseEntity.ok(clients) : ResponseEntity.noContent().build();
    }

    // UPDATE
    @Transactional
    public ResponseEntity<Client> update(Long id, Client client) {
        // Buscar o cliente pelo ID no banco de dados
        Optional<Client> clientOptional = clientDAO.findById(id);
        if (clientOptional.isPresent()) {
            Client existingClient = clientOptional.get();

            try {
                // Copiar apenas as propriedades não nulas do objeto client para o existingClient
                BeanUtils.copyProperties(existingClient, client);
            } catch (IllegalAccessException | InvocationTargetException e) {
                // Lidar com qualquer exceção que ocorrer durante a cópia das propriedades
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            // Salvar o cliente atualizado no banco de dados
            Client updatedClient = clientDAO.save(existingClient);

            return ResponseEntity.ok(updatedClient);
        }

        return ResponseEntity.notFound().build();
    }


    // DELETE
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Optional<Client> clientOptional = clientDAO.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            // Remover o cliente do banco de dados
            clientDAO.delete(client);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    // Business rules

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
    public ResponseEntity<?> updateAtributeActive(Long id, Boolean active) {
        // Buscar o cliente pelo ID no banco de dados
        Client client = clientDAO.findById(id).orElse(null);
        if (client != null) {
            // Atualizar o atributo "active" do cliente e salvar no banco de dados
            client.setActive(active);
            clientDAO.save(client);
        }
        return null;
    }
}
