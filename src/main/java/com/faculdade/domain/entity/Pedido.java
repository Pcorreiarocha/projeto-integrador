package com.faculdade.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonFormat(pattern = "statusPedido")
    private String statusPedido;

    @JsonFormat(pattern = "confirmacaoGarcom")
    private Boolean confirmacaoGarcom;

    @JsonFormat(pattern = "confirmacaoPaciente")
    private Boolean confirmacaoPaciente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PedidoRefeicao> pedidoRefeicoes = new ArrayList<>();
}
