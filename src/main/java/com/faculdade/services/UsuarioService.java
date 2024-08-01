package com.faculdade.services;

import com.faculdade.domain.entity.Usuario;
import com.faculdade.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public void delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario authenticate(String cpf, String senha) {
        Usuario usuario = usuarioRepository.findByCpf(cpf);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }

        return null;
    }
}
