package dev.guilhermesv.model.endereco;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "estado")
@NoArgsConstructor
@AllArgsConstructor
public class EstadoModel extends dev.guilhermesv.model.DefaultEntity {
    
    private String nome;
    
    private String sigla;

    // @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference
    // private List<CityEntity> cities;
}
