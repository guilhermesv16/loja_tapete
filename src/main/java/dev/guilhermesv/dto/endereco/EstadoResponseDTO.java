package dev.guilhermesv.dto.endereco;

import dev.guilhermesv.model.endereco.EstadoModel;

public record EstadoResponseDTO (
    Long id, 
    
    String nome,

    String sigla
    
){
        public EstadoResponseDTO(EstadoModel entity){        
            this(
                entity.getId(), 
                entity.getNome(), 
                entity.getSigla());
        }
}
