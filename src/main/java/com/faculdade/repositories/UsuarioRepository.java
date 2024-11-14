package com.faculdade.repositories;

import com.faculdade.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByCpf( String cpf );

    Optional<UsuarioEntity> findFirstByNomeContaining( String nome );
}
