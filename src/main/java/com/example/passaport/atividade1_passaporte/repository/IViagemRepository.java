package com.example.passaport.atividade1_passaporte.repository;

import com.example.passaport.atividade1_passaporte.Model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IViagemRepository extends JpaRepository<ViagemModel, UUID> {

@Query(nativeQuery = true, value = "select v.destino from tb_viagens v group by v.destino order by count(v.destino) desc limit 1")
String destinoMaisVisitado();

}
