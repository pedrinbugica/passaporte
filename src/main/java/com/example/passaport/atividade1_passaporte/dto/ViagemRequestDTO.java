package com.example.passaport.atividade1_passaporte.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ViagemRequestDTO {
    private String destino;
    private LocalDate dataSaida;
    private LocalDate dataRetorno;
    private UUID pessoaId;
}
