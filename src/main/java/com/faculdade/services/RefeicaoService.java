package com.faculdade.services;

import com.faculdade.commons.exceptions.NegocioException;
import com.faculdade.controllers.dtos.refeicao.RefeicaoRequestDto;
import com.faculdade.domain.entity.RefeicaoEntity;
import com.faculdade.controllers.dtos.refeicao.RefeicaoResponseDto;
import com.faculdade.repositories.RefeicaoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RefeicaoService {

    private final ModelMapper modelMapper;
    private RefeicaoRepository refeicaoRepository;

    public RefeicaoResponseDto findById( Long idRefeicao ) {
        RefeicaoEntity refeicaoEntity = refeicaoRepository.findById( idRefeicao )
                .orElseThrow( () -> new NegocioException( "Refeição não encontrado" ) );

        return new RefeicaoResponseDto( refeicaoEntity.getIdRefeicao(),
                refeicaoEntity.getNome(),
                refeicaoEntity.getDescricao(),
                refeicaoEntity.getDisponibilidade());
    }

    public RefeicaoResponseDto save( RefeicaoRequestDto refeicaoRequestDto ) {
        RefeicaoEntity refeicaoEntity = modelMapper.map( refeicaoRequestDto, RefeicaoEntity.class );
        refeicaoEntity.setDisponibilidade( "Ativo" );

        refeicaoRepository.save( refeicaoEntity );

        return new RefeicaoResponseDto( refeicaoEntity.getIdRefeicao(),
                refeicaoEntity.getNome(),
                refeicaoEntity.getDescricao(),
                refeicaoEntity.getDisponibilidade());
    }

    public void inativar( Long idRefeicao ) {
        RefeicaoResponseDto refeicaoResponseDto = findById( idRefeicao );
        RefeicaoEntity refeicaoEntity = modelMapper.map( refeicaoResponseDto, RefeicaoEntity.class );
        refeicaoEntity.setDisponibilidade( "Inativo" );
        refeicaoRepository.save( refeicaoEntity );
    }

    public List<RefeicaoResponseDto> findAll() {
        return refeicaoRepository.findAll().stream()
                .filter( refeicaoEntity -> "Ativo".equals( refeicaoEntity.getDisponibilidade() ) )
                .map( refeicaoEntity -> new RefeicaoResponseDto(
                        refeicaoEntity.getIdRefeicao(),
                        refeicaoEntity.getNome(),
                        refeicaoEntity.getDescricao(),
                        refeicaoEntity.getDisponibilidade()
                ) )
                .collect( Collectors.toList() );
    }
}
