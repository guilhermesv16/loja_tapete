package dev.guilhermesv.service.cliente;

import java.util.List;

import dev.guilhermesv.dto.cliente.ClienteDTO;
import dev.guilhermesv.dto.cliente.ClienteResponseDTO;

public interface ClienteService {

    List<ClienteResponseDTO> listAll();

    ClienteResponseDTO findById(Long id);
    
    List<ClienteResponseDTO> findByName(String name);

    ClienteResponseDTO persist(ClienteDTO receivedEntity);

    ClienteResponseDTO update(Long id, ClienteDTO receivedEntity);
    
    void deleteById(Long id);

    long count();
}
