package dev.guilhermesv.dto.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public record CidadeDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,

    @NotNull(message = "O campo idEstado deve ser informado.")
    long idEstado
){
}