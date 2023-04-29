package dev.guilhermesv.dto.endereco;

import javax.validation.constraints.NotBlank;


public record EstadoDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,

    @NotBlank(message = "O campo sigla deve ser informado.")
    String sigla
)
{
}