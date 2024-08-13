package com.faculdade.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private String statusPedido;

    private Boolean confirmacaoGarcom;

    private Boolean confirmacaoPaciente;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoRefeicao> pedidoRefeicoes = new ArrayList<>();
}
