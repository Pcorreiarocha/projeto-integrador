package com.faculdade.services;

import com.faculdade.commons.exceptions.NegocioException;
import com.faculdade.controllers.dtos.formulario_medico.FormularioMedicoRequestDto;
import com.faculdade.controllers.dtos.formulario_medico.FormularioMedicoResponseDto;
import com.faculdade.controllers.dtos.usuario.UsuarioResponseDto;
import com.faculdade.domain.entity.FormularioMedicoEntity;
import com.faculdade.domain.entity.UsuarioEntity;
import com.faculdade.repositories.FormularioMedicoRepository;
import com.faculdade.repositories.MedicoRepository;
import com.faculdade.repositories.PacienteRepository;
import com.faculdade.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FormularioMedicoService{

    private final ModelMapper modelMapper;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;
    private FormularioMedicoRepository formularioMedicoRepository;
    private UsuarioService usuarioService;

    public FormularioMedicoResponseDto findById( Long idFormulario ) {
        FormularioMedicoEntity formularioMedicoEntity = formularioMedicoRepository.findById( idFormulario )
                                                                                  .orElseThrow( () -> new NegocioException( "Formulário não encontrado" ) );

        UsuarioResponseDto medico = usuarioService.findById( formularioMedicoEntity.getMedico().getUsuario().getId() );
        UsuarioResponseDto paciente = usuarioService.findById( formularioMedicoEntity.getPaciente().getUsuario().getId() );

        return new FormularioMedicoResponseDto( formularioMedicoEntity.getIdFormulario(),
                                                medico,
                                                paciente,
                                                formularioMedicoEntity.getTipoDieta(),
                                                formularioMedicoEntity.getViaAdministracao(),
                                                formularioMedicoEntity.getObservacao() );
    }

    public FormularioMedicoResponseDto save( FormularioMedicoRequestDto formularioMedicoRequestDto ) {
        FormularioMedicoEntity formularioMedicoEntity = modelMapper.map( formularioMedicoRequestDto, FormularioMedicoEntity.class );

        formularioMedicoEntity.setMedico( medicoRepository.findByUsuario( usuarioService.findByIdEntity( formularioMedicoRequestDto
                                                                                                                 .medico()
                                                                                                                 .id()) ) );
        formularioMedicoEntity.setPaciente( pacienteRepository.findByUsuario( usuarioService.findByIdEntity( formularioMedicoRequestDto
                                                                                                                     .paciente()
                                                                                                                     .id()) ) );

        formularioMedicoRepository.save( formularioMedicoEntity );

        return new FormularioMedicoResponseDto( formularioMedicoEntity.getIdFormulario(),
                                                formularioMedicoRequestDto.medico(),
                                                formularioMedicoRequestDto.paciente(),
                                                formularioMedicoEntity.getTipoDieta(),
                                                formularioMedicoEntity.getViaAdministracao(),
                                                formularioMedicoEntity.getObservacao() );
    }

    public List<FormularioMedicoResponseDto> findAll() {
        return formularioMedicoRepository.findAll().stream()
                .map( formularioMedicoEntity -> new FormularioMedicoResponseDto(
                        formularioMedicoEntity.getIdFormulario(),
                        usuarioService.findById( formularioMedicoEntity.getMedico().getUsuario().getId() ),
                        usuarioService.findById( formularioMedicoEntity.getPaciente().getUsuario().getId() ),
                        formularioMedicoEntity.getTipoDieta(),
                        formularioMedicoEntity.getViaAdministracao(),
                        formularioMedicoEntity.getObservacao()
                ) )
                .collect( Collectors.toList() );
    }
}
