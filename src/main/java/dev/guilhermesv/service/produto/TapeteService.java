package dev.guilhermesv.service.produto;

import java.util.List;

import dev.guilhermesv.dto.produto.TapeteDTO;
import dev.guilhermesv.dto.produto.TapeteResponseDTO;

public interface TapeteService {

    List<TapeteResponseDTO> listAll();

    TapeteResponseDTO findById(Long id);
    
    List<TapeteResponseDTO> findByDescription(String name);

    TapeteResponseDTO persist(TapeteDTO receivedEntity);

    TapeteResponseDTO update(Long id, TapeteDTO receivedEntity);
    
    void deleteById(Long id);

    long count();
}
