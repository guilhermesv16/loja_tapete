package dev.guilhermesv.repository.endereco;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.guilhermesv.model.endereco.CidadeModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CidadeRepository implements PanacheRepository<CidadeModel>{

    public List<CidadeModel> findByName(String name){
        if(name == null) return null;

        String value = "%"+name.toUpperCase()+"%";
        return list("UPPER(name) like ?1", value);
    }
}
