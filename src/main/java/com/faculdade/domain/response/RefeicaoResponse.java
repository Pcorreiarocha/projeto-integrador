package com.faculdade.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefeicaoResponse {

    private Long idRefeicao;
    private String nome;
    private String descricao;
}
