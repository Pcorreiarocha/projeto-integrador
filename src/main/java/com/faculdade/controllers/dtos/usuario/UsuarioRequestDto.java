package com.faculdade.controllers.dtos.usuario;

import com.faculdade.controllers.dtos.perfil.PerfilTypeRequestDto;

import java.time.LocalDate;

public record UsuarioRequestDto( Long id,
                                 String nome,
                                 String cpf,
                                 String senha,
                                 LocalDate dataNascimento,
                                 String sexo,
                                 PerfilTypeRequestDto perfil ) {

}
