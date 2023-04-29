package dev.guilhermesv.model.produto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dev.guilhermesv.model.DefaultEntity;
import lombok.Data;

@Entity
@Data
@Table(name = "compra_tapete")
public class CompraTapeteModel extends DefaultEntity {
    
    @ManyToOne
    @JoinColumn(name = "tapete_id")
    private TapeteModel tapeteModel;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private CompraModel compraModel;

    private int quantidade;
}
