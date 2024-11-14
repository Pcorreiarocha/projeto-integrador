package com.faculdade.controllers.dtos.pedido;

import com.faculdade.controllers.dtos.pedido_refeicao.PedidoRefeicaoDto;

import java.util.List;

public record PedidoResponseDto ( Long idPedido,
                                  String statusPedido,
                                  Long numeroQuarto,
                                  Boolean confirmacaoGarcom,
                                  Boolean confirmacaoPaciente,
                                  List<PedidoRefeicaoDto> pedidoRefeicoes ) {

}
