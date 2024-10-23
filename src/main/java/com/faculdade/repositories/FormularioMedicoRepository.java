package com.faculdade.repositories;

import com.faculdade.domain.entity.FormularioMedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormularioMedicoRepository extends JpaRepository< FormularioMedicoEntity, Long> {
}
