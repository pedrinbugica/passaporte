package com.example.passaport.atividade1_passaporte.service;

import com.example.passaport.atividade1_passaporte.Model.PessoaModel;
import com.example.passaport.atividade1_passaporte.Model.ViagemModel;
import com.example.passaport.atividade1_passaporte.dto.ViagemRequestDTO;
import com.example.passaport.atividade1_passaporte.dto.ViagemResponseDTO;
import com.example.passaport.atividade1_passaporte.repository.IPessoaRepository;
import com.example.passaport.atividade1_passaporte.repository.IViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ViagemService {

    @Autowired
    private IViagemRepository IviagemRepository;
    @Autowired
    private IPessoaRepository IpessoaRepository;

    private ViagemResponseDTO toResponseDTO(ViagemModel model) {
        ViagemResponseDTO dto = new ViagemResponseDTO();
        dto.setId(model.getId());
        dto.setDestino(model.getDestino());
        dto.setDataSaida(model.getDataSaida());
        dto.setDataRetorno(model.getDataRetorno());

        if (model.getPessoa() != null) {
            dto.setPessoaId(model.getPessoa().getId());
            dto.setPessoaNome(model.getPessoa().getNome());
        }
        return dto;
    }


    public ViagemResponseDTO salvar(ViagemRequestDTO dto) {

        if (dto.getDataRetorno() != null && dto.getDataRetorno().isBefore(dto.getDataSaida())) {
            throw new RuntimeException("Data de retono nao pode ser anterior a data de saida");
        }

        UUID pessoaid = dto.getPessoaId();

        Optional<PessoaModel> pessoa = IpessoaRepository.findById(pessoaid);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Pessoa nao encontrada");
        }

        ViagemModel viagem = new ViagemModel();
        viagem.setDestino(dto.getDestino());
        viagem.setDataSaida(dto.getDataSaida());
        viagem.setDataRetorno(dto.getDataRetorno());
        viagem.setPessoa(pessoa.get());

        ViagemModel viagemsalvo = IviagemRepository.save(viagem);

        return toResponseDTO(viagemsalvo);
    }

        public List<ViagemResponseDTO> listartodos() {
            List<ViagemModel> viagens = IviagemRepository.findAll();
            List<ViagemResponseDTO> listVazia = new ArrayList<>();

            for (ViagemModel viagem : viagens) {

                ViagemResponseDTO dto = toResponseDTO(viagem);
                listVazia.add(dto);
            }
            return listVazia;
    }

    public ViagemResponseDTO buscarPorId(UUID id) {
        ViagemModel viagem = IviagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem nao encontrada"));
        return toResponseDTO(viagem);
    }

    public ViagemResponseDTO atualizar(UUID id, ViagemRequestDTO dto) {

        ViagemModel viagem = IviagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem nao encontrada"));

        if (dto.getDestino() != null) {
            viagem.setDestino(dto.getDestino());
        }
        if (dto.getDataSaida() != null) {
            viagem.setDataSaida(dto.getDataSaida());
        }
        if (dto.getDataRetorno() != null) {
            viagem.setDataRetorno(dto.getDataRetorno());
        }

        ViagemModel viagemSalva = IviagemRepository.save(viagem);

        return toResponseDTO(viagemSalva);
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        IviagemRepository.deleteById(id);
    }
    public String destinoMaisVisitado() {return IviagemRepository.destinoMaisVisitado();}

}
