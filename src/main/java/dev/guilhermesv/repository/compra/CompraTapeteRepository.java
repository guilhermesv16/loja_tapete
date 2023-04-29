package dev.guilhermesv.repository.compra;

import javax.enterprise.context.ApplicationScoped;

import dev.guilhermesv.model.produto.CompraTapeteModel;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CompraTapeteRepository implements PanacheRepository<CompraTapeteModel>{

    // public List<BuyPipeProduct> findByName(String name){
    //     if(name == null) return null;

    //     String value = "%"+name.toUpperCase()+"%";
    //     return list("UPPER(address) like ?1", value);
    // }

}
