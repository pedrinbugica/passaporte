package com.example.passaport.atividade1_passaporte.controller;

import com.example.passaport.atividade1_passaporte.Model.PessoaModel;
import com.example.passaport.atividade1_passaporte.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaModel> criar(@RequestBody PessoaModel pessoa) {
        PessoaModel pessoaCriada = pessoaService.criar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCriada);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaModel> buscarPorCpf(@PathVariable String cpf) {
        PessoaModel pessoa = pessoaService.buscarPorCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> listarTodas() {
        List<PessoaModel> pessoa = pessoaService.listarTodas();
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> atualizar(@PathVariable UUID id, @RequestBody PessoaModel pessoaAtualizada) {
        PessoaModel pessoaAtualizada_ = pessoaService.atualizar(id, pessoaAtualizada);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaAtualizada_);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        pessoaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
