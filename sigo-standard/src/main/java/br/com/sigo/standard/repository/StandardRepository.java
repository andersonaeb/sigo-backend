package br.com.sigo.standard.repository;

import br.com.sigo.standard.entity.StandardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends JpaRepository<StandardEntity, Integer> {
}
