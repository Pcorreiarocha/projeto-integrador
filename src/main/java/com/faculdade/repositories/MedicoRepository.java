package com.faculdade.repositories;

import com.faculdade.domain.entity.MedicoEntity;
import com.faculdade.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    MedicoEntity findByUsuario( UsuarioEntity usuario );
}
