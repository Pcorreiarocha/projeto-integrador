package com.faculdade.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class RefeicaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRefeicao;

    @JsonFormat(pattern = "nome")
    private String nome;

    @JsonFormat(pattern = "descricao")
    private String descricao;

    @JsonFormat(pattern = "disponibilidade")
    private String disponibilidade;

    @OneToMany(mappedBy = "refeicaoEntity")
    private List< PedidoRefeicaoEntity > pedidoRefeicoes = new ArrayList<>();
}
