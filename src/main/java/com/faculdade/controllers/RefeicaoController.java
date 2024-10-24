package com.faculdade.controllers;

import com.faculdade.domain.entity.RefeicaoEntity;
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

    @PostMapping
    public ResponseEntity< RefeicaoEntity > save(@RequestBody RefeicaoEntity refeicaoEntity ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoService.save( refeicaoEntity ));
    }

    @GetMapping("/{id}")
    public ResponseEntity< RefeicaoResponseDto > findById(@PathVariable Long id) {
        RefeicaoEntity refeicaoEntity = refeicaoService.findById(id);
        if ( refeicaoEntity != null) {
            RefeicaoResponseDto response = new RefeicaoResponseDto( refeicaoEntity.getIdRefeicao(), refeicaoEntity.getNome(), refeicaoEntity.getDescricao());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity< RefeicaoResponseDto > update(@PathVariable Long id, @RequestBody RefeicaoEntity refeicaoEntityAtualizada ) {
        RefeicaoEntity refeicaoEntity = refeicaoService.findById(id);
        if ( refeicaoEntity != null) {
            refeicaoEntityAtualizada.setIdRefeicao(id);
            RefeicaoEntity updatedRefeicaoEntity = refeicaoService.save( refeicaoEntityAtualizada );
            RefeicaoResponseDto response = new RefeicaoResponseDto( updatedRefeicaoEntity.getIdRefeicao(), updatedRefeicaoEntity.getNome(), updatedRefeicaoEntity.getDescricao());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        RefeicaoEntity refeicaoEntity = refeicaoService.findById(id);
        if ( refeicaoEntity != null) {
            refeicaoService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List< RefeicaoResponseDto >> findAll() {
        List< RefeicaoResponseDto > refeicoes = refeicaoService.findAll();
        return ResponseEntity.ok(refeicoes);
    }
}
