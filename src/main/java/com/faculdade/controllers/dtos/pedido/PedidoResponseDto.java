package com.faculdade.controllers.dtos.pedido;

import com.faculdade.controllers.dtos.pedidorefeicao.PedidoRefeicaoDto;

import java.util.List;

public record PedidoResponseDto ( Long idPedido,
                                  String statusPedido,
                                  Boolean confirmacaoGarcom,
                                  Boolean confirmacaoPaciente,
                                  List<PedidoRefeicaoDto> pedidoRefeicoes ) {

}
