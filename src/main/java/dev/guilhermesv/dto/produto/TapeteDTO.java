package dev.guilhermesv.dto.produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TapeteDTO(
    @NotBlank(message = "O campo descricao deve ser informado.")
    String descricao,
    
    // @NotBlank(message = "O campo characters deve ser informado.")
    // String characters,
    
    @NotNull(message = "O campo estoque deve ser informado.")
    int estoque,

    @NotNull(message = "O campo preco deve ser informado.")
    Double preco,

    @NotBlank(message = "O campo status deve ser informado.")
    String status,

    @NotBlank(message = "O campo material deve ser informado.")
    String material
){
}