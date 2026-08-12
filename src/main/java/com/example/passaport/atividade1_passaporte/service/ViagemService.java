package com.example.passaport.atividade1_passaporte.service;

import com.example.passaport.atividade1_passaporte.Model.PessoaModel;
import com.example.passaport.atividade1_passaporte.Model.ViagemModel;
import com.example.passaport.atividade1_passaporte.repository.IPessoaRepository;
import com.example.passaport.atividade1_passaporte.repository.IViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class ViagemService {

    @Autowired
    private IViagemRepository IviagemRepository;
    @Autowired
    private IPessoaRepository IpessoaRepository;

    public ViagemModel salvar(ViagemModel viagem) {

        UUID pessoaid = viagem.getPessoa().getId();

        Optional<PessoaModel> pessoa = IpessoaRepository.findById(pessoaid);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Pessoa nao encontrada");
        }
        return IviagemRepository.save(viagem);
    }
}
