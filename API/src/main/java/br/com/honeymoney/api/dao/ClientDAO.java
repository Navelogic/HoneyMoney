package br.com.honeymoney.api.dao;

import br.com.honeymoney.api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDAO extends JpaRepository<Client, Long> {
    Client findByEmailOrRegisterOrName(String email, String register, String name);
}
