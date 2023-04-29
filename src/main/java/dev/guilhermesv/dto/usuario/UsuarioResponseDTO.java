package dev.guilhermesv.dto.usuario;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import dev.guilhermesv.model.usuario.UsuarioModel;

public record UsuarioResponseDTO (
    Long id, 
    
    String nomeUsuario,

    String tipoUsuario,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime dataCriacao,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime dataAlteracao
){
        public UsuarioResponseDTO(UsuarioModel entity){        
            this(
                entity.getId(), 
                entity.getNomeUsuario(), 
                entity.getTipoUsuarioModel().toString(), 
                entity.getdataCricao(), 
                entity.getdataCricao());
        }
}
