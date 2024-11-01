package com.faculdade.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MedicoEntity{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long idMedico;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "idPaciente")
    private PacienteEntity paciente;
}
