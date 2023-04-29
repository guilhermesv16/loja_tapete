package dev.guilhermesv.repository.cliente;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.guilhermesv.model.cliente.ClienteModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<ClienteModel>{
    public List<ClienteModel> findByName(String username){
        if(username == null) return null;

        String value = "%"+username.toUpperCase()+"%";
        return list("UPPER(first_name) like ?1", value);
    }
}
