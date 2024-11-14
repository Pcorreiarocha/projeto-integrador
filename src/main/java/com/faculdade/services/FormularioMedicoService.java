package com.faculdade.services;

import com.faculdade.commons.exceptions.NegocioException;
import com.faculdade.controllers.dtos.formulario_medico.FormularioMedicoRequestDto;
import com.faculdade.controllers.dtos.formulario_medico.FormularioMedicoResponseDto;
import com.faculdade.domain.entity.FormularioMedicoEntity;
import com.faculdade.repositories.FormularioMedicoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FormularioMedicoService{

    private final ModelMapper modelMapper;
    private FormularioMedicoRepository formularioMedicoRepository;

    /*public FormularioMedicoResponseDto findById( Long idFormulario ) {
        FormularioMedicoEntity formularioMedicoEntity = formularioMedicoRepository.findById( idFormulario )
                                                                                  .orElseThrow( () -> new NegocioException( "Formulário não encontrado" ) );

        return new FormularioMedicoResponseDto( formularioMedicoEntity.getIdFormulario(),
                                                formularioMedicoEntity.getTipoDieta(),
                                                formularioMedicoEntity.getViaAdministracao(),
                                                formularioMedicoEntity.getObservacao() );
    }*/

    public FormularioMedicoResponseDto save( FormularioMedicoRequestDto formularioMedicoRequestDto ) {
        System.out.println( formularioMedicoRequestDto.nomeMedico() );
        System.out.println( formularioMedicoRequestDto.tipoDieta() );

        FormularioMedicoEntity formularioMedicoEntity = modelMapper.map( formularioMedicoRequestDto, FormularioMedicoEntity.class );

        formularioMedicoRepository.save( formularioMedicoEntity );

        return new FormularioMedicoResponseDto( formularioMedicoEntity.getIdFormulario(),
                                                formularioMedicoRequestDto.nomeMedico(),
                                                formularioMedicoRequestDto.nomePaciente(),
                                                formularioMedicoEntity.getTipoDieta(),
                                                formularioMedicoEntity.getViaAdministracao(),
                                                formularioMedicoEntity.getObservacao() );
    }

    /*public List<FormularioMedicoResponseDto> findAll() {
        return formularioMedicoRepository.findAll().stream()
                .map( formularioMedicoEntity -> new FormularioMedicoResponseDto(
                        formularioMedicoEntity.getIdFormulario(),
                        formularioMedicoEntity.getTipoDieta(),
                        formularioMedicoEntity.getViaAdministracao(),
                        formularioMedicoEntity.getObservacao()
                ) )
                .collect( Collectors.toList() );
    }*/
}
