package com.example.passaport.atividade1_passaporte.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity (name = "tb_pessoal")
public class PessoaModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private LocalDate dataNascimento;
    private String email;

    @OneToOne
    @JoinColumn(name = "passaporte_id")
    private PassaporteModel passaporte;
}
