package com.faculdade.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FormularioMedicoEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idFormulario;

    @JsonFormat( pattern = "tipoDieta" )
    private String tipoDieta;

    @JsonFormat( pattern = "viaAdministracao" )
    private String viaAdministracao;

    @JsonFormat( pattern = "observacao" )
    private String observacao;

    @ManyToOne
    @JoinColumn( name = "idMedico" )
    private MedicoEntity medico;

    @OneToOne
    @JoinColumn( name = "idPaciente")
    private PacienteEntity paciente;
}
