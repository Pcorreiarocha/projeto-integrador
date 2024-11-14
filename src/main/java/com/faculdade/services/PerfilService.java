package com.faculdade.services;

import com.faculdade.domain.entity.MedicoEntity;
import com.faculdade.domain.entity.PacienteEntity;
import com.faculdade.domain.entity.UsuarioEntity;
import com.faculdade.repositories.MedicoRepository;
import com.faculdade.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PerfilService {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public void atribuir( String perfil, UsuarioEntity usuario ) {
        if( perfil.equals( "Paciente" ) ) {
            PacienteEntity pacienteEntity = new PacienteEntity();
            pacienteEntity.setUsuario( usuario );
            pacienteEntity.setMedico( null );
            pacienteRepository.save( pacienteEntity );
        }

        if( perfil.equals( "Médico" ) ) {
            MedicoEntity medicoEntity = new MedicoEntity();
            medicoEntity.setUsuario( usuario );
            medicoEntity.setPaciente( null );
            medicoRepository.save( medicoEntity );
        }
    }
}
