package dev.guilhermesv.dto.usuario;

import javax.validation.constraints.NotBlank;


public record UsuarioDTO(
    @NotBlank(message = "O campo nomeUsuario deve ser informado.")
    String nomeUsuario,

    @NotBlank(message = "O campo senha deve ser informado.")
    String senha,
    
    @NotBlank(message = "O campo tipoUsuario deve ser informado.")
    String tipoUsuario
)
{
}