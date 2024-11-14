package com.faculdade.services;

import com.faculdade.commons.exceptions.NegocioException;
import com.faculdade.controllers.dtos.pedido.PedidoRequestDto;
import com.faculdade.controllers.dtos.pedido.PedidoResponseDto;
import com.faculdade.controllers.dtos.pedido_refeicao.PedidoRefeicaoDto;
import com.faculdade.domain.entity.PedidoEntity;
import com.faculdade.domain.entity.PedidoRefeicaoEntity;
import com.faculdade.domain.entity.RefeicaoEntity;
import com.faculdade.repositories.PedidoRefeicaoRepository;
import com.faculdade.repositories.PedidoRepository;
import com.faculdade.repositories.RefeicaoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidoService {

    private final ModelMapper              modelMapper;
    private final RefeicaoRepository       refeicaoRepository;
    private       PedidoRepository         pedidoRepository;
    private       PedidoRefeicaoRepository pedidoRefeicaoRepository;

    @Transactional
    public PedidoResponseDto salvarPedido( PedidoRequestDto pedidoRequestDto ) {
        PedidoEntity pedidoEntity = modelMapper.map( pedidoRequestDto, PedidoEntity.class );
        pedidoEntity.setConfirmacaoGarcom( Boolean.FALSE );
        pedidoEntity.setConfirmacaoPaciente( Boolean.TRUE );

        PedidoEntity pedidoSalvo = pedidoRepository.save( pedidoEntity );

        List<PedidoRefeicaoEntity> pedidoRefeicoes = pedidoRequestDto.pedidoRefeicoes().stream()
                .map( dto -> convertToPedidoRefeicaoEntity( dto, pedidoSalvo ) )
                .collect( Collectors.toList() );

        pedidoEntity.setPedidoRefeicoes( pedidoRefeicoes );

        return new PedidoResponseDto( pedidoEntity.getIdPedido(),
                                      pedidoEntity.getStatusPedido(),
                                      pedidoEntity.getNumeroQuarto(),
                                      pedidoEntity.getConfirmacaoGarcom(),
                                      pedidoEntity.getConfirmacaoPaciente(),
                                      pedidoRequestDto.pedidoRefeicoes() );
    }

    private PedidoRefeicaoEntity convertToPedidoRefeicaoEntity( PedidoRefeicaoDto pedidoRefeicaoDto, PedidoEntity pedidoSalvo ) {
        PedidoRefeicaoEntity pedidoRefeicaoEntity = new PedidoRefeicaoEntity();
        pedidoRefeicaoEntity.setQuantidadeRefeicao( pedidoRefeicaoDto.quantidadeRefeicao() );
        System.out.println( "pedido: " + pedidoSalvo.getIdPedido() );
        pedidoRefeicaoEntity.setPedidoEntity( pedidoSalvo );

        RefeicaoEntity refeicaoEntity = refeicaoRepository.getReferenceById( pedidoRefeicaoDto.refeicao().idRefeicao() );
        if( Objects.isNull( refeicaoEntity ) ) {
            throw new NegocioException( "Refeição não encontrada" );
        }

        pedidoRefeicaoEntity.setRefeicaoEntity( refeicaoEntity );
        pedidoRefeicaoRepository.save( pedidoRefeicaoEntity );

        return pedidoRefeicaoEntity;
    }
}
