package com.faculdade.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "nome")
    private String nome;

    @JsonFormat(pattern = "cpf")
    private String cpf;

    @JsonFormat(pattern = "senha")
    private String senha;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataNascimento;

    @JsonFormat(pattern = "sexo")
    private String sexo;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODIGO", referencedColumnName = "CODIGO")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private PerfilEntity perfilEntity;
}