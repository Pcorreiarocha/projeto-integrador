package com.faculdade.controllers;

import com.faculdade.commons.support.Response;
import com.faculdade.controllers.dtos.pedido.PedidoRequestDto;
import com.faculdade.controllers.dtos.pedido.PedidoResponseDto;
import com.faculdade.services.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Response<PedidoResponseDto>> criarPedido( @RequestBody PedidoRequestDto pedidoRequestDto ) {
        return ResponseEntity.ok( Response.<PedidoResponseDto>builder()
                .code( 0 )
                .message( "OK" )
                .success( Boolean.TRUE )
                .data( this.pedidoService.salvarPedido( pedidoRequestDto ) )
                .build() );
    }

    @GetMapping
    public ResponseEntity<Response<List<PedidoResponseDto>>> listarPedidos() {
        List<PedidoResponseDto> pedidoResponseDtoList = pedidoService.findAll();
        return pedidoResponseDtoList.isEmpty() ? new ResponseEntity<>(
                Response.<List<PedidoResponseDto>>builder()
                        .build(), HttpStatus.NO_CONTENT ) : ResponseEntity.ok( Response.<List<PedidoResponseDto>>builder()
                                                                                       .code( 0 )
                                                                                       .success( Boolean.TRUE )
                                                                                       .message( "OK" )
                                                                                       .data( pedidoResponseDtoList )
                                                                                       .build() );
    }
}
