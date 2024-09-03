package com.faculdade.services;

import com.faculdade.domain.entity.Pedido;
import com.faculdade.domain.entity.PedidoRefeicao;
import com.faculdade.repositories.PedidoRefeicaoRepository;
import com.faculdade.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoRepository pedidoRepository;

    private PedidoRefeicaoRepository pedidoRefeicaoRepository;

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        Pedido novoPedido = pedidoRepository.save(pedido);

        for (PedidoRefeicao pedidoRefeicao : pedido.getPedidoRefeicoes()) {
            pedidoRefeicao.setPedido(novoPedido);
            pedidoRefeicaoRepository.save(pedidoRefeicao);
        }

        return novoPedido;
    }
}
