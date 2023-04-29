package dev.guilhermesv.dto.cliente;

import java.time.LocalDateTime;

import dev.guilhermesv.model.cliente.ClienteModel;

public record ClienteResponseDTO (
    
    Long id, 
    String primeironome,
    String sobrenome,
    String cpf,
    String rg,
    LocalDateTime dataDeNascimento,
    String email,
    String TipoUsuario
){
        public ClienteResponseDTO(ClienteModel entity){        
            this(
                entity.getId(), 
                entity.getPrimeiroNome(),
                entity.getSobrenome(), 
                entity.getCpf(), 
                entity.getRg(), 
                entity.getDataDeNascimento(),
                entity.getUsuarioModel().getNomeUsuario(),
                entity.getUsuarioModel().getTipoUsuarioModel().name()
            );
    }
}
