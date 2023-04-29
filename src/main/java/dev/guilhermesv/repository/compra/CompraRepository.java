package dev.guilhermesv.repository.compra;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.guilhermesv.model.produto.CompraModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CompraRepository implements PanacheRepository<CompraModel>{

    public List<CompraModel> findByName(String name){
        if(name == null) return null;

        String value = "%"+name.toUpperCase()+"%";
        return list("UPPER(description) like ?1", value);
    }

}
