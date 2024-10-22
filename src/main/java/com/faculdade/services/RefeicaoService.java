package com.faculdade.services;

import com.faculdade.domain.entity.Refeicao;
import com.faculdade.domain.response.RefeicaoResponse;
import com.faculdade.repositories.RefeicaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RefeicaoService {

    private RefeicaoRepository refeicaoRepository;

    public Refeicao findById(Long id) {
        Optional<Refeicao> refeicao = refeicaoRepository.findById(id);
        return refeicao.orElse(null);
    }

    public Refeicao save(Refeicao refeicao) {
        refeicao.setDisponibilidade("Ativo");
        return refeicaoRepository.save(refeicao);
    }

    public void delete(Long id) {
        Refeicao refeicao = findById(id);
        refeicao.setDisponibilidade("Inativo");
        refeicaoRepository.save(refeicao);
    }

    public List<RefeicaoResponse> findAll() {
        List<Refeicao> refeicoes = refeicaoRepository.findAll();
        return refeicoes.stream()
                .filter(refeicao -> "Ativo".equals(refeicao.getDisponibilidade()))
                .map(refeicao -> new RefeicaoResponse(refeicao.getIdRefeicao(), refeicao.getNome(), refeicao.getDescricao()))
                .collect(Collectors.toList());
    }
}
