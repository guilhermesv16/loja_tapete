package dev.guilhermesv.repository.endereco;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.guilhermesv.model.endereco.EstadoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<EstadoModel>{       
    
    public List<EstadoModel> findByName(String name){
        if(name == null) return null;

        String value = "%"+name.toUpperCase()+"%";
        return list("UPPER(name) like ?1", value);
    }
}
