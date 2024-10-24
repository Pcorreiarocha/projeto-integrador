package com.faculdade.services;

import com.faculdade.domain.entity.RefeicaoEntity;
import com.faculdade.controllers.dtos.refeicao.RefeicaoResponseDto;
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

    public RefeicaoEntity findById(Long id) {
        Optional< RefeicaoEntity > refeicao = refeicaoRepository.findById(id);
        return refeicao.orElse(null);
    }

    public RefeicaoEntity save( RefeicaoEntity refeicaoEntity ) {
        refeicaoEntity.setDisponibilidade("Ativo");
        return refeicaoRepository.save( refeicaoEntity );
    }

    public void delete(Long id) {
        RefeicaoEntity refeicaoEntity = findById(id);
        refeicaoEntity.setDisponibilidade("Inativo");
        refeicaoRepository.save( refeicaoEntity );
    }

    public List< RefeicaoResponseDto > findAll() {
        List< RefeicaoEntity > refeicoes = refeicaoRepository.findAll();
        return refeicoes.stream()
                .filter( refeicaoEntity -> "Ativo".equals( refeicaoEntity.getDisponibilidade()))
                .map( refeicaoEntity -> new RefeicaoResponseDto( refeicaoEntity.getIdRefeicao(), refeicaoEntity.getNome(), refeicaoEntity.getDescricao()))
                .collect(Collectors.toList());
    }
}
