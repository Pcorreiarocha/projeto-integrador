package com.faculdade.controllers.dtos.formulario_medico;

import com.faculdade.controllers.dtos.usuario.UsuarioResponseDto;

public record FormularioMedicoResponseDto( Long idFormulario,
                                           UsuarioResponseDto medico,
                                           UsuarioResponseDto paciente,
                                           String tipoDieta,
                                           String viaAdministracao,
                                           String observacao ){

}
