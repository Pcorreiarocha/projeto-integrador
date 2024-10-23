package com.faculdade.controllers.dtos.usuario;

import com.faculdade.controllers.dtos.perfil.PerfilTypeResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponseDto( String nome,
                                  String cpf,
                                  String senha,
                                  LocalDate dataNascimento,
                                  String sexo,
                                  LocalDateTime dataCadastro,
                                  PerfilTypeResponseDto perfil ) {
}
