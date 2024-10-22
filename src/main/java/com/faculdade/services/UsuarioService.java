package com.faculdade.services;

import com.faculdade.domain.entity.UsuarioEntity;
import com.faculdade.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioEntity save( UsuarioEntity usuarioEntity ) {
        usuarioEntity.setSenha(passwordEncoder.encode( usuarioEntity.getSenha()));
        return usuarioRepository.save( usuarioEntity );
    }

    public UsuarioEntity findById(Long id) {
        Optional< UsuarioEntity > usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public void delete(Long id) {
        UsuarioEntity usuarioEntity = findById(id);
        usuarioRepository.delete( usuarioEntity );
    }

    public List< UsuarioEntity > findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity authenticate(String cpf, String senha) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCpf(cpf);
        if ( usuarioEntity != null && passwordEncoder.matches(senha, usuarioEntity.getSenha())) {
            return usuarioEntity;
        }

        return null;
    }
}
