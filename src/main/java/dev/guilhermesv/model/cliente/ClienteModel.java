package dev.guilhermesv.model.cliente;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dev.guilhermesv.model.DefaultEntity;
import dev.guilhermesv.model.endereco.EnderecoModel;
import dev.guilhermesv.model.usuario.UsuarioModel;
import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class ClienteModel extends DefaultEntity {
    
    @Column(name = "primeiro_nome")
    private String primeiroNome;
    
    @Column(name = "sobrenome")
    private String sobrenome;

    private String cpf;

    private String rg;

    @Column(name = "data_de_nascimento")
    private LocalDateTime dataDeNascimento;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuarioModel;

    @OneToMany(mappedBy = "clienteModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoModel> enderecoModel;
}
