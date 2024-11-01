package com.faculdade.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
