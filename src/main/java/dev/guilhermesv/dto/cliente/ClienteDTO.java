package dev.guilhermesv.dto.cliente;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ClienteDTO(
    @NotBlank(message = "O campo primeiroNome deve ser informado.")
    String primeiroNome,

    @NotBlank(message = "O campo sobrenome deve ser informado.")
    String sobrenome,
    
    @NotBlank(message = "O campo cpf deve ser informado.")
    String cpf,

    @NotBlank(message = "O campo rg deve ser informado.")
    String rg,

    @NotBlank(message = "O campo dataDeNascimento deve ser informado.")
    String dataDeNascimento,
    
    @NotNull(message = "O campo email deve ser informado.")
    Long idUsuario
)
{
}