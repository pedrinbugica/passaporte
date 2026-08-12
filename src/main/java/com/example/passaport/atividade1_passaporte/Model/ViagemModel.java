package com.example.passaport.atividade1_passaporte.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "tb_viagens")
public class ViagemModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String destino;

    private LocalDate dataSaida;
    private LocalDate dataRetorno;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private PessoaModel pessoa;
}
