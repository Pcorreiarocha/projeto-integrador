package com.faculdade.formulario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/formulario")
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @PostMapping
    public ResponseEntity<Void> receberFormulario(@RequestBody Formulario formulario) {

        formularioService.salvar(formulario);

        return ResponseEntity.ok().build();
    }
}