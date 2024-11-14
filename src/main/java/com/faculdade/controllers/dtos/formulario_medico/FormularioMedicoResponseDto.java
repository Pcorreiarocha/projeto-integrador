package com.faculdade.controllers.dtos.formulario_medico;

public record FormularioMedicoResponseDto( Long idFormulario,
                                           String nomeMedico,
                                           String nomePaciente,
                                           String tipoDieta,
                                           String viaAdministracao,
                                           String observacao ){

}
