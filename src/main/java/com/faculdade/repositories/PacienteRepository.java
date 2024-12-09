package com.faculdade.repositories;

import com.faculdade.domain.entity.PacienteEntity;
import com.faculdade.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    PacienteEntity findByUsuario( UsuarioEntity usuario );
}
