package com.faculdade.controllers.dtos.pedido_refeicao;

import com.faculdade.controllers.dtos.refeicao.RefeicaoResponseDto;

public record PedidoRefeicaoDto( RefeicaoResponseDto refeicao,
                                 Long quantidadeRefeicao ) {

}
