package dev.guilhermesv.model.produto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tapete")
public class TapeteModel extends ProdutoModel {
    
    private String material;

    @OneToMany(mappedBy = "tapeteModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraTapeteModel> tapeteModels;
}
