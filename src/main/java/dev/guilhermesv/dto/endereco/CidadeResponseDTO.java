package dev.guilhermesv.dto.endereco;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import dev.guilhermesv.model.endereco.CidadeModel;

public record CidadeResponseDTO (
    Long id, 
    
    String nome,

    String estado,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime dataCriacao,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime dataAlteracao
){
        public CidadeResponseDTO(CidadeModel entity){        
            this(
                entity.getId(), 
                entity.getNome(), 
                entity.getEstado().getNome(), 
                entity.getdataCricao(), 
                entity.getdataAlteracao());
        }
}
