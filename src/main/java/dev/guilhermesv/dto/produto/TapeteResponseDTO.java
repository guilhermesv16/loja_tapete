package dev.guilhermesv.dto.produto;

import dev.guilhermesv.model.produto.TapeteModel;

public record TapeteResponseDTO(
    Long id,

    String descricao,
    
    // String characters,
    
    int estoque,

    Double preco,

    String status,

    String material
){
    public TapeteResponseDTO(TapeteModel entity){
        this(
            entity.getId(),
            entity.getDescricao(), 
            //entity.getCharacters(), 
            entity.getEstoque(), 
            entity.getPreco(),
            entity.getStatus().name(),
            entity.getMaterial()
        );
    }
}
