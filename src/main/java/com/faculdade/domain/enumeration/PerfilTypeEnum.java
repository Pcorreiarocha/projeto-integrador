package com.faculdade.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PerfilTypeEnum {

    ADMINISTRADOR(0, "Administrador"),
    PACIENTE(1, "Paciente"),
    NUTRICIONISTA(2, "Nutricionista"),
    COZINHA(3, "Cozinha"),
    GARCOM(4, "Garçom");

    private final Integer codigo;

    private final String descricao;
}
