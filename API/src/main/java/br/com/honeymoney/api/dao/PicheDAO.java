package br.com.honeymoney.api.dao;

import br.com.honeymoney.api.model.Piche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicheDAO extends JpaRepository<Piche, Long> {
}
