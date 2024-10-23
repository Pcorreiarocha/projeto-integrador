package com.faculdade.services;

import com.faculdade.controllers.dtos.usuario.UsuarioRequestDto;
import com.faculdade.controllers.dtos.usuario.UsuarioResponseDto;
import com.faculdade.domain.entity.PacienteEntity;
import com.faculdade.domain.entity.UsuarioEntity;
import com.faculdade.repositories.PacienteRepository;
import com.faculdade.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;
    private PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto save( UsuarioRequestDto usuarioRequestDto ) {
        UsuarioEntity usuarioEntity = modelMapper.map( usuarioRequestDto, UsuarioEntity.class );
        usuarioEntity.setSenha( passwordEncoder.encode( usuarioEntity.getSenha() ) );
        usuarioEntity.setDataCadastro( LocalDateTime.now() );

        usuarioRepository.save( usuarioEntity );

        /*if( usuarioEntity.getPerfil().getDescricao().equals( "Paciente" ) ) {
            PacienteEntity pacienteEntity = new PacienteEntity();
            pacienteEntity.setUsuario( usuarioEntity );
            pacienteEntity.setMedico( null );
            pacienteRepository.save( pacienteEntity );
        }*/

        return new UsuarioResponseDto( usuarioEntity.getNome(), usuarioEntity.getCpf(), usuarioEntity.getSenha(), usuarioEntity.getDataNascimento(),
                                       usuarioEntity.getSexo(), usuarioEntity.getDataCadastro());
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
