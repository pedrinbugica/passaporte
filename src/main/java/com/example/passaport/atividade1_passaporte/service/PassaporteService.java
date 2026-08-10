package com.example.passaport.atividade1_passaporte.service;

import com.example.passaport.atividade1_passaporte.Model.PassaporteModel;
import com.example.passaport.atividade1_passaporte.repository.IPassaporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PassaporteService {

    @Autowired
    private IPassaporteRepository passaporteRepository;

    public PassaporteModel criar(PassaporteModel passaporte) {
        if (passaporte.getNumero() == null || passaporte.getNumero().isBlank()) {
            throw new RuntimeException("Numero do passaporte nao pode ser vazio");
        }
        if (passaporteRepository.findByNumero(passaporte.getNumero()).isPresent()) {
            throw new RuntimeException("Ja existe um passaporte com esse numero");
        }
        if (passaporte.getPaisEmissor() == null || passaporte.getPaisEmissor().isBlank()) {
            throw new RuntimeException("Pais emissor nao pode ser vazio");
        }
        if (passaporte.getDataEmissao() == null) {
            throw new RuntimeException("Data de emissao nao pode ser vazia");
        }
        if (passaporte.getDataValidade() == null) {
            throw new RuntimeException("Data de validade nao pode ser vazia");
        }
        if (passaporte.getDataValidade().isBefore(passaporte.getDataEmissao())) {
            throw new RuntimeException("Data de validade deve ser maior que data de emissao");
        }
        return passaporteRepository.save(passaporte);
    }

    public PassaporteModel buscarPorNumero(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new RuntimeException("Numero nao pode ser vazio!");
        }
        return passaporteRepository.findByNumero(numero)
                .orElseThrow(() -> new RuntimeException("Passaporte nao encontrado com esse numero"));
    }

    public List<PassaporteModel> listarTodos() {
        return passaporteRepository.findAll();
    }

    public PassaporteModel atualizar(UUID id, PassaporteModel passaporteAtualizado) {
        if (id == null) {
            throw new RuntimeException("ID nao pode ser nulo!");
        }
        PassaporteModel passaporteExistente = passaporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passaporte nao encontrado com esse ID!"));

        if (passaporteAtualizado.getPaisEmissor() != null && !passaporteAtualizado.getPaisEmissor().isBlank()) {
            passaporteExistente.setPaisEmissor(passaporteAtualizado.getPaisEmissor());
        }

        if (passaporteAtualizado.getDataEmissao() != null) {
            passaporteExistente.setDataEmissao(passaporteAtualizado.getDataEmissao());
        }

        if (passaporteAtualizado.getDataValidade() != null) {
            passaporteExistente.setDataValidade(passaporteAtualizado.getDataValidade());
        }

        if (passaporteAtualizado.getNumero() != null &&
                !passaporteAtualizado.getNumero().equals(passaporteExistente.getNumero())) {
            throw new RuntimeException("Nao e permitido alterar o numero do passaporte");
        }

        if (passaporteExistente.getDataValidade().isBefore(passaporteExistente.getDataEmissao())) {
            throw new RuntimeException("Data de validade deve ser maior que data de emissao");
        }

        return passaporteRepository.save(passaporteExistente);
    }

    public void deletar(UUID id) {
        if (id == null) {
            throw new RuntimeException("Id nao pode ser nulo");
        }
        if (!passaporteRepository.existsById(id)) {
            throw new RuntimeException("Passaporte nao encontrado com esse ID!");
        }
        passaporteRepository.deleteById(id);
    }
}