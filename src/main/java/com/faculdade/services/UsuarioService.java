package com.faculdade.services;

import com.faculdade.commons.exceptions.NegocioException;
import com.faculdade.controllers.dtos.perfil.PerfilTypeResponseDto;
import com.faculdade.controllers.dtos.usuario.UsuarioRequestDto;
import com.faculdade.controllers.dtos.usuario.UsuarioResponseDto;
import com.faculdade.domain.entity.MedicoEntity;
import com.faculdade.domain.entity.PacienteEntity;
import com.faculdade.domain.entity.PerfilEntity;
import com.faculdade.domain.entity.UsuarioEntity;
import com.faculdade.repositories.MedicoRepository;
import com.faculdade.repositories.PacienteRepository;
import com.faculdade.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final ModelMapper        modelMapper;
    private final MedicoRepository medicoRepository;
    private       UsuarioRepository  usuarioRepository;
    private       PacienteRepository pacienteRepository;
    private final PasswordEncoder    passwordEncoder;

    public UsuarioResponseDto save( UsuarioRequestDto usuarioRequestDto ) {
        UsuarioEntity usuarioEntity = modelMapper.map( usuarioRequestDto, UsuarioEntity.class );
        
        usuarioEntity.setSenha( passwordEncoder.encode( usuarioEntity.getSenha() ) );
        usuarioEntity.setDataCadastro( LocalDateTime.now() );

        PerfilEntity perfilEntity = new PerfilEntity();
        perfilEntity.setCodigo( usuarioRequestDto.perfil().codigo() );
        perfilEntity.setDescricao( usuarioRequestDto.perfil().descricao() );
        usuarioEntity.setPerfil( perfilEntity );

        PerfilTypeResponseDto perfilTypeResponseDto = new PerfilTypeResponseDto( usuarioRequestDto.perfil().codigo(),
                                                                                 usuarioRequestDto.perfil().descricao(),
                                                                                 "S" );

        usuarioRepository.save( usuarioEntity );

        if( usuarioEntity.getPerfil().getDescricao().equals( "Paciente" ) ) {
            PacienteEntity pacienteEntity = new PacienteEntity();
            pacienteEntity.setUsuario( usuarioEntity );
            pacienteEntity.setMedico( null );
            pacienteRepository.save( pacienteEntity );
        }

        if( usuarioEntity.getPerfil().getDescricao().equals( "Médico" ) ) {
            MedicoEntity medicoEntity = new MedicoEntity();
            medicoEntity.setUsuario( usuarioEntity );
            medicoEntity.setPaciente( null );
            medicoRepository.save( medicoEntity );
        }

        return new UsuarioResponseDto( usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getCpf(), usuarioEntity.getSenha(),
                                       usuarioEntity.getDataNascimento(), usuarioEntity.getSexo(), usuarioEntity.getDataCadastro(), perfilTypeResponseDto );
    }

    public UsuarioResponseDto findById( Long id ) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById( id )
                .orElseThrow( () -> new NegocioException( "Usuário não encontrado" ) );

        PerfilTypeResponseDto perfilTypeResponseDto = new PerfilTypeResponseDto( usuarioEntity.getPerfil().getCodigo(),
                                                                                 usuarioEntity.getPerfil().getDescricao(),
                                                                            "S" );

        return new UsuarioResponseDto( usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getCpf(), usuarioEntity.getSenha(),
                                       usuarioEntity.getDataNascimento(), usuarioEntity.getSexo(), usuarioEntity.getDataCadastro(), perfilTypeResponseDto );
    }

    public void delete( Long id ) {
        UsuarioResponseDto usuarioResponseDto = findById(id);
        UsuarioEntity usuarioEntity = modelMapper.map( usuarioResponseDto, UsuarioEntity.class );
        usuarioRepository.delete( usuarioEntity );
    }

    public List<UsuarioResponseDto> findAll() {
        return usuarioRepository.findAll().stream()
                                .map(usuarioEntity -> new UsuarioResponseDto(
                                        usuarioEntity.getId(),
                                        usuarioEntity.getNome(),
                                        usuarioEntity.getCpf(),
                                        usuarioEntity.getSenha(),
                                        usuarioEntity.getDataNascimento(),
                                        usuarioEntity.getSexo(),
                                        usuarioEntity.getDataCadastro(),
                                        new PerfilTypeResponseDto( usuarioEntity.getPerfil().getCodigo(),
                                                                   usuarioEntity.getPerfil().getDescricao(),
                                                                   usuarioEntity.getPerfil().getAtivo() )
                                ) )
                                .collect( Collectors.toList() );
    }


    public UsuarioResponseDto authenticate( String cpf, String senha ) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCpf( cpf );
        if( usuarioEntity != null && passwordEncoder.matches( senha, usuarioEntity.getSenha() ) ) {
            return new UsuarioResponseDto( usuarioEntity.getId(), usuarioEntity.getNome(), usuarioEntity.getCpf(), usuarioEntity.getSenha(),
                                           usuarioEntity.getDataNascimento(), usuarioEntity.getSexo(),
                                           usuarioEntity.getDataCadastro(), convertToDto( usuarioEntity.getPerfil() ) );
        }

        return null;
    }

    public PerfilTypeResponseDto convertToDto( PerfilEntity perfilEntity ) {
        return new PerfilTypeResponseDto( perfilEntity.getCodigo(), perfilEntity.getDescricao(), perfilEntity.getAtivo() );
    }
}
