package com.faculdade.repositories;

import com.faculdade.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository< UsuarioEntity, Long> {

    public UsuarioEntity findByCpf(String cpf);
}
