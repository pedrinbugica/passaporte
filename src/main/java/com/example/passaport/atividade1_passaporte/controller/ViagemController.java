package com.example.passaport.atividade1_passaporte.controller;

import com.example.passaport.atividade1_passaporte.Model.ViagemModel;
import com.example.passaport.atividade1_passaporte.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/viagens")
@RestController
public class ViagemController {

    @Autowired
    ViagemService viagemService;

    @PostMapping
    public ResponseEntity<ViagemModel> salvar(@RequestBody ViagemModel viagemModel) {

        ViagemModel viagemSalva = viagemService.salvar(viagemModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(viagemSalva);
    }

    @GetMapping
    public ResponseEntity<List<ViagemModel>> listarTodos() {
        List<ViagemModel> listaViagem = viagemService.listartodos();

        return ResponseEntity.status(HttpStatus.OK).body(listaViagem);
    }

    @GetMapping("/destino-mais-visitado")
    public ResponseEntity<String> destinoMaisVisitado() {
        return ResponseEntity.status(HttpStatus.OK).body(viagemService.destinoMaisVisitado());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemModel> buscarPorId(@PathVariable UUID id) {

        ViagemModel viagemId = viagemService.buscarPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(viagemId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViagemModel> atualizar(@PathVariable UUID id, @RequestBody ViagemModel viagemAtualizada){

        ViagemModel viagemAtualizada_ = viagemService.atualizar(id,viagemAtualizada);

        return ResponseEntity.status(HttpStatus.OK).body(viagemAtualizada_);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        viagemService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
