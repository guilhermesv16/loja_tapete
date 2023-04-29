package dev.guilhermesv.service.usuario;

import java.util.List;

import dev.guilhermesv.dto.usuario.UsuarioDTO;
import dev.guilhermesv.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {

    List<UsuarioResponseDTO> listAll();

    UsuarioResponseDTO findById(Long id);
    
    List<UsuarioResponseDTO> findByName(String name);

    UsuarioResponseDTO persist(UsuarioDTO receivedEntity);

    UsuarioResponseDTO update(Long id, UsuarioDTO receivedEntity);
    
    void deleteById(Long id);

    long count();
}
