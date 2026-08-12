package com.example.passaport.atividade1_passaporte.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ViagemResponseDTO {
    private String destino;
    private LocalDate dataSaida;
    private LocalDate dataRetorno;
}
