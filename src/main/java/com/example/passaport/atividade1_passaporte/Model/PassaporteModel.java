package com.example.passaport.atividade1_passaporte.Model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "tb_passaporte")
public class PassaporteModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String numero;

    private LocalDate dataEmissao;
    private LocalDate dataValidade;

    private String paisEmissor;

    @OneToOne(mappedBy = "passaporte")
    private PessoaModel pessoa;
}

