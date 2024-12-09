package com.faculdade.services;

import com.faculdade.commons.exceptions.NegocioException;
import com.faculdade.controllers.dtos.perfil.PerfilTypeResponseDto;
import com.faculdade.controllers.dtos.usuario.UsuarioRequestDto;
import com.faculdade.controllers.dtos.usuario.UsuarioResponseDto;
import com.faculdade.domain.entity.PerfilEntity;
import com.faculdade.domain.entity.UsuarioEntity;
import com.faculdade.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final ModelMapper        modelMapper;
    private       UsuarioRepository  usuarioRepository;
    private final PasswordEncoder    passwordEncoder;
    private       PerfilService      perfilService;

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

        UsuarioEntity usuarioSalvo = usuarioRepository.save( usuarioEntity );

        perfilService.atribuir( usuarioEntity.getPerfil().getDescricao(), usuarioSalvo );

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
        UsuarioResponseDto usuarioResponseDto = findById( id );
        UsuarioEntity usuarioEntity = modelMapper.map( usuarioResponseDto, UsuarioEntity.class );
        usuarioRepository.delete( usuarioEntity );
    }

    public List<UsuarioResponseDto> findAll() {
        return usuarioRepository.findAll().stream()
                .filter( usuarioEntity -> "Paciente".equals( usuarioEntity.getPerfil().getDescricao() ) )
                                .map( usuarioEntity -> new UsuarioResponseDto(
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

    public UsuarioEntity findByIdEntity( Long id ) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById( id )
                                                       .orElseThrow( () -> new NegocioException( "Usuário não encontrado" ) );
        return usuarioEntity;
    }

    public PerfilTypeResponseDto convertToDto( PerfilEntity perfilEntity ) {
        return new PerfilTypeResponseDto( perfilEntity.getCodigo(), perfilEntity.getDescricao(), perfilEntity.getAtivo() );
    }

    public Optional<UsuarioEntity> buscarUsuarioPorNome( String nome ) {
        return usuarioRepository.findFirstByNomeContaining( nome );
    }
}
