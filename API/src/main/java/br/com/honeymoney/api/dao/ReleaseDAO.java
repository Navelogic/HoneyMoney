package br.com.honeymoney.api.dao;

import br.com.honeymoney.api.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseDAO extends JpaRepository<Release, Long> {
}
