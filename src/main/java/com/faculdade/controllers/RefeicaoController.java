package com.faculdade.controllers;

import com.faculdade.commons.support.Response;
import com.faculdade.controllers.dtos.refeicao.RefeicaoRequestDto;
import com.faculdade.controllers.dtos.refeicao.RefeicaoResponseDto;
import com.faculdade.services.RefeicaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/refeicao")
public class RefeicaoController {

    private RefeicaoService refeicaoService;

    @GetMapping( "/{idRefeicao}" )
    public ResponseEntity<Response<RefeicaoResponseDto>> findById( @PathVariable Long idRefeicao ) {
        return ResponseEntity.ok( Response.<RefeicaoResponseDto>builder()
                .code( 0 )
                .success( Boolean.TRUE )
                .message( "OK" )
                .data( this.refeicaoService.findById( idRefeicao ) )
                .build() );

    }

    @PostMapping
    public ResponseEntity<Response<RefeicaoResponseDto>> save( @RequestBody RefeicaoRequestDto refeicaoRequestDto ) {
        return ResponseEntity.status( HttpStatus.CREATED )
                             .body( Response.<RefeicaoResponseDto>builder()
                                     .code( 0 )
                                     .success( Boolean.TRUE )
                                     .message( "OK" )
                                     .data( this.refeicaoService.save( refeicaoRequestDto ) )
                                     .build() );
    }

    @DeleteMapping( "/{idRefeicao}" )
    public ResponseEntity<Response<Void>> inativar( @PathVariable Long idRefeicao ) {
        this.refeicaoService.inativar( idRefeicao );
        return ResponseEntity.accepted().body(
                Response.<Void>builder().code( 0 ).success( Boolean.TRUE ).message( "OK" ).build() );
    }

    @GetMapping("/")
    public ResponseEntity<Response<List<RefeicaoResponseDto>>> findAll() {
        List<RefeicaoResponseDto> refeicaoResponseDtoList = refeicaoService.findAll();
        return refeicaoResponseDtoList.isEmpty() ? new ResponseEntity<>(
                Response.<List<RefeicaoResponseDto>>builder()
                        .build(), HttpStatus.NO_CONTENT ) : ResponseEntity.ok( Response.<List<RefeicaoResponseDto>>builder()
                                                                                       .code( 0 )
                                                                                       .success( Boolean.TRUE )
                                                                                       .message( "OK" )
                                                                                       .data( refeicaoResponseDtoList )
                                                                                       .build() );
    }
}
