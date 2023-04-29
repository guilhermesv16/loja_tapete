package dev.guilhermesv.repository.usuario;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import dev.guilhermesv.model.usuario.UsuarioModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<UsuarioModel>{
    public List<UsuarioModel> findByName(String username){
        if(username == null) return null;

        String value = "%"+username.toUpperCase()+"%";
        return list("UPPER(username) like ?1", value);
    }
}
