package com.faculdade.controllers;

import com.faculdade.commons.support.Response;
import com.faculdade.controllers.dtos.pedido.PedidoRequestDto;
import com.faculdade.controllers.dtos.pedido.PedidoResponseDto;
import com.faculdade.services.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
