package com.faculdade.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PacienteEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long idPaciente;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "idMedico" )
    private UsuarioEntity medico;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "idUsuario" )
    private UsuarioEntity usuario;

    @OneToOne( mappedBy = "paciente", cascade = CascadeType.ALL )
    private FormularioMedicoEntity formularioMedico;
}
