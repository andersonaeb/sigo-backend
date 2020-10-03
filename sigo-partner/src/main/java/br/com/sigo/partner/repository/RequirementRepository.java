package br.com.sigo.partner.repository;

import br.com.sigo.partner.entity.RequirementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<RequirementEntity, Integer> {
}
