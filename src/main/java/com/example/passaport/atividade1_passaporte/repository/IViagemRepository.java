package com.example.passaport.atividade1_passaporte.repository;

import com.example.passaport.atividade1_passaporte.Model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IViagemRepository extends JpaRepository<ViagemModel, UUID> {
}
