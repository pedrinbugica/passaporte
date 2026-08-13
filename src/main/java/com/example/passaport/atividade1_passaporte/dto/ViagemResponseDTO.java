package com.example.passaport.atividade1_passaporte.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ViagemResponseDTO {
    private UUID id;
    private String destino;
    private LocalDate dataSaida;
    private LocalDate dataRetorno;
    private UUID pessoaId;
    private String pessoaNome;
}
