package dev.guilhermesv.model.produto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dev.guilhermesv.model.DefaultEntity;
import dev.guilhermesv.model.cliente.ClienteModel;
import dev.guilhermesv.model.endereco.EnderecoModel;
import lombok.Data;

@Entity
@Data
@Table(name = "compra")
public class CompraModel extends DefaultEntity {
    
    // private LocalDateTime dateOfBuy;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private EnderecoModel enderecoModel;

    @OneToMany(mappedBy = "compraModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraTapeteModel> tapetes;


}
