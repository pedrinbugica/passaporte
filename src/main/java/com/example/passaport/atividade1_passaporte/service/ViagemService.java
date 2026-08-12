package com.example.passaport.atividade1_passaporte.service;

import com.example.passaport.atividade1_passaporte.Model.PessoaModel;
import com.example.passaport.atividade1_passaporte.Model.ViagemModel;
import com.example.passaport.atividade1_passaporte.repository.IPessoaRepository;
import com.example.passaport.atividade1_passaporte.repository.IViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ViagemService {

    @Autowired
    private IViagemRepository IviagemRepository;
    @Autowired
    private IPessoaRepository IpessoaRepository;

    public ViagemModel salvar(ViagemModel viagem) {

        if (viagem.getDataRetorno() != null && viagem.getDataRetorno().isBefore(viagem.getDataSaida())) {
            throw new RuntimeException("Data de retono nao pode ser anterior a data de saida");
        }

        UUID pessoaid = viagem.getPessoa().getId();

        Optional<PessoaModel> pessoa = IpessoaRepository.findById(pessoaid);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Pessoa nao encontrada");
        }
        return IviagemRepository.save(viagem);
    }

    public List<ViagemModel> listartodos() {
        return IviagemRepository.findAll();
    }

    public ViagemModel buscarPorId(UUID id) {
        return IviagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Viagem nao encontrada"));
    }

    public ViagemModel atualizar(UUID id, ViagemModel viagemAtualizada) {

        ViagemModel viagem = IviagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem nao encontrada"));

        if (viagemAtualizada.getDestino() != null) {
            viagem.setDestino(viagemAtualizada.getDestino());
        }
        if (viagemAtualizada.getDataSaida() != null) {
            viagem.setDataSaida(viagemAtualizada.getDataSaida());
        }
        if (viagemAtualizada.getDataRetorno() != null) {
            viagem.setDataRetorno(viagemAtualizada.getDataRetorno());
        }
        return IviagemRepository.save(viagem);
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        IviagemRepository.deleteById(id);
    }
    public String destinoMaisVisitado() {return IviagemRepository.destinoMaisVisitado();}
}
