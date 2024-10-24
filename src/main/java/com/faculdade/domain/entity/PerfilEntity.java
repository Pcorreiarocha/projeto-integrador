package com.faculdade.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PerfilEntity {

    @Id
    private Long codigo;

    private String descricao;

    private String ativo;
}
