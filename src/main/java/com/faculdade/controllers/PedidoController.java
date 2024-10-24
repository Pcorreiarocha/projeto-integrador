package com.faculdade.controllers;

import com.faculdade.domain.entity.PedidoEntity;
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
    public ResponseEntity< PedidoEntity > criarPedido(@RequestBody PedidoEntity pedidoEntity ) {
        PedidoEntity novoPedidoEntity = pedidoService.criarPedido( pedidoEntity );
        return ResponseEntity.ok( novoPedidoEntity );
    }
}
