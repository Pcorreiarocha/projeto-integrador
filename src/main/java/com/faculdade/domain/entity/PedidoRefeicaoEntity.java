package com.faculdade.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PedidoRefeicaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    @JsonBackReference
    private PedidoEntity pedidoEntity;

    @ManyToOne
    @JoinColumn(name = "id_refeicao")
    private RefeicaoEntity refeicaoEntity;

    private Long quantidadeRefeicao;
}
