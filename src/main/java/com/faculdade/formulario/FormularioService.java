package com.faculdade.formulario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormularioService {

    @Autowired
    private FormularioRepository formularioRepository;

    public void salvar(Formulario formulario){
        formularioRepository.save(formulario);
    }
}
