package com.faculdade.domain.entity;

import jakarta.persistence.*;

@Entity
public class PedidoRefeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_refeicao")
    private Refeicao refeicao;

    private Long quantidadeRefeicao;
}
