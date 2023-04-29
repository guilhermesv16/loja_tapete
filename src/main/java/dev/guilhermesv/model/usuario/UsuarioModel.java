package dev.guilhermesv.model.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import dev.guilhermesv.model.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioModel extends DefaultEntity {

    private String nomeUsuario;
    
    @Column
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuarioModel tipoUsuarioModel;

    // Não necessário
    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // private PersonEntity person;
}
