package dev.guilhermesv.repository.produto;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.guilhermesv.model.produto.TapeteModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TapeteRepository implements PanacheRepository<TapeteModel>{

    public List<TapeteModel> findByName(String name){
        if(name == null) return null;

        String value = "%"+name.toUpperCase()+"%";
        return list("UPPER(description) like ?1", value);
    }

}
