package com.example.passaport.atividade1_passaporte.repository;

import com.example.passaport.atividade1_passaporte.Model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPessoaRepository extends JpaRepository<PessoaModel, UUID> {

    Optional<PessoaModel> findByCpf(String cpf);
}
