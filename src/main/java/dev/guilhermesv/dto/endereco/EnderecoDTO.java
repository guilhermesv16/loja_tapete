package dev.guilhermesv.dto.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record EnderecoDTO(
    @NotBlank(message = "O campo endereco deve ser informado.")
    String endereco,
    
    @NotBlank(message = "O campo complemento deve ser informado.")
    String complemento,
    
    @NotNull(message = "O campo idCidade deve ser informado.")
    Long idCidade,

    // @NotNull(message = "O campo idPerson deve ser informado.")
    Long idCliente
){
}