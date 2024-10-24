package com.faculdade.services;

import com.faculdade.domain.entity.PedidoEntity;
import com.faculdade.domain.entity.PedidoRefeicaoEntity;
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
    public PedidoEntity criarPedido( PedidoEntity pedidoEntity ) {
        PedidoEntity novoPedidoEntity = pedidoRepository.save( pedidoEntity );

        for ( PedidoRefeicaoEntity pedidoRefeicaoEntity : pedidoEntity.getPedidoRefeicoes()) {
            pedidoRefeicaoEntity.setPedidoEntity( novoPedidoEntity );
            pedidoRefeicaoRepository.save( pedidoRefeicaoEntity );
        }

        return novoPedidoEntity;
    }
}
