package br.com.honeymoney.api.dao;

import br.com.honeymoney.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDAO extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
}
