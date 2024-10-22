package com.faculdade.controllers;

import com.faculdade.domain.entity.Refeicao;
import com.faculdade.domain.response.RefeicaoResponse;
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
    public ResponseEntity<Refeicao> save(@RequestBody Refeicao refeicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoService.save(refeicao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefeicaoResponse> findById(@PathVariable Long id) {
        Refeicao refeicao = refeicaoService.findById(id);
        if (refeicao != null) {
            RefeicaoResponse response = new RefeicaoResponse(refeicao.getIdRefeicao(), refeicao.getNome(), refeicao.getDescricao());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefeicaoResponse> update(@PathVariable Long id, @RequestBody Refeicao refeicaoAtualizada) {
        Refeicao refeicao = refeicaoService.findById(id);
        if (refeicao != null) {
            refeicaoAtualizada.setIdRefeicao(id);
            Refeicao updatedRefeicao = refeicaoService.save(refeicaoAtualizada);
            RefeicaoResponse response = new RefeicaoResponse(updatedRefeicao.getIdRefeicao(), updatedRefeicao.getNome(), updatedRefeicao.getDescricao());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Refeicao refeicao = refeicaoService.findById(id);
        if (refeicao != null) {
            refeicaoService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<RefeicaoResponse>> findAll() {
        List<RefeicaoResponse> refeicoes = refeicaoService.findAll();
        return ResponseEntity.ok(refeicoes);
    }
}
