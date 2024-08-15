package com.faculdade.controllers;

import com.faculdade.domain.entity.Refeicao;
import com.faculdade.domain.entity.Usuario;
import com.faculdade.services.RefeicaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Ref;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/refeicao")
public class RefeicaoController {

    private RefeicaoService refeicaoService;

    @PostMapping
    public ResponseEntity<Refeicao> save(@RequestBody Refeicao refeicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(refeicaoService.save(refeicao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refeicao> findById(@PathVariable Long id) {
        Refeicao refeicao = refeicaoService.findById(id);
        if (refeicao != null) {
            return ResponseEntity.ok(refeicao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Refeicao> update(@PathVariable Long id, @RequestBody Refeicao refeicaoAtualizada) {
        Refeicao refeicao = refeicaoService.findById(id);
        if (refeicao != null) {
            refeicaoAtualizada.setIdRefeicao(id);
            return ResponseEntity.ok(refeicaoService.save(refeicaoAtualizada));
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
    public ResponseEntity<List<Refeicao>> findAll() {
        List<Refeicao> refeicoes = refeicaoService.findAll();
        return ResponseEntity.ok(refeicoes);
    }
}
