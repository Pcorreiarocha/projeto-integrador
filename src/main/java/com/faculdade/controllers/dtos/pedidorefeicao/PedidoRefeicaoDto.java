package com.faculdade.controllers.dtos.pedidorefeicao;

import com.faculdade.controllers.dtos.refeicao.RefeicaoResponseDto;

public record PedidoRefeicaoDto( RefeicaoResponseDto refeicao,
                                 Long quantidadeRefeicao ) {

}
