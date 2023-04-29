package dev.guilhermesv.dto.endereco;

import dev.guilhermesv.model.endereco.EnderecoModel;

public record EnderecoResponseDTO (
    Long id, 
    
    String endereco,

    String complemento,

    String cidade,

    String cliente
){
    public EnderecoResponseDTO(EnderecoModel entity){        
        this(
            entity.getId(), 
            entity.getEndereco(), 
            entity.getComplemento(), 

            entity.getCidadeModel().getNome(), 
            entity.getClienteModel().getPrimeiroNome()
            );
    }
}
