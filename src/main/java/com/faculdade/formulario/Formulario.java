package com.faculdade.formulario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String condutaAdotada;
    private String tipoDeDieta;
    private String observacoes;
    private String mensagemParaNutricionista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCondutaAdotada() {
        return condutaAdotada;
    }

    public void setCondutaAdotada(String condutaAdotada) {
        this.condutaAdotada = condutaAdotada;
    }

    public String getTipoDeDieta() {
        return tipoDeDieta;
    }

    public void setTipoDeDieta(String tipoDeDieta) {
        this.tipoDeDieta = tipoDeDieta;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getMensagemParaNutricionista() {
        return mensagemParaNutricionista;
    }

    public void setMensagemParaNutricionista(String mensagemParaNutricionista) {
        this.mensagemParaNutricionista = mensagemParaNutricionista;
    }
}
