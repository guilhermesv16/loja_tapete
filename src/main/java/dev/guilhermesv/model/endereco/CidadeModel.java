package dev.guilhermesv.model.endereco;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dev.guilhermesv.model.DefaultEntity;
import lombok.Data;

@Entity
@Data
@Table(name = "cidade")
public class CidadeModel extends DefaultEntity {
    
    private String nome;
    
    @ManyToOne
    private EstadoModel estado;
}
