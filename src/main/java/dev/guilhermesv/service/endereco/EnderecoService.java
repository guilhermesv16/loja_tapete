package dev.guilhermesv.service.endereco;

import java.util.List;

import dev.guilhermesv.dto.endereco.EnderecoDTO;
import dev.guilhermesv.dto.endereco.EnderecoResponseDTO;

public interface EnderecoService {

    List<EnderecoResponseDTO> listAll();

    EnderecoResponseDTO findById(Long id);
    
    List<EnderecoResponseDTO> findByStreet(String nome);

    EnderecoResponseDTO persist(EnderecoDTO receivedEntity);

    EnderecoResponseDTO update(Long id, EnderecoDTO receivedEntity);
    
    void deleteById(Long id);

    long count();
}
