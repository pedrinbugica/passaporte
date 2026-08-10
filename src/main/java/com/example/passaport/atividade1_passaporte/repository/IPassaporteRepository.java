package com.example.passaport.atividade1_passaporte.repository;

import com.example.passaport.atividade1_passaporte.Model.PassaporteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPassaporteRepository extends JpaRepository<PassaporteModel, UUID> {

    Optional<PassaporteModel> findByNumero(String numero);

    Optional<PassaporteModel> findByPessoaId(UUID pessoa);
}
