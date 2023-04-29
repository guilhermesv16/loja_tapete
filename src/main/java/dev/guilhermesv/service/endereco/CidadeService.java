package dev.guilhermesv.service.endereco;

import java.util.List;

import dev.guilhermesv.dto.endereco.CidadeDTO;
import dev.guilhermesv.dto.endereco.CidadeResponseDTO;

public interface CidadeService {

    List<CidadeResponseDTO> listAll();

    CidadeResponseDTO findById(Long id);
    
    List<CidadeResponseDTO> findByName(String name);

    CidadeResponseDTO persist(CidadeDTO receivedEntity);

    CidadeResponseDTO update(Long id, CidadeDTO receivedEntity);
    
    void deleteById(Long id);

    long count();
}
