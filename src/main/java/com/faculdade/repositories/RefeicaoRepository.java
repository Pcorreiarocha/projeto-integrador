package com.faculdade.repositories;

import com.faculdade.domain.entity.RefeicaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefeicaoRepository extends JpaRepository<RefeicaoEntity, Long> {
}
