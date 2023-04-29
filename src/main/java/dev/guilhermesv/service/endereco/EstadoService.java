package dev.guilhermesv.service.endereco;

import java.util.List;

import dev.guilhermesv.dto.endereco.EstadoDTO;
import dev.guilhermesv.dto.endereco.EstadoResponseDTO;

public interface EstadoService {

    List<EstadoResponseDTO> listAll();

    EstadoResponseDTO findById(Long id);
    
    List<EstadoResponseDTO> findByName(String name);

    EstadoResponseDTO persist(EstadoDTO receivedEntity);

    EstadoResponseDTO update(Long id, EstadoDTO receivedEntity);
    
    void deleteById(Long id);

    long count();
}
