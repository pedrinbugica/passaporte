package com.example.passaport.atividade1_passaporte.service;

import com.example.passaport.atividade1_passaporte.Model.PessoaModel;
import com.example.passaport.atividade1_passaporte.repository.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private IPessoaRepository pessoaRepository;

    public PessoaModel criar(PessoaModel pessoa) {
        if (pessoa.getCpf() == null || pessoa.getCpf().isBlank()) {
            throw new RuntimeException("Cpf nao pode ser vazio");
        }
        if (pessoaRepository.findByCpf(pessoa.getCpf()).isPresent()) {
            throw new RuntimeException("Ja existe uma pessoa com esse cpf");
        }
        if (pessoa.getNome() == null || pessoa.getNome().isBlank()) {
            throw new RuntimeException("Nome nao pode ser vazio");
        }
        if (pessoa.getDataNascimento() == null) {
            throw new RuntimeException("Data de nascimento nao pode ser vazia");
        }
        return pessoaRepository.save(pessoa);
    }

    public PessoaModel buscarPorCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new RuntimeException("Cpf nao pode ser vazio!");
        }
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Pessoa nao encontrado com esse cpf"));
    }

    public List<PessoaModel> listarTodas() {
        return pessoaRepository.findAll();
    }

    public PessoaModel atualizar(UUID id, PessoaModel pessoaAtualizada) {
        if (id == null) {
            throw new RuntimeException("ID nao pode ser nulo!");
        }
        PessoaModel pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa nao encontrada com esse ID!"));


        if (pessoaAtualizada.getNome() != null && !pessoaAtualizada.getNome().isBlank()) {
            pessoaExistente.setNome(pessoaAtualizada.getNome());
        }

        if (pessoaAtualizada.getEmail() != null && !pessoaAtualizada.getEmail().isBlank()) {
            pessoaExistente.setEmail(pessoaAtualizada.getEmail());
        }

        if (pessoaAtualizada.getDataNascimento() != null) {
            pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        }

        if (pessoaAtualizada.getCpf() != null && !pessoaAtualizada.getCpf().equals(pessoaExistente.getCpf())) {
            throw new RuntimeException("Nao e permitdo alterar cpf");
        }

        return pessoaRepository.save(pessoaExistente);
    }

    public void deletar(UUID id) {
        if (id == null) {
            throw new RuntimeException("Id nao pode ser nulo");
        }
        if (!pessoaRepository.existsById(id)) {
            throw new RuntimeException("Pessoa nao encontrada com esse ID!");
        }
        pessoaRepository.deleteById(id);
    }
}

