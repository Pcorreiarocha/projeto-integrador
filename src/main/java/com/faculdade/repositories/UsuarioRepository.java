package com.faculdade.repositories;

import com.faculdade.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByCpf(String cpf);
}
