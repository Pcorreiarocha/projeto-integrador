package com.faculdade.controllers.dtos.refeicao;

public record RefeicaoResponseDto( Long idRefeicao,
                                   String nome,
                                   String descricao,
                                   String disponibilidade ) {

}
