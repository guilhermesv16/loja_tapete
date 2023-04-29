package dev.guilhermesv.model.endereco;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dev.guilhermesv.model.DefaultEntity;
import dev.guilhermesv.model.cliente.ClienteModel;
import lombok.Data;

@Entity
@Data
@Table(name = "endereco")
public class EnderecoModel extends DefaultEntity {
    
    private String endereco;
    
    private String complemento;
    
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private CidadeModel cidadeModel;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;
}
