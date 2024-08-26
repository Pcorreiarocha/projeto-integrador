package com.faculdade.services;

import com.faculdade.domain.entity.Refeicao;
import com.faculdade.repositories.RefeicaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RefeicaoService {

    private RefeicaoRepository refeicaoRepository;

    public Refeicao findById(Long id) {
        Optional<Refeicao> refeicao = refeicaoRepository.findById(id);
        return refeicao.orElse(null);
    }

    public Refeicao save(Refeicao refeicao) {
        return refeicaoRepository.save(refeicao);
    }

    public void delete(Long id) {
        Refeicao refeicao = findById(id);
        refeicaoRepository.delete(refeicao);
    }

    public List<Refeicao> findAll() {
        return refeicaoRepository.findAll();
    }
}
