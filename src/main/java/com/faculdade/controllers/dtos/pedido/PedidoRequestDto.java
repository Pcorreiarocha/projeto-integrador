package com.faculdade.controllers.dtos.pedido;

import com.faculdade.controllers.dtos.pedidorefeicao.PedidoRefeicaoDto;

import java.util.List;

public record PedidoRequestDto( Long idPedido,
                                String statusPedido,
                                List<PedidoRefeicaoDto> pedidoRefeicoes ) {
}
