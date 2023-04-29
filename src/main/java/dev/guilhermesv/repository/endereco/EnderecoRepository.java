package dev.guilhermesv.repository.endereco;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.guilhermesv.model.endereco.EnderecoModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<EnderecoModel>{

    public List<EnderecoModel> findByName(String name){
        if(name == null) return null;

        String value = "%"+name.toUpperCase()+"%";
        return list("UPPER(address) like ?1", value);
    }
}
