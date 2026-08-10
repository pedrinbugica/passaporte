package com.example.passaport.atividade1_passaporte.controller;

import com.example.passaport.atividade1_passaporte.Model.PassaporteModel;
import com.example.passaport.atividade1_passaporte.service.PassaporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/passaportes")
public class PassaporteController {

    @Autowired
    private PassaporteService passaporteService;

    @PostMapping
    public ResponseEntity<PassaporteModel> criar(@RequestBody PassaporteModel passaporte) {
        PassaporteModel passaporteCriado = passaporteService.criar(passaporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(passaporteCriado);
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<PassaporteModel> buscarPorNumero(@PathVariable String numero) {
        PassaporteModel passaporte = passaporteService.buscarPorNumero(numero);
        return  ResponseEntity.status(HttpStatus.OK).body(passaporte);
    }

    @GetMapping
    public  ResponseEntity<List<PassaporteModel>> listarTodos() {
        List<PassaporteModel> passaportes = passaporteService.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(passaportes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassaporteModel> atualizar(@PathVariable UUID id, @RequestBody PassaporteModel passaporteAtualizado){
        PassaporteModel passaporteAtualizado_ = passaporteService.atualizar(id, passaporteAtualizado);
        return ResponseEntity.status(HttpStatus.OK).body(passaporteAtualizado_);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        passaporteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
